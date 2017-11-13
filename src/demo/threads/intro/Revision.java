package demo.threads.intro;

import sun.awt.windows.ThemeReader;

public class Revision {
    public static void main(String[] args) {
        Thread1 thread1= new Thread1();
        thread1.start();

        Thread thread= new Thread(new Thread2());
        thread.start();

//        Creating thread using java 8
        Thread thread2=new Thread(()-> System.out.println("This is another thread"),"Thread3");
        thread2.start();

        TestVolatile testVolatile = new TestVolatile();
        TestVolatile testVolatile1 = new TestVolatile();
        testVolatile.start();
        testVolatile1.start();
    }
}

class Thread1 extends Thread{

    Thread1(){
        this("ThreadName");
    }
    Thread1(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("The thread group is "+getThreadGroup().getName());
        System.out.println("The thread priority is: "+getPriority());
        System.out.println("this is thread 1 "+getName());
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("The thread group is "+Thread.currentThread().getThreadGroup().getName());
        System.out.println("The thread priority is: "+Thread.currentThread().getPriority());
        System.out.println("this is thread 2 "+Thread.currentThread().getName());
    }
}

class TestVolatile extends Thread{
    private volatile Boolean mutex=true;

   public void run(){
       System.out.println("waiting for mutex");
      synchronized (mutex){
          System.out.println("Processing "+mutex);
          try {
              mutex=false;
              System.out.println("The mutex is "+mutex);
              Thread.sleep(3000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
       System.out.println("Mutex released............");
   }
}