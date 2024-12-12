import React, { useState, useEffect } from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import TicketDisplay from './components/TicketDisplay';
import ControlPanel from './components/ControlPanel';
import LogDisplay from './components/LogDisplay';
import './App.css';

const App = () => {
  const [configuration, setConfiguration] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
  });
  const [availableTickets, setAvailableTickets] = useState(0);
  const [systemStatus, setSystemStatus] = useState('Stopped');
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    // Initialize the system with the provided configuration
    initializeSystem(configuration);
  }, [configuration]);

  const handleConfigurationSubmit = (config) => {
    setConfiguration(config);
  };

  const startSystem = () => {
    // Start the ticket vendor and customer threads
    startTicketOperations();
    setSystemStatus('Running');
  };

  const stopSystem = () => {
    // Stop the ticket vendor and customer threads
    stopTicketOperations();
    setSystemStatus('Stopped');
  };

  const initializeSystem = (config) => {
    // Initialize the system with the provided configuration
    // Set the initial available tickets
    setAvailableTickets(config.totalTickets);

    // Add initial log entry
    addLog(`System initialized with configuration: ${JSON.stringify(config)}`);
  };

  const startTicketOperations = () => {
    // Start the ticket vendor and customer threads
    // Update the available tickets and logs in real-time
  };

  const stopTicketOperations = () => {
    // Stop the ticket vendor and customer threads
    // Ensure all threads have been terminated
  };

  const addLog = (event) => {
    // Add a new log entry with the current timestamp
    setLogs((prevLogs) => [
      ...prevLogs,
      { timestamp: new Date().toLocaleString(), event },
    ]);
  };

  return (
    <div>
      <h1 className='title'>Real-Time Event Ticketing System</h1>
      <ConfigurationForm onSubmit={handleConfigurationSubmit} />
      <TicketDisplay availableTickets={availableTickets} />
      <ControlPanel
        onStart={startSystem}
        onStop={stopSystem}
        systemStatus={systemStatus}
      />
      <LogDisplay logs={logs} />
    </div>
  );
};

export default App;