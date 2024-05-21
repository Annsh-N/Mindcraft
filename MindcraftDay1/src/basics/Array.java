package basics;

import java.util.Scanner;

public class Array {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        System.out.println("Please enter 5 integers: ");
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
            sc.nextLine();
        }

        System.out.println();


        System.out.println("Maximum element is : " + maxArr(arr));
        System.out.println("Minimum element is : " + minArr(arr));
        System.out.println("Array multiplied by 5 is:");
        mult5(arr);
    }

    public static int maxArr(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static int minArr(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }

        return min;
    }

    public static void mult5(int[] arr) {
        int[] arr2 = new int[arr.length];

        for (int i= 0; i < arr.length; i ++) {
            arr2[i] = arr[i] * 5;
        }

        for (int i= 0; i < arr2.length; i ++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
