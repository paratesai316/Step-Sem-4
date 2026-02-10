import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem2 {
    private static final Map<String, AtomicInteger> inventory = new ConcurrentHashMap<>();
    private static final Map<String, LinkedHashMap<Integer, Integer>> waitingList =
            new ConcurrentHashMap<>();
    static {
        inventory.put("IPHONE15_256GB", new AtomicInteger(100));
        inventory.put("PS5", new AtomicInteger(50));
        inventory.put("AIRPODS_PRO", new AtomicInteger(30));
        waitingList.put("IPHONE15_256GB", new LinkedHashMap<>());
        waitingList.put("PS5", new LinkedHashMap<>());
        waitingList.put("AIRPODS_PRO", new LinkedHashMap<>());
    }
    public static int checkStock(String productId) {
        AtomicInteger stock = inventory.get(productId);
        return stock != null ? stock.get() : -1;
    }
    public static synchronized String purchaseItem(String productId, int userId) {
        AtomicInteger stock = inventory.get(productId);
        if (stock == null) {
            return "Product does not exist";
        }
        if (stock.get() > 0) {
            int remaining = stock.decrementAndGet();
            return "Success! Remaining stock: " + remaining;
        } else {
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            queue.put(userId, queue.size() + 1);
            return "Out of stock. Added to waiting list at position #" + queue.size();
        }
    }
    public static void showWaitingList(String productId) {
        LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
        if (queue == null || queue.isEmpty()) {
            System.out.println("Waiting list is empty");
            return;
        }
        System.out.println("Waiting List:");
        for (Map.Entry<Integer, Integer> entry : queue.entrySet()) {
            System.out.println("User ID: " + entry.getKey() +
                    " | Position: " + entry.getValue());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Flash Sale Inventory Manager ---");
            System.out.println("1. Check stock");
            System.out.println("2. Purchase item");
            System.out.println("3. View waiting list");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    String productCheck = scanner.nextLine();
                    int stock = checkStock(productCheck);
                    System.out.println(stock >= 0
                            ? stock + " units available"
                            : "Product not found");
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    String productBuy = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(purchaseItem(productBuy, userId));
                    break;
                case 3:
                    System.out.print("Enter product ID: ");
                    String productQueue = scanner.nextLine();
                    showWaitingList(productQueue);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
