package MidExam;

import java.util.*;
import java.util.stream.Collectors;

public class LastStop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> paintings = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = "";

        while (!"END".equals(input = scanner.nextLine())) {

            String [] data = input.split("\\s+");

            String command = data[0];

            switch (command) {
                case "Change":
                    int firstNum = Integer.parseInt(data[1]);
                    int secondNum = Integer.parseInt(data[2]);

                    if(paintings.contains(firstNum)) {

                        paintings.set(paintings.indexOf(firstNum), secondNum);

                    }

                    break;
                case "Hide":

                    int paintingNum = Integer.parseInt(data[1]);

                    paintings.remove(paintings.indexOf(paintingNum));

                    break;
                case "Switch":

                    int firstPainting = Integer.parseInt(data[1]);
                    int secondPainting = Integer.parseInt(data[2]);

                    int firstIndex = paintings.indexOf(firstPainting);
                    int secondIndex = paintings.indexOf(secondPainting);

                    if(paintings.contains(firstPainting) && paintings.contains(secondPainting)) {
                        paintings.set(firstIndex, secondPainting);
                        paintings.set(secondIndex, firstPainting);
                    }

                    break;
                case "Insert":

                    int index = Integer.parseInt(data[1]);
                    int paintingNumber = Integer.parseInt(data[2]);

                    if (index <= paintings.size()-1) {
                        paintings.add(index + 1, paintingNumber);
                    }

                    break;
                case "Reverse":

                    Collections.reverse(paintings);

                    break;
            }
        }

        for (int i = 0; i < paintings.size(); i++) {
            System.out.print(paintings.get(i) + " ");
        }
    }

}
