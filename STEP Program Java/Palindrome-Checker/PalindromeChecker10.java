// UC11: Object-Oriented Palindrome Service

import java.util.Scanner;
import java.util.Stack;

public class PalindromeChecker10 {
    public boolean checkPalindrome(String input) {
        
        if (input == null) {
            return false;
        }
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
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PalindromeChecker10 checker = new PalindromeChecker10();

        System.out.print("Input text: ");
        String input = scanner.nextLine();

        boolean result = checker.checkPalindrome(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true (ignoring case & spaces)");
        } else {
            System.out.print(" false");
        }

        scanner.close();
    }
}