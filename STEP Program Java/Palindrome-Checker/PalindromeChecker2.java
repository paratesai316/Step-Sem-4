// UC3: Palindrome Check Using String Reverse

import java.util.Scanner;

public class PalindromeChecker2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Input text: ");
        String original = sc.nextLine();

        String reversed = "";

        for (int i = original.length() - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }

        System.out.println("Is it a Palindrome: ");
        if (original.equals(reversed)) {
            System.out.print(" true");
        } else {
            System.out.print(" false");
        }
    }
}
