package executor;

import java.util.List;

/**
 * Created by guoyu on 15/4/17.
 */


public interface BatchExecutor {

    <T, V> List<V> batch(List<T> args, ExecutorTask<T, V> task);

}
