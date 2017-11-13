 package demo.threads.intro;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

 /**
 * Created by neha on 10/11/17.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        A1 a1 = new A1(latch);
        B1 b1 = new B1(latch);

        a1.start();
        b1.start();

        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class A1 extends Thread {

    CountDownLatch latch;
    public A1(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Thread A1 started");
        CountDownLatchDemo.sleep();
        System.out.println("Thread A1 completed");
        latch.countDown();
    }
}

class B1 extends Thread {

    CountDownLatch latch;

    public B1(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Thread B1 started");
        CountDownLatchDemo.sleep();
        System.out.println("Thread B1 completed");
        latch.countDown();
    }
}