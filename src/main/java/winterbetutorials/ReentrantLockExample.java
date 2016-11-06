package winterbetutorials;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static concurrency.tutorials.ConcurrentUtils.sleep;
import static concurrency.tutorials.ConcurrentUtils.stop;

public class ReentrantLockExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executorService.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executorService.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executorService);

    }
}