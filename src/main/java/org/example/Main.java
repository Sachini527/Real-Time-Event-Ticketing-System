package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Welcome to the Real-Time Ticket Management System!");

            Configuration config = new Configuration();

            System.out.print("\nLoad configuration from file? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes") && config.loadConfigurationFromFile()) {
                Logger.info("Using configuration from file.");
            } else {
                config.loadConfiguration(scanner);
            }

            TicketPool ticketPool = new TicketPool(config.getMaxCapacity());
            Logger.enableLogging(); // Reset logging for each system run

            Thread vendorThread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
            Thread customerThread = new Thread(new Customer(ticketPool, config.getTicketRetrievalRate()));

            vendorThread.start();
            customerThread.start();

            System.out.println("System is running. Press Enter to terminate the system.");
            scanner.nextLine();

            vendorThread.interrupt();
            customerThread.interrupt();

            try {
                vendorThread.join();
                customerThread.join();
            } catch (InterruptedException e) {
                Logger.error("Error while stopping threads: " + e.getMessage());
            }

            Logger.info("System terminated.");

            System.out.print("\nDo you want to restart the system? (yes/no): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("yes"));

        System.out.println("\nThank you for using our service. Have a nice day!");
    }
}


