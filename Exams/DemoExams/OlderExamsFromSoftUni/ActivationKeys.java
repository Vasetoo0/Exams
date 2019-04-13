package DemoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("&");

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{25}|[A-Za-z0-9]{16}$");

        List<String> activateKey = new ArrayList<>();

        for (String data : input) {
            String decryptedKey = "";

            Matcher matcher = pattern.matcher(data);

            if (matcher.find()) {
                if (matcher.group().length() == 16) {
                    String matched = matcher.group();
                    while (matched.length() > 0) {
                        if(decryptedKey.length() > 0) {
                            decryptedKey += "-";
                        }
                        for (int i = 0; i < 4; i++) {
                            if (Character.isLetter(matched.charAt(i))) {
                                decryptedKey += String.valueOf(matched.charAt(i)).toUpperCase();
                            } else if (Character.isDigit(matched.charAt(i))) {
                                decryptedKey += 9 - (Integer.parseInt(String.valueOf(matched.charAt(i))));
                            }
                        }
                        matched = matched.substring(4);
                    }

                } else {
                    String matched = matcher.group();
                    while (matched.length() > 0) {
                        if(decryptedKey.length() > 0) {
                            decryptedKey += "-";
                        }
                        for (int i = 0; i < 5; i++) {
                            if (Character.isLetter(matched.charAt(i))) {
                                decryptedKey += String.valueOf(matched.charAt(i)).toUpperCase();
                            } else if (Character.isDigit(matched.charAt(i))) {
                                decryptedKey += 9 - (Integer.parseInt(String.valueOf(matched.charAt(i))));
                            }
                        }
                        matched = matched.substring(5);
                    }
                }
            }
            if(decryptedKey.length() > 0) {

                activateKey.add(decryptedKey);
            }
        }
        System.out.println(String.join(", ", activateKey));
    }
}
