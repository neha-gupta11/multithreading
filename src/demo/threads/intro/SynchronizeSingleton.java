package demo.threads.intro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizeSingleton {
    public static void main(String[] args) {
        System.out.println("The Main method");

        Thread thread1=new Thread(SynchronizeSingleton::doSomething,"Thread 1");
        Thread thread2=new Thread(SynchronizeSingleton::doSomething,"Thread 2");

        thread1.start();
        thread2.start();

    }

    static void doSomething(){
        SomeService service1=SomeService.getInstance();
        System.out.println("The service reference is " + service1);
    }
}

class  SomeService{
    private static SomeService instance;
    private SomeService() {
        System.out.println("Creating the Object for the said service....");
    }

    public static SomeService getInstance() {
        System.out.println("Going to lock the class " + Thread.currentThread().getName());
        synchronized (SomeService.class) {
            System.out.println("Lock acquired by " + Thread.currentThread().getName());
            try {
                Thread.sleep(4000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (instance == null)
                instance = new SomeService();
        }
        System.out.println("Releasing lock..........." + Thread.currentThread().getName());
        return instance;
    }
}