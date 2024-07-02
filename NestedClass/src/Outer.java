public class Outer {
    private int i = 0;

    private void printHello() {
        System.out.println("Hello");
    }

    class Inner {
        void printI() {
            System.out.println(i);
            printHello();
        }
    }
}
