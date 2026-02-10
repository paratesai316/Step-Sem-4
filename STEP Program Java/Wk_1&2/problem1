import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class problem1 {
    private static final ConcurrentHashMap<String, Integer> userMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> attemptFrequency = new ConcurrentHashMap<>();
    private static final AtomicInteger userIdGenerator = new AtomicInteger(1);
    public static boolean checkAvailability(String username) {
        recordAttempt(username);
        return !userMap.containsKey(username);
    }
    public static boolean registerUsername(String username) {
        recordAttempt(username);
        return userMap.putIfAbsent(username, userIdGenerator.getAndIncrement()) == null;
    }
    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        if (!userMap.containsKey(username)) {
            return suggestions;
        }
        int count = 1;
        while (suggestions.size() < 3) {
            String suggestion1 = username + count;
            String suggestion2 = username.replace("_", ".");
            String suggestion3 = username + "_" + count;
            if (!userMap.containsKey(suggestion1)) {
                suggestions.add(suggestion1);
            }
            if (suggestions.size() < 3 && !userMap.containsKey(suggestion2)) {
                suggestions.add(suggestion2);
            }
            if (suggestions.size() < 3 && !userMap.containsKey(suggestion3)) {
                suggestions.add(suggestion3);
            }
            count++;
        }
        return suggestions;
    }
    private static void recordAttempt(String username) {
        attemptFrequency
                .computeIfAbsent(username, k -> new AtomicInteger(0))
                .incrementAndGet();
    }
    public static String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;
        for (Map.Entry<String, AtomicInteger> entry : attemptFrequency.entrySet()) {
            int attempts = entry.getValue().get();
            if (attempts > maxAttempts) {
                maxAttempts = attempts;
                mostAttempted = entry.getKey();
            }
        }
        return mostAttempted;
    }
    public static void main(String[] args) {
        registerUsername("john_doe");
        registerUsername("admin");
        System.out.println(checkAvailability("john_doe"));      
        System.out.println(checkAvailability("jane_smith"));    
        System.out.println(suggestAlternatives("john_doe"));
        for (int i = 0; i < 10543; i++) {
            checkAvailability("admin");
        }
        System.out.println(getMostAttempted()); 
    }
}
