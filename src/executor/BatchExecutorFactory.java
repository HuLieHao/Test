package executor;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by guoyu on 15/4/17.
 */
@Component
public class BatchExecutorFactory{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BatchExecutorFactory.class);

    private HashMap<Object, BatchExecutor> executors = new HashMap<>();

    public BatchExecutorFactory() {
    }

    public synchronized BatchExecutor getExecutor(Object key) {
        if (!executors.containsKey(key)) {
            executors.put(key, new PooledExecutor(key));
        }
        return executors.get(key);
    }

    private static class PooledExecutor implements BatchExecutor, ThreadFactory {

        private ExecutorService pool;

        private Object key;

        private PooledExecutor(Object key) {
            this.key = key;
            this.pool = Executors.newFixedThreadPool(20, this);
        }

        @Override
        public <T, V> List<V> batch(List<T> args, ExecutorTask<T, V> task) {
            int size = args.size();
            List<Callable<V>> callables = new ArrayList<>(size);
            List<V> res = new ArrayList<>(size);
            for (T arg : args) {
                callables.add(new TaskCallable(task, arg));
            }
            Integer count=0;
            try {
                List<Future<V>> futures = pool.invokeAll(callables, 60, TimeUnit.SECONDS);
                for (Future<V> future : futures) {
                    res.add(future.get());
                    count++;
                }
                return res;
            } catch (Exception e) {
                LOG.info("BatchExecutorFactory.batch： 第"+count+" 号任务回收结果时失败!");
                throw new RuntimeException(e);
            }
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,
                MessageFormat.format("batch-{0}", key.toString()));
            t.setDaemon(true);
            return t;
        }
    }

    private static class TaskCallable<T, V> implements Callable<V>{

        ExecutorTask<T, V> task;

        T arg;

        private TaskCallable(ExecutorTask<T, V> task, T arg) {
            this.task = task;
            this.arg = arg;
        }

        @Override
        public V call() throws Exception {
            return task.execute(arg);
        }
    }
}
