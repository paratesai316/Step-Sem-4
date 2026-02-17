// UC10: Case-Insensitive & Space-Ignored Palindrome

import java.util.Scanner;

public class PalindromeChecker9 {
    public static boolean isPalindromeIgnoreCaseAndSpace(String input) {

        String normalized = input.toLowerCase().replaceAll("\\s+", "");

        int left = 0;
        int right = normalized.length() - 1;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = sc.nextLine();

        boolean result = isPalindromeIgnoreCaseAndSpace(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.println(" true (ignoring case & spaces)");
        } else {
            System.out.println(" false");
        }
    }
}
