package others.leetc;

import java.util.function.IntConsumer;

//https://leetcode.com/problems/fizz-buzz-multithreaded/
public class FizzBuzzMultithreaded {

    private final int n;
    private volatile int cur;
    private final Object monitor;

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
        this.cur = 1;
        this.monitor = new Object();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (monitor) {
            while (cur <= n) {
                if (cur % 3 == 0 && cur % 15 != 0) {
                    printFizz.run();
                    cur++;
                    monitor.notifyAll();
                } else {
                    monitor.wait();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (monitor) {
            while (cur <= n) {
                if (cur % 5 == 0 && cur % 15 != 0) {
                    printBuzz.run();
                    cur++;
                    monitor.notifyAll();
                } else {
                    monitor.wait();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (monitor) {
            while (cur <= n) {
                if (cur % 15 == 0) {
                    printFizzBuzz.run();
                    cur++;
                    monitor.notifyAll();
                } else {
                    monitor.wait();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (monitor) {
            while (cur <= n) {
                if (cur % 3 != 0 && cur % 5 != 0) {
                    printNumber.accept(cur);
                    cur++;
                    monitor.notifyAll();
                } else {
                    monitor.wait();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzzMultithreaded fb = new FizzBuzzMultithreaded(15);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    fb.fizz(() -> System.out.println("fizz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    fb.buzz(() -> System.out.println("buzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                try {
                    fb.fizzbuzz(() -> System.out.println("fizzbuzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                try {
                    fb.number(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
