package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "logs.txt";

    private static boolean loggingEnabled = true;

    public static void enableLogging() {
        loggingEnabled = true;
    }

    public static void disableLogging() {
        loggingEnabled = false;
    }

    public static void info(String message) {
        if (loggingEnabled) {
            log("INFO: " + message);
        }
    }

    public static void warn(String message) {
        if (loggingEnabled) {
            log("WARN: " + message);
        }
    }

    public static void error(String message) {
        if (loggingEnabled) {
            log("ERROR: " + message);
        }
    }

    private static void log(String message) {
        String timeStampedMessage = LocalDateTime.now() + ": " + message;
        System.out.println("\u001B[31m" + timeStampedMessage + "\u001B[0m"); // ANSI escape code for red text
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timeStampedMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





