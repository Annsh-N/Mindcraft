package multithreading;
import java.awt.*;

public class Strings extends Frame implements Runnable{
	private int x1, x2;
	private Thread t1, t2;
	
	public Strings() {     
		x1 = 0;
		x2 = 285;
		
		t1 = new Thread(this, "s1");
		t2 = new Thread(this, "s2");
		
		t1.start();
		t2.start();
	}
	
	public void paint(Graphics g) {
		g.drawString("Mindcraft", x1, 100);
		g.drawString("Software", x2, 200);
	}
	
	public void run() {
		boolean s1Forward = true;
		boolean s2Backward = true;
		while(true) {
			if (Thread.currentThread() == t1) {
				synchronized(this) {
					if (s1Forward) {
						x1++;
					}
					else {
						x1--;
					}
					if (x1 == this.getWidth() - 60) {
						s1Forward = false;
						x1--;
					}
					if (x1 == 0) {
						s1Forward = true;
						x1++;
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (Thread.currentThread() == t2) {
				synchronized(this) {
					if (s2Backward) {
						x2--;
					}
					else {
						x2++;
					}
					if (x2 == 0) {
						s2Backward = false;
						x2++;
					}
					if (x2 == this.getWidth() - 50) {
						s2Backward = true;
						x2--;
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			repaint();
		}
	}
	
	public static void main(String[] args) {
		Strings s = new Strings();
		s.setSize(300, 300);
		s.setVisible(true);
	}
	
}
