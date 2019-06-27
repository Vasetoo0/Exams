package FinalExam;

import java.util.*;

public class OnTheWayToAnnapurna {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String input;

        Map<String, List<String>> stores = new LinkedHashMap<>();

        while (!"END".equals(input = scanner.nextLine())) {

            String[] data = input.split("->");

            String command = data[0];
            String store = data[1];

            if (command.equals("Add")) {

                String item = data[2];
                if (item.contains(",")) {

                    String[] items = item.split(",");

                    if (!stores.containsKey(store)) {
                        stores.put(store, new ArrayList<>());
                        for (String product : items) {

                            stores.get(store).add(product);
                        }
                    } else {
                        for (String product : items) {

                            stores.get(store).add(product);
                        }
                    }
                } else {
                    if (!stores.containsKey(store)) {
                        stores.put(store, new ArrayList<>());
                        stores.get(store).add(item);
                    } else {
                        stores.get(store).add(item);
                    }
                }
            } else {
                stores.remove(store);
            }
        }
        System.out.println("Stores list:");

        stores.entrySet().stream()
                .sorted((n1,n2) -> n2.getKey().compareTo(n1.getKey()))
                .sorted((c1,c2) -> Integer.compare(c2.getValue().size(),c1.getValue().size()))
                .forEach(e -> {
                    System.out.println(String.format("%s", e.getKey()));
                    e.getValue().forEach(i -> {
                        System.out.println(String.format("<<%s>>",i));
                    });
                });

    }
}
