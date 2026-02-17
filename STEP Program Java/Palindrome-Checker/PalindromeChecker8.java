// UC9: Recursive Palindrome Checker

import java.util.Scanner;

public class PalindromeChecker8 {
    public static boolean isPalindromeRecursive(String input, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (input.charAt(start) != input.charAt(end)) {
            return false;
        }

        return isPalindromeRecursive(input, start + 1, end - 1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = scanner.nextLine();

        boolean result = isPalindromeRecursive(input, 0, input.length() - 1);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }

        scanner.close();
    }
}
