package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger.log("Starting Ticket Management System...");

        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            try {
                Configuration configuration = new Configuration();
                configuration.setup();
                TicketPool ticketPool = new TicketPool(configuration);

                Logger.log("Configuration set: " + configuration);
                System.out.println("Start the system? (yes/no)");
                String userResponse = scanner.nextLine().trim().toLowerCase();

                if (userResponse.equals("yes")) {
                    Thread vendorThread = new Thread(new Vendor(ticketPool, configuration));
                    Thread customerThread = new Thread(new Customer(ticketPool, configuration));

                    vendorThread.start();
                    customerThread.start();

                    vendorThread.join();
                    customerThread.join();

                    Logger.log("All tickets processed.");

                    System.out.println("All tickets processed. Stop the system? (yes/no)");
                    userResponse = scanner.nextLine().trim().toLowerCase();

                    if (userResponse.equals("yes")) {
                        keepRunning = false;
                        Logger.log("System stopped by user.");
                    } else {
                        Logger.log("System restarting with new configuration...");
                    }
                } else {
                    Logger.log("System not started. Exiting...");
                    keepRunning = false;
                }
            } catch (Exception e) {
                Logger.logError("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        Logger.log("Exiting Ticket Management System...");
        System.out.println("System terminated.");
    }
}

/*
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setup();

        while (true) {
            System.out.println("System ready. Type 'start' to run or 'exit' to quit.");
            String command = configuration.getScanner().next().trim().toLowerCase();

            if ("exit".equals(command)) {
                System.out.println("Exiting system...");
                Logger.log("System exited by user.");
                break;
            } else if ("start".equals(command)) {
                TicketPool ticketPool = new TicketPool(configuration);
                Thread vendorThread = new Thread(new Vendor(ticketPool, configuration));
                Thread customerThread = new Thread(new Customer(ticketPool, configuration));

                vendorThread.start();
                customerThread.start();

                try {
                    vendorThread.join();
                    customerThread.join();
                } catch (InterruptedException e) {
                    Logger.logError("System interrupted: " + e.getMessage());
                }

                System.out.println("Processing complete. Restart? (yes/no):");
                if ("no".equalsIgnoreCase(configuration.getScanner().next().trim())) {
                    System.out.println("Shutting down...");
                    Logger.log("System stopped by user.");
                    break;
                }
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }
    }
}
*/
