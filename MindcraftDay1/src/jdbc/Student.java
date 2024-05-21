package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Student {
    private static final String URL = "jdbc:mysql://localhost:3306/SchoolDB";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "rootroot"; // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert record");
            System.out.println("2. Update record");
            System.out.println("3. Delete record");
            System.out.println("4. Display particular record");
            System.out.println("5. Display all records");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertRecord();
                    break;
                case 2:
                    updateRecord();
                    break;
                case 3:
                    deleteRecord();
                    break;
                case 4:
                    displayParticularRecord();
                    break;
                case 5:
                    displayAllRecords();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void insertRecord() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Student (rollno, name, percentage) VALUES (?, ?, ?)")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter roll number: ");
            int rollno = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter percentage: ");
            float percentage = scanner.nextFloat();

            stmt.setInt(1, rollno);
            stmt.setString(2, name);
            stmt.setFloat(3, percentage);
            stmt.executeUpdate();
            System.out.println("Record inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateRecord() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Student SET name = ?, percentage = ? WHERE rollno = ?")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter roll number of the record to update: ");
            int rollno = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new percentage: ");
            float percentage = scanner.nextFloat();

            stmt.setString(1, name);
            stmt.setFloat(2, percentage);
            stmt.setInt(3, rollno);
            stmt.executeUpdate();
            System.out.println("Record updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecord() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Student WHERE rollno = ?")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter roll number of the record to delete: ");
            int rollno = scanner.nextInt();

            stmt.setInt(1, rollno);
            stmt.executeUpdate();
            System.out.println("Record deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayParticularRecord() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Student WHERE rollno = ?")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter roll number of the record to display: ");
            int rollno = scanner.nextInt();

            stmt.setInt(1, rollno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Roll No: " + rs.getInt("rollno"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Percentage: " + rs.getFloat("percentage"));
            } else {
                System.out.println("Record not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllRecords() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                System.out.println("Roll No: " + rs.getInt("rollno"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Percentage: " + rs.getFloat("percentage"));
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

