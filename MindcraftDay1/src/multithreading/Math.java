package multithreading;
import java.util.Scanner;

class t1 extends Thread {
	private int number;

    public t1(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Next 10 incremented values:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + i);
        }
    }
}

class t2 extends Thread {
	private int number;

    public t2(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Multiplication table:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}
public class Math {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		System.out.println("Enter a number: ");
		num = sc.nextInt();
		
		t1 one = new t1(num);
		t2 two = new t2(num);
		
		one.start();
		two.start();
	}
}
