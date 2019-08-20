package others.cmpn.LogicH;

import util.Creator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LookAheadIterator<T> implements Iterator<T> {

    final Iterator<T> iter;

    volatile T value;

    final Lock lock;

    public LookAheadIterator(Iterator<T> iter) {
        this.iter = iter;
        this.lock = new ReentrantLock();
        fetchNext();
    }

    private void fetchNext() {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(()->{
            try {
                lock.lock();
                value = iter.next();
            } finally {
                lock.unlock();
            }
        });
        ex.shutdown();
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        try {
            lock.lock();
            result = value != null || iter.hasNext();
        } finally {
            lock.unlock();
        }
        return result;
    }

    @Override
    public T next() {
        while (true) {
            lock.lock();
            //System.out.println(Thread.currentThread().getName() + " acquired sem");
            if (value == null) {
                if (iter.hasNext()) {
                    //System.out.println(Thread.currentThread().getName() + " released sem from cont");
                    lock.unlock();
                    continue;
                }
                //System.out.println(Thread.currentThread().getName() + " released sem from break");
                lock.unlock();
                break;
            }
            T oldVal = value;
            value = null;
            //System.out.println(Thread.currentThread().getName() + " will return " + oldVal + " & set value to " + value);
            fetchNext();
            //System.out.println(Thread.currentThread().getName() + " released sem");
            lock.unlock();
            return oldVal;
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        List<Integer> list = Creator.getList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        LookAheadIterator<Integer> lookAheadIterator = new LookAheadIterator<>(list.iterator());
        int numberOfThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads);
        ThreadFactory tf = new ThreadFactory() {
            AtomicInteger tid = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "Thread id : " + tid.incrementAndGet());
            }
        };
        ExecutorService ftpe = Executors.newFixedThreadPool(numberOfThreads, tf);
        for (int i = 1; i <= numberOfThreads; i++) {
            ftpe.submit(() -> {
                try {
                    barrier.await();
                    while (lookAheadIterator.hasNext()) {
                        System.out.println("Value from thread " + Thread.currentThread().getName() + " : " + lookAheadIterator.next());
                    }

                } catch (Exception e) {
                    //System.out.println("Exception in thread " + Thread.currentThread().getName() + " : " + e.getClass().getName());
                    //e.printStackTrace();
                }
            });
        }
        ftpe.shutdown();
    }
}
