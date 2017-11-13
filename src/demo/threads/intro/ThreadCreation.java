package demo.threads.intro;

/**
 * Created by neha on 9/11/17.
 */
public class ThreadCreation {

    public static void main(String[] args) {
        A a = new A();
        a.start();

        B b = new B();
        Thread t = new Thread(b, "B");
        t.start();

        Thread c = new Thread(() -> {
            System.out.println("This is thread " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getThreadGroup().getName());
        }, "C");
        c.start();
    }
}

class A extends Thread {

    public A() {
        this("A");
    }

    public A(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getThreadGroup().getName());
        System.out.println("This is a thread A: " + getName());
    }
}

class B implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println("This is thread B: " + Thread.currentThread().getName());
    }
}
