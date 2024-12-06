package config;

import java.io.*;
import java.util.Scanner;

import logging.Logger;

public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return this.totalTickets;
    }

    public int getTicketReleaseRate() {
        return this.ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return this.customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return this.maxTicketCapacity;
    }

    // Method to prompt the user for configuration parameters
    public static Configuration promptForConfiguration() {
        Scanner input = new Scanner(System.in);
        // Call the log method from the Logger class
        Logger.log("Starting configuration prompt...");
        // prompt the user for configuration parameters
        int totalTickets = promptForInt(input, "Enter total number of tickets: ");
        int ticketReleaseRate = promptForInt(input, "Enter ticket release rate: ");
        int customerRetrievalRate = promptForInt(input, "Enter customer retrieval rate: ");
        int maxTicketCapacity = promptForInt(input, "Enter maximum ticket capacity: ");

        // Call the log method from the Logger class
        Logger.log("Configuration prompt completed.");
        // return a new config.Configuration object with the user's input
        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    // method to prompt for an integer and validate the input
    private static int promptForInt(Scanner input, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                value = input.nextInt();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive value.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // clear the invalid input
            }
        }
        return value;
    }

    // method to load the configuration from a text file
    public static Configuration loadFromTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // read the configuration parameters from the file
            int totalTickets = Integer.parseInt(reader.readLine().split("=")[1]);
            int ticketReleaseRate = Integer.parseInt(reader.readLine().split("=")[1]);
            int customerRetrievalRate = Integer.parseInt(reader.readLine().split("=")[1]);
            int maxTicketCapacity = Integer.parseInt(reader.readLine().split("=")[1]);
            // return a new config.Configuration object with the loaded parameters
            return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        }
    }

    // method to save the configuration to a text file
    public void saveToTextFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("totalTickets=" + totalTickets + "\n");
            writer.write("ticketReleaseRate=" + ticketReleaseRate + "\n");
            writer.write("customerRetrievalRate=" + customerRetrievalRate + "\n");
            writer.write("maxTicketCapacity=" + maxTicketCapacity + "\n");
        }
    }
}
