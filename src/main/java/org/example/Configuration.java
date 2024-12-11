package org.example;

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
                System.out.print("Enter total tickets (must be greater than 0): ");
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets <= 0) throw new IllegalArgumentException();

                System.out.print("Enter ticket release rate (must be greater than 0): ");
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate <= 0) throw new IllegalArgumentException();

                System.out.print("Enter customer retrieval rate (must be greater than 0): ");
                ticketRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (ticketRetrievalRate <= 0) throw new IllegalArgumentException();

                System.out.print("Enter max ticket capacity (must be greater than total tickets): ");
                maxCapacity = Integer.parseInt(scanner.nextLine());
                if (maxCapacity <= 0 || maxCapacity <= totalTickets) throw new IllegalArgumentException();

                System.out.println("Configuration set successfully.");
                saveConfiguration();
                break;
            } catch (Exception e) {
                Logger.error("Invalid input. Please try again.");
            }
        }
    }

    public void saveConfiguration() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
            writer.write(totalTickets + "\n");
            writer.write(ticketReleaseRate + "\n");
            writer.write(ticketRetrievalRate + "\n");
            writer.write(maxCapacity + "\n");
            System.out.println("Configuration saved successfully to config.txt.");
        } catch (IOException e) {
            Logger.error("Failed to save configuration: " + e.getMessage());
        }
    }

    public boolean loadConfigurationFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            totalTickets = Integer.parseInt(reader.readLine());
            ticketReleaseRate = Integer.parseInt(reader.readLine());
            ticketRetrievalRate = Integer.parseInt(reader.readLine());
            maxCapacity = Integer.parseInt(reader.readLine());
            Logger.info("Configuration loaded successfully from config.txt.");
            return true;
        } catch (IOException | NumberFormatException e) {
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





