package com.eventsystem.controller;

import com.eventsystem.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @PostMapping("/start")
    public String startSystem(@RequestParam int totalTickets,
                              @RequestParam int maxCapacity,
                              @RequestParam int ticketReleaseRate,
                              @RequestParam int customerRetrievalRate) {
        systemService.startSystem(totalTickets, maxCapacity, ticketReleaseRate, customerRetrievalRate);
        return "System started.";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        systemService.stopSystem();
        return "System stopped.";
    }
}

