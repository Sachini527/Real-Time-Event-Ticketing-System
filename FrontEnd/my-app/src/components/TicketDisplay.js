import React from 'react';

const TicketDisplay = ({ availableTickets }) => {
    return (
        <div className="ticket-display">
            <h2>Ticket Availability</h2>
            <div className="available-tickets">
                Available Tickets: {availableTickets}
            </div>
        </div>
    );
};

export default TicketDisplay;