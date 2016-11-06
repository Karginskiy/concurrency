package winterbetutorials;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static concurrency.tutorials.ConcurrentUtils.stop;

public class SynchronizeExample {

    int count = 0;

    synchronized void increment() {
        count = count + 1;
    }

    public void thready() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executorService.submit(this::increment));

        stop(executorService);

        System.out.println(count);

        // 9978 - result. Not 10000.

    }

    public static void main(String[] args) {

        new SynchronizeExample().thready();

    }

}
