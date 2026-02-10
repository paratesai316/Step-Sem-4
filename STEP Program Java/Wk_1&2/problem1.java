import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem1 {
    private static final Map<String, Integer> usernameToUserId = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> attemptFrequency = new ConcurrentHashMap<>();
    private static int userIdCounter = 1;
    public static boolean checkAvailability(String username) {
        attemptFrequency
                .computeIfAbsent(username, k -> new AtomicInteger(0))
                .incrementAndGet();

        return !usernameToUserId.containsKey(username);
    }
    public static boolean registerUser(String username) {
        if (checkAvailability(username)) {
            usernameToUserId.put(username, userIdCounter++);
            return true;
        }
        return false;
    }
    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int suffix = 1;
        while (suggestions.size() < 3) {
            String candidate = username + suffix;
            if (!usernameToUserId.containsKey(candidate)) {
                suggestions.add(candidate);
            }
            suffix++;
        }
        String dotVersion = username.replace("_", ".");
        if (!usernameToUserId.containsKey(dotVersion)) {
            suggestions.add(dotVersion);
        }
        return suggestions;
    }
    public static String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;
        for (Map.Entry<String, AtomicInteger> entry : attemptFrequency.entrySet()) {
            if (entry.getValue().get() > maxAttempts) {
                maxAttempts = entry.getValue().get();
                mostAttempted = entry.getKey();
            }
        }
        return mostAttempted;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        registerUser("john_doe");
        registerUser("admin");
        while (true) {
            System.out.println("\n--- Username Availability System ---");
            System.out.println("1. Check availability");
            System.out.println("2. Register username");
            System.out.println("3. Suggest alternatives");
            System.out.println("4. Most attempted username");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String checkUser = scanner.nextLine();
                    System.out.println(checkAvailability(checkUser)
                            ? "Available"
                            : "Already taken");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String registerUser = scanner.nextLine();
                    System.out.println(registerUser(registerUser)
                            ? "Registration successful"
                            : "Username already taken");
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    String suggestUser = scanner.nextLine();
                    System.out.println("Suggestions: " + suggestAlternatives(suggestUser));
                    break;
                case 4:
                    System.out.println("Most attempted username: " + getMostAttempted());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

