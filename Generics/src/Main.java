class TheMap<T, k> {
    private T key;
    private k value;

    public k getValue() {
        return value;
    }

    public void setValue(k value) {
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}

public class Main {
    public static <T> T PrintHello(T obj) {
        return (T) (obj + " Hello");
    }

    public static <T> void PrintClass(T obj) {
        System.out.println(obj.getClass().getName());
    }

    public static void main(String[] args) {
        TheMap myTheMap = new TheMap();
        myTheMap.setKey("AA");
        myTheMap.setValue("BB");
        System.out.println(myTheMap.getKey() + ", " + myTheMap.getValue());

        Main.PrintClass(6);
        System.out.println(Main.PrintHello("aaa"));

        Info<String> a = new Info<>();
        a.setInfo("Hello");
        System.out.println(a.getInfo());

        Info<Integer> b = new Info<>();
        b.setInfo(5);
        System.out.println(b.getInfo());
    }
}