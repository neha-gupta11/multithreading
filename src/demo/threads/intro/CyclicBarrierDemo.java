package demo.threads.intro;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by neha on 10/11/17.
 */
public class CyclicBarrierDemo {

    static Random r = new Random();

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> run(barrier), "T1");
        Thread t2 = new Thread(() -> run(barrier), "T2");
        Thread t3 = new Thread(() -> run(barrier), "T3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void run(CyclicBarrier barrier) {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread "+Thread.currentThread().getName()+" -> "+i);
            try {
                Thread.sleep(r.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 6) {
                try {
                    System.out.println("Thread "+Thread.currentThread().getName()+" is waiting for others");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
