package org.example;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("TicketSystemLogger");

    static {
        try {
            FileHandler fh = new FileHandler("TicketSystem.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            System.out.println("Error initializing logger: " + e.getMessage());
        }
    }

    public static void log(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }
}

