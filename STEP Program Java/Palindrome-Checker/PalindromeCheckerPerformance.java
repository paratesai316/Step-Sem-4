import java.util.*;

public class PalindromeChecker12 {

    public static boolean isPalindromeReverseLoop1(String word) {
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }
        return word.equals(reversed);
    }

    public static boolean isPalindromeReverseLoop2(String original) {
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }
        return original.equals(reversed);
    }

    public static boolean isPalindromeUsingCharArray(String input) {
        char[] characters = input.toCharArray();
        int left = 0, right = characters.length - 1;
        while (left < right) {
            if (characters[left] != characters[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeUsingStack(String input) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++)
            stack.push(input.charAt(i));
        for (int i = 0; i < input.length(); i++)
            if (input.charAt(i) != stack.pop()) return false;
        return true;
    }

    public static boolean isPalindromeUsingQueueStack(String input) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            stack.push(ch);
            queue.add(ch);
        }
        while (!stack.isEmpty())
            if (stack.pop() != queue.remove()) return false;
        return true;
    }

    public static boolean isPalindromeUsingDeque(String input) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++)
            deque.addLast(input.charAt(i));
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }

    static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    public static boolean isPalindromeUsingLinkedList(String input) {
        if (input == null || input.length() == 0) return true;

        Node head = new Node(input.charAt(0));
        Node current = head;

        for (int i = 1; i < input.length(); i++) {
            current.next = new Node(input.charAt(i));
            current = current.next;
        }

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow);
        Node firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private static Node reverse(Node head) {
        Node prev = null, current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    public static boolean isPalindromeRecursive(String input, int start, int end) {
        if (start >= end) return true;
        if (input.charAt(start) != input.charAt(end)) return false;
        return isPalindromeRecursive(input, start + 1, end - 1);
    }

    public static boolean isPalindromeIgnoreCaseAndSpace(String input) {
        String normalized = input.toLowerCase().replaceAll("\\s+", "");
        int left = 0, right = normalized.length() - 1;
        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean checkPalindrome(String input) {
        if (input == null) return false;
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char ch : cleaned.toCharArray()) stack.push(ch);
        for (char ch : cleaned.toCharArray())
            if (ch != stack.pop()) return false;
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input text: ");
        String input = scanner.nextLine();

        Map<String, Boolean> results = new LinkedHashMap<>();
        Map<String, Long> timings = new LinkedHashMap<>();

        String[] names = {
            "ReverseLoop1", "ReverseLoop2", "CharArray",
            "Stack", "QueueStack", "Deque",
            "LinkedList", "Recursive",
            "IgnoreCaseSpace", "CleanedStack"
        };

        for (String name : names) {

            long start = System.nanoTime();
            boolean result = false;

            switch (name) {
                case "ReverseLoop1":
                    result = isPalindromeReverseLoop1(input);
                    break;
                case "ReverseLoop2":
                    result = isPalindromeReverseLoop2(input);
                    break;
                case "CharArray":
                    result = isPalindromeUsingCharArray(input);
                    break;
                case "Stack":
                    result = isPalindromeUsingStack(input);
                    break;
                case "QueueStack":
                    result = isPalindromeUsingQueueStack(input);
                    break;
                case "Deque":
                    result = isPalindromeUsingDeque(input);
                    break;
                case "LinkedList":
                    result = isPalindromeUsingLinkedList(input);
                    break;
                case "Recursive":
                    result = isPalindromeRecursive(input, 0, input.length() - 1);
                    break;
                case "IgnoreCaseSpace":
                    result = isPalindromeIgnoreCaseAndSpace(input);
                    break;
                case "CleanedStack":
                    result = checkPalindrome(input);
                    break;
            }

            long end = System.nanoTime();
            long duration = end - start;

            results.put(name, result);
            timings.put(name, duration);
        }

        System.out.println("\nPerformance Results");

        System.out.printf("%-20s %-20s %-15s%n", "Algorithm", "Execution Time (ns)", "Result");
        System.out.println("_______________________________________________________________");

        for (String name : timings.keySet()) {
            System.out.printf("%-20s %-20d %-15s%n",
            name,
            timings.get(name),
            results.get(name));
}

        System.out.println("_______________________________________________________________");

        String fastest = Collections.min(timings.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("\nFastest Algorithm: " + fastest);

        scanner.close();
    }
}
