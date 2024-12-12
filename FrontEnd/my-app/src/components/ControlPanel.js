import React from 'react';

const ControlPanel = ({ onStart, onStop, systemStatus }) => {
    return (
        <div className="control-panel">
            <h2>System Control</h2>
            <div className="system-status">
                Current Status: {systemStatus}
            </div>
            <button className="control-btn start-btn" onClick={onStart}>Start</button>
            <button className="control-btn stop-btn" onClick={onStop}>Stop</button>
        </div>
    );
};

export default ControlPanel;