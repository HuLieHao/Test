package lambda;

import java.util.Optional;

/**
 * User:  maktub
 * Date:   16/6/20 上午10:57
 */
public class Demo1 {

    public static void execute(WorkerInterface face) {
        face.doSomeWork();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("lambda1");
            }
        }).start();

        new Thread( () -> System.out.println("lambda2")).start();

        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Lambda expression 2");
            }
        });

        execute(() -> System.out.println("Worker invoked using Lambda expression 1"));

    }
}
