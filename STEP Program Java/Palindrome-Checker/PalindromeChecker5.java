// UC6: Queue + Stack Based Palindrome Check

import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class PalindromeChecker5 {
    public static boolean isPalindromeUsingQueueStack(String input) {

        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            stack.push(ch);
            queue.add(ch);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.remove()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input text: ");
        String input = sc.nextLine();

        boolean result = isPalindromeUsingQueueStack(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }
    }
}
