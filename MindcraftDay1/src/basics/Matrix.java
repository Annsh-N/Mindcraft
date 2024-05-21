package basics;

import java.util.Scanner;

public class Matrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] mat = new int[3][3];

        System.out.println("Please enter first element:");
        mat[0][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter second element:");
        mat[0][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter third element:");
        mat[0][2] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter fourth element:");
        mat[1][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter fifth element:");
        mat[1][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter sixth element:");
        mat[1][2] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter seventh element:");
        mat[2][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter eighth element:");
        mat[2][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter ninth element:");
        mat[2][2] = sc.nextInt();
        sc.nextLine();

        System.out.println("Matrix is:");
        display(mat);

        System.out.println();

        System.out.println("Transpose is:");
        transpose(mat);

        System.out.println();

        int[][] mat2 = new int[3][3];
        System.out.println("Enter another Matrix.");

        System.out.println("Please enter first element:");
        mat2[0][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter second element:");
        mat2[0][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter third element:");
        mat2[0][2] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter fourth element:");
        mat2[1][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter fifth element:");
        mat2[1][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter sixth element:");
        mat2[1][2] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter seventh element:");
        mat2[2][0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter eighth element:");
        mat2[2][1] = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter ninth element:");
        mat2[2][2] = sc.nextInt();
        sc.nextLine();

        System.out.println("Second matrix is:");
        display(mat2);

        System.out.println();

        System.out.println("Addition of both matrices is:");
        addition(mat, mat2);


    }

    public static void display(int[][] mat) {
        for (int[] row : mat) {
            for (int j : row) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void transpose(int[][] mat) {
        int[][] transpose = new int[mat[0].length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                transpose[j][i] = mat[i][j];
            }
        }

        display(transpose);
    }

    public static void addition(int[][] mat1, int[][] mat2) {
        int[][] sum = new int[mat1.length][mat1[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        display(sum);
    }
}
