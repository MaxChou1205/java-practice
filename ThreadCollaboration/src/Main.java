import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Account account = new Account();

    private static class Account {
        private int balance = 0;
        private static final Lock lock = new ReentrantLock();
        private static final Condition newDeposit = lock.newCondition();

        public int getBalance() {
            return this.balance;
        }

        public void deposit(int amount) {
            lock.lock();
            this.balance += amount;
            System.out.println("deposit " + amount + ", balance is " + getBalance());
            newDeposit.signalAll();
            lock.unlock();
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while (getBalance() < amount) {
                    System.out.println("\t wait for deposit.");
                    newDeposit.await();
                }
                this.balance -= amount;
                System.out.println("withdraw " + amount + ", balance is " + getBalance());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public static class DepositTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    account.deposit((int) (Math.random() * 20) + 5);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class WithdrawTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 20) + 5);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new DepositTask());
        es.execute(new WithdrawTask());
        es.shutdown();
    }
}