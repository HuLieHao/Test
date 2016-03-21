package executor;

/**
 * Created by guoyu on 15/4/17.
 */

public interface ExecutorTask<T, V> {

    V execute(T arg);

}