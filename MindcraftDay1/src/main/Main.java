package main;
import com.mindcraft.pack1.*;
import com.mindcraft.pack2.*;

public class Main {
	
	public static void main(String[] args) {
		Student student = new Student("Annsh", 71);
		Batch batch = new Batch("Physics", 30);
		
		student.display();
		batch.display();
	}

}
