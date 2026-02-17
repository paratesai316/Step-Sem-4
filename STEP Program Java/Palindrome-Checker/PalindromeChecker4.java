// UC5: Stack-Based Palindrome Checker

import java.util.Scanner;
import java.util.Stack;

public class PalindromeChecker4 {
    public static boolean isPalindromeUsingStack(String input) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = sc.nextLine();

        boolean result = isPalindromeUsingStack(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }
    }
}
