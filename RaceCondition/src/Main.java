import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Account account = new Account();

    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return this.balance;
        }

        // method 1
//        public synchronized void deposit() {
//            this.balance = this.balance + 1;
//        }


        public void deposit() {
            this.balance = this.balance + 1;
        }
    }

    private static class AddMoneyTask implements Runnable {
        @Override
//        public void run() {
//            account.deposit();
//        }
        // method 2
        public void run() {
            synchronized (account) {
                account.deposit();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            es.execute(new AddMoneyTask());
        }

        es.shutdown();

        while (!es.isTerminated()) {

        }
        System.out.println(account.getBalance());
    }
}