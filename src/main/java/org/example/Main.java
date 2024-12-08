package org.example;

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

