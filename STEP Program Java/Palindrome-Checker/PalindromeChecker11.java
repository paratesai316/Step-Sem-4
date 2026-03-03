import java.util.*;

public class PalindromeChecker11 {
    interface PalindromeStrategy {
        boolean check(String input);
    }

    static class StackStrategy implements PalindromeStrategy {
        public boolean check(String input) {

            String cleaned = input.replaceAll("\\s+", "").toLowerCase();
            Stack<Character> stack = new Stack<>();

            for (char ch : cleaned.toCharArray()) {
                stack.push(ch);
            }

            for (char ch : cleaned.toCharArray()) {
                if (ch != stack.pop()) {
                    return false;
                }
            }

            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean check(String input) {

            String cleaned = input.replaceAll("\\s+", "").toLowerCase();
            Deque<Character> deque = new ArrayDeque<>();

            for (char ch : cleaned.toCharArray()) {
                deque.addLast(ch);
            }

            while (deque.size() > 1) {
                if (!deque.removeFirst().equals(deque.removeLast())) {
                    return false;
                }
            }

            return true;
        }
    }

    private static PalindromeStrategy chooseStrategy(String input) {

        String cleaned = input.replaceAll("\\s+", "");

        if (cleaned.length() < 20) {
            return new StackStrategy();
        } else {
            return new DequeStrategy();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = scanner.nextLine();

        PalindromeStrategy strategy = chooseStrategy(input);

        boolean result = strategy.check(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true (ignoring case & spaces)");
        } else {
            System.out.print(" false");
        }

        scanner.close();
    }
}