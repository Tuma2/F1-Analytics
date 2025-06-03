import React, {useState} from 'react';
import {tokens} from "../theme";
import {useTheme} from "@mui/material";
import { useLocation } from "react-router-dom";
import CardList from "../components/CardList"; // Assuming you have a CardList component to display driver cards

const Driver = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const [driver, setDriver] = useState('home');
    const [selectedDriver, setSelectedDriver] = useState(null);

    const handleSelectDriver = (driverData) => {
        setSelectedDriver(driverData);
    };

    //Driver data state
    const location = useLocation();
    const driverInfo = location.state || { name: 'Default Driver', team: 'Default Team', points: 0 };


    return (
        <div style={{ padding: "20px", backgroundColor: colors.primary[400], color: colors.grey[100] }}>
            <h1>Driver Information</h1>
            {driverInfo ? (
                <div>
                    <h2>{driverInfo.name}</h2>
                    <p>Team: {driverInfo.team}</p>
                    <p>Points: {driverInfo.points}</p>
                </div>
            ) : (
                <CardList onSelectDriver={handleSelectDriver} />
            )}
        </div>
    );

}

export default Driver;// This component will be used to display the driver information
