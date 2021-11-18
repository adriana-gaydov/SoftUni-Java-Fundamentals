package ProgrammingFundamentalsFinalExam04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String barcode = sc.nextLine();
            Matcher matcher = Pattern.compile("@#+(?<productGroup>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+").matcher(barcode);

            if (matcher.find()) {
                String productGroup = matcher.group("productGroup");
                productGroup = getProductGroup2(productGroup);
                System.out.printf("Product group: %s%n", productGroup);
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }

    private static String getProductGroup(String productGroup) {
        StringBuilder result = new StringBuilder();
        for (char c : productGroup.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            }
        }
        if (result.toString().equals("")) return "00";
        return result.toString();
    }

    private static String getProductGroup2(String productGroup) {
        Matcher m = Pattern.compile("\\d").matcher(productGroup);
        StringBuilder result = new StringBuilder();

        while (m.find()) {
            result.append(m.group());
        }
        if (result.toString().equals("")) return "00";
        return result.toString();
    }
}
