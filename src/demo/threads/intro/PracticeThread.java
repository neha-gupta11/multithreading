package demo.threads.intro;


public class PracticeThread {
    public static void main(String[] args) throws InterruptedException {
        First first=new First();
        first.start();
        first.join();
        System.out.println("Exiting from main thread");
    }

}

class First extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Iteration "+i+getName());
        }
    }
}

class Second implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Iteration "+i+Thread.currentThread().getName());
        }
    }
}