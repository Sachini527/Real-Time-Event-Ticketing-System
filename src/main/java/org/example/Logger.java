package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "logs.txt";
    private static boolean loggingEnabled = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";      // Default text color
    private static final String RED = "\u001B[31m";       // Red text color

    public static void enableLogging() {
        loggingEnabled = true;
    }

    public static void disableLogging() {
        loggingEnabled = false;
    }

    public static void info(String message) {
        if (loggingEnabled) {
            log("INFO: " + message, true);
        }
    }

    public static void warn(String message) {
        if (loggingEnabled) {
            log("WARN: " + message, true);
        }
    }

    public static void error(String message) {
        if (loggingEnabled) {
            log("ERROR: " + message, true);
        }
    }

    private static void log(String message, boolean colorize) {
        String timeStampedMessage = formatter.format(LocalDateTime.now()) + ": " + message;

        // Write the detailed log in red
        if (colorize) {
            System.out.println(RED + timeStampedMessage + RESET);
        } else {
            System.out.println(timeStampedMessage);
        }

        // Write the general info log without color
        System.out.println("[INFO]: " + message);

        // Log to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timeStampedMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








