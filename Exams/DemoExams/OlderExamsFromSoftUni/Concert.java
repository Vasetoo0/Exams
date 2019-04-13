package DemoTest;

import java.util.*;

public class Concert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;

        Map<String, Set<String>> bands = new LinkedHashMap<>();

        Map<String, Integer> concertTime = new TreeMap<>();

        int totalTime = 0;

        while (!"start of concert".equals(input = scanner.nextLine())) {

            String[] data = input.split(";\\s+");

            String command = data[0];
            String bandName = data[1];

            if (command.equals("Add")) {
                String[] members = data[2].split(",\\s+");

                if (!bands.containsKey(bandName)) {
                    bands.put(bandName, new LinkedHashSet<>());
                    for (String member : members) {
                        bands.get(bandName).add(member);
                    }
                } else {
                    for (String member : members) {
                        bands.get(bandName).add(member);
                    }
                }
            } else if (command.equals("Play")) {
                int playTime = Integer.parseInt(data[2]);
                if (!concertTime.containsKey(bandName)) {
                    concertTime.put(bandName, playTime);
                } else {
                    concertTime.put(bandName, concertTime.get(bandName) + playTime);
                }

                totalTime += playTime;
            }
        }

        System.out.println(String.format("Total time: %d", totalTime));

        concertTime.entrySet().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getValue(), t1.getValue()))
                .forEach(e -> {
                    System.out.println(String.format("%s -> %d", e.getKey(), e.getValue()));
                });

        String bandToShow = scanner.nextLine();

        System.out.println(bandToShow);

        for (Map.Entry<String, Set<String>> kvp : bands.entrySet()) {
            if (kvp.getKey().equals(bandToShow)) {
                kvp.getValue().forEach(e -> System.out.println(String.format("=> %s", e)));
            }
        }
    }
}
