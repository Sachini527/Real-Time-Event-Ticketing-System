package org.example;

import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrieveRate;
    private int maxTicketCapacity;
    private final Scanner scanner;

    public Configuration() {
        this.scanner = new Scanner(System.in);
    }

    public void setup() {
        System.out.println("Welcome to Real Time Event Ticketing System...");
        System.out.println("\nSystem Configuration:");
        totalTickets = getIntInput("Enter total number of tickets: ");
        ticketReleaseRate = getIntInput("Enter ticket release rate (tickets/sec): ");
        customerRetrieveRate = getIntInput("Enter customer retrieval rate (tickets/sec): ");
        maxTicketCapacity = getIntInput("Enter max ticket capacity: ");

        Logger.log("Configuration set: TotalTickets=" + totalTickets +
                ", TicketReleaseRate=" + ticketReleaseRate +
                ", CustomerRetrieveRate=" + customerRetrieveRate +
                ", MaxTicketCapacity=" + maxTicketCapacity);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrieveRate() {
        return customerRetrieveRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public Scanner getScanner() {
        return scanner;
    }
}

/*
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrieveRate;
    private int maxTicketCapacity;
    private final Scanner scanner;

    public Configuration() {
        this.scanner = new Scanner(System.in);
    }

    public void setup() {
        System.out.println("Welcome to Real Time Event Ticketing System...");
        System.out.println("\nSystem Configuration:");
        totalTickets = getIntInput("Enter total number of tickets: ");
        ticketReleaseRate = getIntInput("Enter ticket release rate (tickets/sec): ");
        customerRetrieveRate = getIntInput("Enter customer retrieval rate (tickets/sec): ");
        maxTicketCapacity = getIntInput("Enter max ticket capacity: ");

        Logger.log("Configuration set: TotalTickets=" + totalTickets +
                ", TicketReleaseRate=" + ticketReleaseRate +
                ", CustomerRetrieveRate=" + customerRetrieveRate +
                ", MaxTicketCapacity=" + maxTicketCapacity);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrieveRate() {
        return customerRetrieveRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
*/

