import React, { useState} from 'react';
import {tokens} from "../theme";
import {Box, useTheme} from "@mui/material";
import { useLocation } from "react-router-dom";
import CardList from "../components/CardList";
import Divider from "@mui/material/Divider"; // Assuming you have a CardList component to display driver cards

const Driver = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    // const [driver, setDriver] = useState('home');
    const [selectedDriver, setSelectedDriver] = useState(null);

    const handleSelectDriver = (driverData) => {
        setSelectedDriver(driverData);
    };

    //Driver data state
    const location = useLocation();
    const driverInfo = location.state || { name: 'Default Driver', team: 'Default Team', points: 0 };


    return (
        <Box sx={{
            backgroundColor: colors.primary[500],
            color: colors.grey[100],
            minHeight: "90vh",
            padding: "20px",
            display: "flex",
            paddingLeft: "30vh",
            gap: "40px", // Add space between the two divs
        }}>
            <div style={{
                display: "flex",
                flexDirection: "column",
                // justifyContent: "center",
                padding: "60px",
                backgroundColor: colors.primary[400],
                color: colors.grey[100],
                minHeight: "70vh",
                width: "60vh",
                borderRadius: "18px",
                boxShadow: "0 4px 20px rgba(0,0,0,0.25)", // <-- added
            }}>
                <h1>Driver Information</h1>
                {driverInfo ? (
                    <>
                    <div>
                        <h1>Name: {driverInfo.name} {driverInfo.surname}</h1>
                        <h2>Team: {driverInfo.team}</h2>
                        <h3>Number: {driverInfo.driverNumber}</h3>
                        <h3>Country : {driverInfo.country}</h3>
                    </div>
                    <Divider style={{ margin: "20px 0" }} />

                    </>
                ) : (
                    <CardList onSelectDriver={handleSelectDriver} />
                )}
            </div>

            <div style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                padding: "80px",
                backgroundColor: colors.primary[400],
                color: colors.grey[100],
                minHeight: "70vh",
                width: "60vh",
                borderRadius: "18px",
                boxShadow: "0 4px 20px rgba(0,0,0,0.25)", // <-- added
            }}>
                <h1>Driver Video</h1>
                <video
                    width="100%"
                    height="auto"
                    controls
                    style={{
                        borderRadius: "12px",
                        background: "#000",
                        boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.5)",
                        border: "2px solid #fff",
                    }}
                >
                    <source src={"https://www.youtube.com/watch?v=9_qmWm9aefk&pp=ygUObWF4IHZlcnN0YXBwZW4%3D"} type="video/mp4" />
                    Your browser does not support the video tag.
                </video>
            </div>
        </Box>

    );

}

export default Driver;// This component will be used to display the driver information
