package MidExam;

import java.util.Scanner;

public class SpringVacationTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        double budget = Double.parseDouble(scanner.nextLine());
        int peopleCount = Integer.parseInt(scanner.nextLine());
        double fuelPerKm = Double.parseDouble(scanner.nextLine());
        double foodExpensesPerPerson = Double.parseDouble(scanner.nextLine());
        double hotelPrice = Double.parseDouble(scanner.nextLine());

        double foodExpenses = days * peopleCount * foodExpensesPerPerson;

        double hotelExpenses = days * peopleCount * hotelPrice;
        if (peopleCount > 10) {
            hotelExpenses = hotelExpenses - (hotelExpenses * 0.25);
        }

        double currentExpenses = 0;

        currentExpenses = hotelExpenses + foodExpenses;

        for (int i = 1; i <= days; i++) {
            if (currentExpenses > budget) {
          //      System.out.println("Not enough money to continue the trip");
                break;
            }

            double traveledDistance = Double.parseDouble(scanner.nextLine());

            double consumedFuelPerDay = traveledDistance * fuelPerKm;

            currentExpenses += consumedFuelPerDay;

            if (i % 3 == 0 || i % 5 == 0) {
                currentExpenses += (currentExpenses * 0.4);
            }
            if (i % 7 == 0) {
                currentExpenses -= (currentExpenses / peopleCount);
            }


        }

        if (budget >= currentExpenses) {
            System.out.printf("You have reached the destination. You have %.2f$ budget left.", budget - currentExpenses);
        } else {
            System.out.printf("Not enough money to continue the trip. You need %.2f$ more.", currentExpenses - budget);
        }
    }
}
