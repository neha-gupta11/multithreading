package demo.threads.intro;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by neha on 10/11/17.
 */
public class SemaphoreDemo {

    Semaphore semaphore = new Semaphore(2);

    public void perform() {

        String tname = "Thread "+Thread.currentThread().getName();
        System.out.println(tname + " trying to enter in critical section");

        try {
            semaphore.acquire();
            System.out.println(tname + " has acquired the lock.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tname + " is finishing now and releasing the lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo s = new SemaphoreDemo();
        List<Thread> threads = IntStream.range(1, 11)
                .boxed()
                .map(integer -> new Thread(s::perform, "T" + integer)).collect(Collectors.toList());

        threads.stream().forEach(Thread::start);
    }
}
