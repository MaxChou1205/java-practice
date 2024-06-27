import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static class Buffer {
        private static final int CAPATITY = 1;
        private LinkedList<Integer> queue = new LinkedList<Integer>();
        private static Lock lock = new ReentrantLock();
        private static Condition notFull = lock.newCondition();
        private static Condition notEmpty = lock.newCondition();

        public void write(int value) {
            lock.lock();
            try {
                while (queue.size() == CAPATITY) {
                    notFull.await();
                }
                queue.offer(value);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();

            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                value = queue.remove();
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }

    private static Buffer buffer = new Buffer();

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("Consumer read: " + buffer.read());
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class Producer implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println("Producer write: " + i);
                buffer.write(i++);
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Consumer());
        es.execute(new Producer());
        es.shutdown();
    }
}