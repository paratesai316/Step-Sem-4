// UC4: Character Array Based Palindrome Check

import java.util.Scanner;

public class PalindromeChecker3 {

    public static boolean isPalindromeUsingCharArray(String input) {

        char[] characters = input.toCharArray();

        int left = 0;
        int right = characters.length - 1;

        while (left < right) {

            if (characters[left] != characters[right]) {
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

        boolean result = isPalindromeUsingCharArray(input);

        System.out.println("Is it a Palindrome: ");
        if (result) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }
    }
}
