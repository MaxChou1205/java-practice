import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    static int[] buffer = new int[3];
    static int producerIndex = 0;
    static int consumerIndex = 0;
    static Semaphore sLock, nLock, eLock;

    private static void append(int value) {
        buffer[producerIndex] = value;
        if (producerIndex != buffer.length - 1) {
            producerIndex++;
        } else {
            producerIndex = 0;
        }
    }

    private static int take() {
        int tmp = buffer[consumerIndex];
        if (consumerIndex != buffer.length - 1) {
            consumerIndex++;
        } else {
            consumerIndex = 0;
        }
        return tmp;
    }

    private static class ProducerTask implements Runnable {
        int threadId;

        ProducerTask(int threadId) {
            this.threadId = threadId;
            System.out.println("Producer #" + threadId + " launched.");
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    eLock.acquire();
                    sLock.acquire();
                    int value = (int) (Math.random() * 10);
                    System.out.println("Producer #" + threadId + " produced " + value);
                    append(value);
                    sLock.release();
                    nLock.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        int threadId;

        ConsumerTask(int threadId) {
            this.threadId = threadId;
            System.out.println("Consumer #" + threadId + " launched.");
        }

        @Override
        public void run() {
            int valueTook;
            for (int i = 0; i < 20; i++) {
                try {
                    nLock.acquire();
                    sLock.acquire();
                    valueTook = take();
                    System.out.println("Consumer #" + threadId + " consumed " + valueTook);
                    sLock.release();
                    eLock.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) {
        sLock = new Semaphore(1);
        nLock = new Semaphore(0);
        eLock = new Semaphore(buffer.length);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                es.execute(new ProducerTask(i));
            } else {
                es.execute(new ConsumerTask(i));
            }
        }
        es.shutdown();
    }
}