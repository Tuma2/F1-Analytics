import React, {useContext} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import CardActionArea from '@mui/material/CardActionArea';
import {Box, useTheme} from "@mui/material";
import {ColorModeContext, tokens} from "../theme";
import {useNavigate} from "react-router-dom";

const InteractiveCard = ({driver}) => {

    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const colorMode = useContext(ColorModeContext);
    const navigate = useNavigate();

    // Uncomment and improve this safety check

    if(!driver) {
        return (
            <Box sx={{ pl: 4 }}>
                <Card sx={{ maxWidth: 345, backgroundColor: colors.primary[400] }}>
                    <CardContent>
                        <Typography variant="h5" color={colors.grey[100]}>
                            Loading driver...
                        </Typography>
                    </CardContent>
                </Card>
            </Box>
        );
    }

    return(
        <Box sx={{ pl: 4 }}>
            <Card sx={{ maxWidth: 345, backgroundColor: colors.primary[400] }}>
                <CardActionArea onClick={() => navigate('/driver',{
                    state:{
                        name: driver.name,
                        team: driver.team,
                        points: driver.points,
                    }
                })}>
                    <CardMedia
                        component="img"
                        height="400"
                        image="https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"
                        alt={`${driver.name} F1 Driver`}
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div" color={colors.grey[100]}>
                            {driver.name}
                        </Typography>
                        <Typography variant="body2" color={colors.grey[300]}>
                            Team: {driver.team} | Points: {driver.points}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </Box>
    )
}

export default InteractiveCard;
