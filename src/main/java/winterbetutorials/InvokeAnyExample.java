package winterbetutorials;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        try {
            String result = executorService.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Callable callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

}
