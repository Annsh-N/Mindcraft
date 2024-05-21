package basics;

import java.util.Scanner;

public class VarArgs {

    public static void main(String[] args) {
        System.out.println("Sum of n integers is: " + sum(1,2,3,4));
    }
    public static int sum(int ... nums) {
        int sum = 0;

        for (int n : nums) {
            sum += n;
        }

        return sum;
    }
}
