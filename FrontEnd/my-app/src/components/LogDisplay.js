import React from 'react';

const LogDisplay = ({ logs }) => {
    return (
        <div className="log-display">
            <h2>System Logs</h2>
            <table className="log-table">
                <thead>
                    <tr>
                        <th>Timestamp</th>
                        <th>Event</th>
                    </tr>
                </thead>
                <tbody>
                    {logs.map((log, index) => (
                        <tr key={index}>
                            <td>{log.timestamp}</td>
                            <td>{log.event}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default LogDisplay;