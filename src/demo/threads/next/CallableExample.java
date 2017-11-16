package demo.threads.next;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws Exception{
        Callable callable=new ImplementCallable();
        ExecutorService service= Executors.newSingleThreadExecutor();
        Future<String> future=service.submit(callable);
        System.out.println("The returned value "+future.get());
        service.shutdown();
        System.out.println("End of main");
    }
}

class ImplementCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("The call method "+Thread.currentThread().getName());
        Thread.sleep(1000);   // try-catch not required
        return "Something";
    }
}

class ImplementRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("this is run method " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}