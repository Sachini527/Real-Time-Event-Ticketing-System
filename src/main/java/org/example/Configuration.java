package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.*;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int ticketRetrievalRate;
    private int maxCapacity;

    public void loadConfiguration(Scanner scanner) {
        System.out.println("Starting new configuration setup...");

        while (true) {
            try {
                System.out.print("\nEnter total tickets (must be greater than 0): ");
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets <= 0) throw new IllegalArgumentException("Total tickets must be greater than 0.");

                System.out.print("Enter ticket release rate (must be greater than 0): ");
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate <= 0) throw new IllegalArgumentException("Ticket release rate must be greater than 0.");

                System.out.print("Enter customer retrieval rate (must be greater than 0): ");
                ticketRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (ticketRetrievalRate <= 0) throw new IllegalArgumentException("Customer retrieval rate must be greater than 0.");

                System.out.print("Enter max ticket capacity (must be greater than total tickets): ");
                maxCapacity = Integer.parseInt(scanner.nextLine());
                if (maxCapacity <= totalTickets) throw new IllegalArgumentException("Max capacity must be greater than total tickets.");

                System.out.println("Configuration set successfully.");
                saveConfiguration();
                break;
            } catch (IllegalArgumentException e) {
                Logger.error("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                Logger.error("Unexpected error: " + e.getMessage());
            }
        }
    }

//    public void saveConfiguration() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
//            writer.write(totalTickets + "\n");
//            writer.write(ticketReleaseRate + "\n");
//            writer.write(ticketRetrievalRate + "\n");
//            writer.write(maxCapacity + "\n");
//            Logger.info("Configuration saved successfully to config.txt.");
//        } catch (IOException e) {
//            Logger.error("Failed to save configuration: " + e.getMessage());
//        }
//    }
//
//    public boolean loadConfigurationFromFile() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
//            totalTickets = Integer.parseInt(reader.readLine());
//            ticketReleaseRate = Integer.parseInt(reader.readLine());
//            ticketRetrievalRate = Integer.parseInt(reader.readLine());
//            maxCapacity = Integer.parseInt(reader.readLine());
//            Logger.info("Configuration loaded successfully from config.txt.");
//            return true;
//        } catch (IOException | NumberFormatException e) {
//            Logger.error("Failed to load configuration: " + e.getMessage());
//            return false;
//        }
//    }

    private static final String CONFIG_FILE = "config.json";

    public void saveConfiguration() {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(this, writer);
            Logger.info("Configuration saved successfully to " + CONFIG_FILE);
        } catch (IOException e) {
            Logger.error("Failed to save configuration: " + e.getMessage());
        }
    }

    public boolean loadConfigurationFromFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            Configuration config = gson.fromJson(reader, Configuration.class);
            this.totalTickets = config.totalTickets;
            this.ticketReleaseRate = config.ticketReleaseRate;
            this.ticketRetrievalRate = config.ticketRetrievalRate;
            this.maxCapacity = config.maxCapacity;
            Logger.info("Configuration loaded successfully from " + CONFIG_FILE);
            return true;
        } catch (IOException | JsonIOException e) {
            Logger.error("Failed to load configuration: " + e.getMessage());
            return false;
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}







