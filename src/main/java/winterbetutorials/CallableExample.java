package winterbetutorials;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) {

        Callable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 10;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Future<Integer> future = executorService.submit(task);

            System.out.println("future done? " + future.isDone());

            Integer result = future.get();
            System.out.println("future done? " + future.isDone());
            System.out.print("result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
