package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private int availableTickets = 0;
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(Configuration config) {
        this.totalTickets = config.getTotalTickets();
        this.maxCapacity = config.getMaxTicketCapacity();
    }

    public boolean produceTickets(int ticketsToProduce) {
        lock.lock();
        try {
            if (totalTickets > 0 && availableTickets < maxCapacity) {
                int produced = Math.min(ticketsToProduce, totalTickets);
                availableTickets += produced;
                totalTickets -= produced;
                Logger.log("Produced " + produced + " tickets. Available: " + availableTickets);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean consumeTickets(int ticketsToConsume) {
        lock.lock();
        try {
            if (availableTickets > 0) {
                int consumed = Math.min(ticketsToConsume, availableTickets);
                availableTickets -= consumed;
                Logger.log("Consumed " + consumed + " tickets. Available: " + availableTickets);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isProcessingComplete() {
        lock.lock();
        try {
            return totalTickets == 0 && availableTickets == 0;
        } finally {
            lock.unlock();
        }
    }
}

/*
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private int availableTickets = 0;
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(Configuration config) {
        this.totalTickets = config.getTotalTickets();
        this.maxCapacity = config.getMaxTicketCapacity();
    }

    public boolean produceTickets(int ticketsToProduce) {
        lock.lock();
        try {
            if (totalTickets > 0 && availableTickets < maxCapacity) {
                int produced = Math.min(ticketsToProduce, totalTickets);
                availableTickets += produced;
                totalTickets -= produced;
                Logger.log("Produced " + produced + " tickets. Available: " + availableTickets);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean consumeTickets(int ticketsToConsume) {
        lock.lock();
        try {
            if (availableTickets > 0) {
                int consumed = Math.min(ticketsToConsume, availableTickets);
                availableTickets -= consumed;
                Logger.log("Consumed " + consumed + " tickets. Available: " + availableTickets);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isProcessingComplete() {
        lock.lock();
        try {
            return totalTickets == 0 && availableTickets == 0;
        } finally {
            lock.unlock();
        }
    }
}
*/
