import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        String[] names = new String[]{"Ron", "Wilson", "Harry", "Grace", "Mike", "Ray"};
//        Stream.of(names)
//                .filter(item -> item.startsWith("R"))
//                .forEach(System.out::println);

//        Arrays.stream(new int[]{2, 4, 6, 8, 10})
//                .map(n -> n * n)
//                .average()
//                .ifPresent(System.out::println);

//        ArrayList<String> namesList = new ArrayList<>(Arrays.asList("Ron", "Wilson", "Harry", "Grace", "Mike", "Ray"));
//        namesList.stream()
//                .map(name -> name.substring(0, 1).toLowerCase() + name.substring(1).toUpperCase())
//                .forEach(System.out::println);

//        Stream<String> bands = Files.lines(Paths.get("StreamAPI/bands.txt"), StandardCharsets.UTF_8);
//        bands
//                .filter(x -> x.length() > 13)
//                .sorted()
//                .forEach(System.out::println);
//        List<String> myBandList = bands
//                .filter(x -> x.contains("jit"))
//                .toList();
//        bands.close();

//        Stream<String> rows = Files.lines(Paths.get("StreamAPI/data.txt"), StandardCharsets.UTF_8);
//        Map<String, Integer> myMap = rows.map(x -> x.split(","))
//                .filter(x -> x.length == 3)
//                .filter(x -> Integer.parseInt(x[1]) > 15)
//                .collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
//        myMap.forEach((key, value) -> {
//            System.out.println(key + ", " + value);
//        });
//        rows.close();

//        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        IntSummaryStatistics s = intStream.summaryStatistics();
//        System.out.println(s.getMax());
//        System.out.println(s.getMin());
//        System.out.println(s.getAverage());
//        System.out.println(s.getCount());
//        System.out.println(s.getSum());

//        int result = intStream.reduce(0, (a, b) -> a + b);
//        System.out.println(result);

        Random random = new Random();
        int[] list = random.ints(50000000)
                .toArray();
        long startTime = System.currentTimeMillis();
        int[] list1 = IntStream.of(list).filter(e -> e > 0).sorted().limit(5).toArray();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
        System.out.println("=====");

        startTime = System.currentTimeMillis();
        int[] list2 = IntStream.of(list).parallel().filter(e -> e > 0).sorted().limit(5).toArray();
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }
}