package winterbetutorials;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAllExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3"
        );

        executor.invokeAll(callables)
                .stream()
                .map(stringFuture -> {
                    try {
                        return stringFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).forEach(System.out::println);

    }

}
