import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//
//        list.forEach(item -> {
//            System.out.println(item);
//        });

//        Map<String, Integer> map = new HashMap<>();
//        map.put("Max", 5000);
//        map.put("John", 3000);
//        map.put("Peter", 9000);
//        map.forEach((k, v) -> System.out.println("key is: " + k + ", and value is: " + v));

        MyRunnable r1 = new MyRunnable();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        });
        t1.start();
        t2.start();
    }
}