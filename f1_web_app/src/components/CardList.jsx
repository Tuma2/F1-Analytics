import React, {useEffect, useState} from "react";
import Card from "./Card";
import {Grid} from "@mui/material";

// Example driver data array
// const driversX = [
//     { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
//     { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
//     { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
//     { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
//     { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
//     { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
//     { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
//     { name: "Lewis Hamilton", team: "Mercedes", points: 350 },
//     { name: "Max Verstappen", team: "Red Bull Racing", points: 400 },
//     { name: "Lewis Hamilton", team: "Mercedes", points: 350 }
//     // Add more drivers as needed
// ];

const CardList = ({onSelectDriver }) => {
    const [driverData, setDriverData] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/drivers/refresh")
            .then(res => res.json())
            .then(data => {
                console.log('Fetched driver data:', data);
                setDriverData(data);
            }).catch((err) => {
                console.log("error fetching drivers",err);
        });
    }, []);

    return (
        <Grid container spacing={2}>
            {Array.isArray(driverData) && driverData.map((driver) => (
                <Grid
                    item
                    xs={12}
                    sm={6}
                    md={4}
                    lg={3}
                    key={driver.id || driver.name}
                    onClick={() => onSelectDriver && onSelectDriver(driver)}
                >
                    <Card driver={driver} />
                </Grid>
            ))}
        </Grid>
    );

}

// <Grid container spacing={2}>
//     {drivers.map((driver, idx) => (
//         <Grid item xs={driversX.length} sm={6} md={4} lg={3} key={idx} onClick={() => onSelectDriver && onSelectDriver(driver)}>
//             <Card driver={driver} />
//         </Grid>
//     ))}
// </Grid>

export default CardList;
