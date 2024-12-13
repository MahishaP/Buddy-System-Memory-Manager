package eex5563_miniProject_buddy_system;

import java.util.Scanner;

public class BuddySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the BuddySystemManager with total memory of 1024 KB
        BuddySystemManager manager = new BuddySystemManager(1024);

        while (true) {
            // Display the menu
            System.out.println("\nBuddy System Menu:");
            System.out.println("1. Add Job");
            System.out.println("2. Free Job");
            System.out.println("3. Display Memory State");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Job
                    System.out.print("Enter Job ID: ");
                    int jobId = scanner.nextInt();
                    System.out.print("Enter Job Size (KB): ");
                    int jobSize = scanner.nextInt();

                    // Input validation
                    if (jobSize <= 0) {
                        System.out.println("Error: Job size must be greater than 0.");
                    } else {
                        manager.allocateJob(new Job(jobId, jobSize));
                    }
                    break;

                case 2: // Free Job
                    System.out.print("Enter Job ID to Free: ");
                    int freeJobId = scanner.nextInt();
                    manager.freeJob(freeJobId);
                    break;

                case 3: // Display Memory State
                    manager.displayMemoryState();
                    break;

                case 4: // Exit
                    System.out.println("Exiting the Buddy System Program...");
                    scanner.close();
                    return;

                default: // Invalid Input
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}


