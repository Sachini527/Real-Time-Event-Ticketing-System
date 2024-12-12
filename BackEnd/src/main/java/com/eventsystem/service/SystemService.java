package com.eventsystem.service;

import com.eventsystem.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class SystemService {
    private TicketPool ticketPool;
    private Thread vendorThread;
    private Thread customerThread;
    private boolean isRunning = false;

    public void startSystem(int totalTickets, int maxCapacity, int ticketReleaseRate, int customerRetrievalRate) {
        ticketPool = new TicketPool(totalTickets, maxCapacity);
        isRunning = true;

        vendorThread = new Thread(() -> {
            while (isRunning) {
                if (!ticketPool.addTicket()) {
                    sleep(ticketReleaseRate);
                }
            }
        });

        customerThread = new Thread(() -> {
            while (isRunning) {
                if (!ticketPool.removeTicket()) {
                    sleep(customerRetrievalRate);
                }
            }
        });

        vendorThread.start();
        customerThread.start();
    }

    public void stopSystem() {
        isRunning = false;
        if (vendorThread != null) vendorThread.interrupt();
        if (customerThread != null) customerThread.interrupt();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
