import React, {useEffect, useState} from "react";
import Card from "./Card";
import {Grid} from "@mui/material";

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

export default CardList;
