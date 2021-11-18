import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\|(?<bossName>[A-Z]{4,})\\|:#(?<title>[A-Za-z]+ [A-Za-z]+)#");
        int numberOfInputs = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfInputs; i++) {
            String input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String bossName = matcher.group("bossName");
                String title = matcher.group("title");
                System.out.printf("%s, The %s%n", bossName, title);
                System.out.printf(">> Strength: %d%n", bossName.length());
                System.out.printf(">> Armor: %d%n", title.length());
            } else {
                System.out.println("Access denied!");
            }
        }
    }
}
