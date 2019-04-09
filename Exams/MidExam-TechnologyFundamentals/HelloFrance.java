package MidExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloFrance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = scanner.nextLine().split("[|]");

        double budget = Double.parseDouble(scanner.nextLine());

        List<String> newPrices = new ArrayList<>();

        double profit = 0;
        double newPriceSum = 0;

        for (int i = 0; i < items.length; i++) {

            String[] data = items[i].split("->");

            String item = data[0];
            double itemPrice = Double.parseDouble(data[1]);


            switch (item) {
                case "Clothes":

                    if(itemPrice <= 50 && itemPrice <= budget) {

                        profit += ((itemPrice + (itemPrice * 0.40)) - itemPrice);
                        budget -= itemPrice;

                        itemPrice += (itemPrice * 0.40);

                        newPriceSum += itemPrice;

                        String formatNum = String.format("%.2f", itemPrice);

                        newPrices.add(formatNum);

                    }

                    break;
                case "Shoes":

                    if(itemPrice <= 35 && itemPrice <= budget) {

                        profit += ((itemPrice + (itemPrice * 0.40)) - itemPrice);
                        budget -= itemPrice;

                        itemPrice += (itemPrice * 0.40);
                        newPriceSum += itemPrice;

                        String formatNum = String.format("%.2f", itemPrice);

                        newPrices.add(formatNum);

                    }

                    break;
                case "Accessories":

                    if(itemPrice <= 20.50 && itemPrice <= budget) {

                        profit += ((itemPrice + (itemPrice * 0.40)) - itemPrice);
                        budget -= itemPrice;

                        itemPrice += (itemPrice * 0.40);
                        newPriceSum += itemPrice;

                        String formatNum = String.format("%.2f", itemPrice);

                        newPrices.add(formatNum);

                    }
                    break;
            }
        }

        for (int i = 0; i < newPrices.size(); i++) {
            System.out.print(newPrices.get(i) + " ");
        }
        System.out.println();

        System.out.printf("Profit: %.2f%n", profit);

        if(budget + newPriceSum >= 150 ) {
            System.out.println("Hello, France!");
        }else {
            System.out.println("Time to go.");
        }


    }
}
