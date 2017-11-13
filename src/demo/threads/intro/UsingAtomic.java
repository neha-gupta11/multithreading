package demo.threads.intro;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by neha on 10/11/17.
 */
public class UsingAtomic {


    int i = 0;

    AtomicInteger integer = new AtomicInteger(0);

    public synchronized void increment() {
        i = i + 1; // 3 CPU instruction (read-> update -> write)
    }

    public void incrementAtomic() {
        integer.incrementAndGet();  // comare-and-swap || compare-and-set CPU instruction (compare and set 1 instruction)
    }



}
