import React, { useState } from 'react';

const ConfigurationForm = ({ onSubmit }) => {
    const [totalTickets, setTotalTickets] = useState(0);
    const [ticketReleaseRate, setTicketReleaseRate] = useState(0);
    const [customerRetrievalRate, setCustomerRetrievalRate] = useState(0);
    const [maxTicketCapacity, setMaxTicketCapacity] = useState(0);

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({ 
            totalTickets, 
            ticketReleaseRate, 
            customerRetrievalRate, 
            maxTicketCapacity 
        });
    };

    return (
        <form className="configuration-form" onSubmit={handleSubmit}>
            <h2>Ticket System Configuration</h2>
            <div className="form-group">
                <label>Total Number of Tickets:</label>
                <input 
                    type="number" 
                    className="form-input"
                    value={totalTickets}
                    onChange={(e) => setTotalTickets(parseInt(e.target.value))}
                />
            </div>
            <div className="form-group">
                <label>Ticket Release Rate:</label>
                <input 
                    type="number" 
                    className="form-input"
                    value={ticketReleaseRate}
                    onChange={(e) => setTicketReleaseRate(parseInt(e.target.value))}
                />
            </div>
            <div className="form-group">
                <label>Customer Retrieval Rate:</label>
                <input 
                    type="number" 
                    className="form-input"
                    value={customerRetrievalRate}
                    onChange={(e) => setCustomerRetrievalRate(parseInt(e.target.value))}
                />
            </div>
            <div className="form-group">
                <label>Maximum Ticket Capacity:</label>
                <input 
                    type="number" 
                    className="form-input"
                    value={maxTicketCapacity}
                    onChange={(e) => setMaxTicketCapacity(parseInt(e.target.value))}
                />
            </div>
            <button type="submit" className="submit-btn">Submit</button>
        </form>
    );
};

export default ConfigurationForm;