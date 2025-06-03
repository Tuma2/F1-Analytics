import React from "react";
import Card from "./Card";
import {Grid} from "@mui/material";

// Example driver data array
const driversX = [
    { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
    { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
    { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
    { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
    { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
    { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
    { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
    { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
    { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
    { name: "Lewis Hamilton", team: "Mercedes", points: 350 }
    // Add more drivers as needed
];

console.log('drivers:', driversX);
const CardList = ({ drivers = driversX, onSelectDriver }) => (
    <Grid container spacing={2}>
        {drivers.map((driver, idx) => (
            <Grid item xs={driversX.length} sm={6} md={4} lg={3} key={idx} onClick={() => onSelectDriver && onSelectDriver(driver)}>
                <Card driver={driver} />
            </Grid>
        ))}
    </Grid>
);

export default CardList;
