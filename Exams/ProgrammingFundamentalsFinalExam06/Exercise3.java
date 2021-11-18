import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> messagesSent = new LinkedHashMap<>();
        Map<String, Integer> messagesReceived = new LinkedHashMap<>();
        int maximumCapacity = Integer.parseInt(sc.nextLine());

        String command = sc.nextLine();
        while (!command.equals("Statistics")) {
            String[] commandArgs = command.split("=");
            String action = commandArgs[0];

            switch (action) {
                case "Add":
                    String usernameToAdd = commandArgs[1];
                    int sentMessages = Integer.parseInt(commandArgs[2]);
                    int receivedMessages = Integer.parseInt(commandArgs[3]);

                    messagesReceived.putIfAbsent(usernameToAdd, receivedMessages);
                    messagesSent.putIfAbsent(usernameToAdd, sentMessages);
                    break;
                case "Message":
                    String sender = commandArgs[1];
                    String receiver = commandArgs[2];

                    if (messagesReceived.containsKey(receiver) && messagesSent.containsKey(sender)) {
                        messagesReceived.put(receiver, messagesReceived.get(receiver) + 1);
                        messagesSent.put(sender, messagesSent.get(sender) + 1);

                        if (messagesSent.get(sender) + messagesReceived.get(sender) == maximumCapacity) {
                            messagesSent.remove(sender);
                            messagesReceived.remove(sender);
                            System.out.printf("%s reached the capacity!%n", sender);
                        }
                        if (messagesReceived.get(receiver) + messagesSent.get(receiver) == maximumCapacity) {
                            messagesSent.remove(receiver);
                            messagesReceived.remove(receiver);
                            System.out.printf("%s reached the capacity!%n", receiver);
                        }
                    }
                    break;
                case "Empty":
                    String username = commandArgs[1];

                    if (username.equals("All")) {
                        messagesReceived.clear();
                        messagesSent.clear();
                    } else {
                        if (messagesReceived.containsKey(username) && messagesSent.containsKey(username)) {
                            messagesReceived.remove(username);
                            messagesSent.remove(username);
                        }
                    }
                    break;
            }
            command = sc.nextLine();
        }
        System.out.printf("Users count: %d%n", messagesReceived.size());
        messagesReceived.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> System.out.printf("%s - %d%n", e.getKey(), e.getValue() + messagesSent.get(e.getKey())));
    }
}
