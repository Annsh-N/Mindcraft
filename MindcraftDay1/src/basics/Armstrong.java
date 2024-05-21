package basics;

import java.util.Scanner;

public class Armstrong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter a number:");
        int num = sc.nextInt();

        if (checkArmstrong(num)) {
            System.out.println(num + " is an Armstrong Number.");
        } else {
            System.out.println(num + " is not an Armstrong Number.");
        }
    }

    public static boolean checkArmstrong(int n) {
        int sum = 0;
        int original = n;

        while (n != 0) {
            sum += (n % 10) * (n % 10) * (n % 10);
            n = n/10 ;
        }

        return sum == original;
    }
}
