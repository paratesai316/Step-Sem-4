// UC7: Deque-Based Optimized Palindrome Checker

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class PalindromeChecker6 {
    public static boolean isPalindromeUsingDeque(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            deque.addLast(input.charAt(i));
        }

        while (deque.size() > 1) {
            char front = deque.removeFirst();
            char rear = deque.removeLast();

            if (front != rear) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = sc.nextLine();

        boolean result = isPalindromeUsingDeque(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }
    }
}
