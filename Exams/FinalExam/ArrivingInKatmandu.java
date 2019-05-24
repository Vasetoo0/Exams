package FinalExam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrivingInKatmandu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;

        Pattern pattern = Pattern.compile("^(?<nameOfPeak>[A-Za-z0-9!@#$?]+)=(?<lengthOfGeoHash>[0-9]+)<<(?<geoHashCode>.+)$");

        while (!"Last note".equals(input = scanner.nextLine())) {

            Matcher matcher = pattern.matcher(input);

            if(matcher.find()) {
                if(matcher.group("geoHashCode").length() == Integer.parseInt(matcher.group("lengthOfGeoHash"))) {
                    String peakName = "";

                    for (int i = 0; i < matcher.group("nameOfPeak").length(); i++) {
                        if(Character.isLetter(matcher.group("nameOfPeak").charAt(i)) ||
                                Character.isDigit(matcher.group("nameOfPeak").charAt(i))) {
                            peakName += matcher.group("nameOfPeak").charAt(i);
                        }
                    }
                    if(peakName.length() > 0) {

                        System.out.println(String.format("Coordinates found! %s -> %s",peakName, matcher.group("geoHashCode")));
                    } else {
                        System.out.println("Nothing found!");
                    }
                } else {
                    System.out.println("Nothing found!");
                }
            } else {
                System.out.println("Nothing found!");
            }
        }
    }
}
