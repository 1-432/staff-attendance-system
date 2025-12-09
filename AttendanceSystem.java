import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AttendanceSystem {
    private static final String FILE_NAME = "attendance_records.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("===== STAFF ATTENDANCE SYSTEM (Console Backend) =====");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Records");
        System.out.print("Choose an option: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 -> saveAttendance("Check In");
            case 2 -> saveAttendance("Check Out");
            case 3 -> viewRecords();
            default -> System.out.println("Invalid option!");
        }
    }

    private static void saveAttendance(String action) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            out.println(action + " at " + dateTime);
            System.out.println("Attendance recorded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void viewRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n--- Attendance Records ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No records found.");
        }
    }
}