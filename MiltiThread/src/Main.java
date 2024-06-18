class MyPrint implements Runnable {
    private final int times;
    private final String content;

    public MyPrint(int times) {
        this(times, null);
    }

    public MyPrint(int times, String content) {
        this.times = times;
        this.content = content;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            if (content == null)
                System.out.println(i);
            else
                System.out.println(content);
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        single thread
//        for (int i = 0; i < 50; i++) {
//            System.out.println(i);
//        }
//
//        for (int i = 0; i < 20; i++) {
//            System.out.println("a");
//        }

        // multiple thread
        Thread thread1 = new Thread(new MyPrint(50));
        Thread thread2 = new Thread(new MyPrint(20, "aa"));
        Thread thread3 = new Thread(new MyPrint(50));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}