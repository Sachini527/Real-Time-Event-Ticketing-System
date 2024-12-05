package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    // Define the log file name
    private static final String LOG_FILE = "log.txt";

    public static void log(String message) {
        // Create a log message with the current date and time
        String logMessage = String.format("%s: %s\n", java.time.LocalDateTime.now(), message);
        System.out.println(logMessage);
        // Write the log message to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
