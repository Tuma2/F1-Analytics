import React, {useContext} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import CardActionArea from '@mui/material/CardActionArea';
import {Box, useTheme} from "@mui/material";
import {ColorModeContext, tokens} from "../theme";

const InteractiveCard = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const colorMode = useContext(ColorModeContext);


    return(

        <Box sx={{ pl: 4 }}> {/* Adds left padding (theme spacing * 4) */}
            <Card sx={{ maxWidth: 345, backgroundColor: colors.primary[400] }}>
                {/* ...rest of your card */}
                <Card sx={{ maxWidth: 345, backgroundColor: colors.primary[400] }}>
                    <CardActionArea>
                        <CardMedia
                            component="img"
                            height="400"
                            image="https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"
                            alt="Placeholder Image"
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div" color={colors.grey[100]}>
                                Interactive Card
                            </Typography>
                            <Typography variant="body2" color={colors.grey[300]}>
                                This is an example of an interactive card using Material-UI.
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                </Card>
            </Card>
        </Box>

    )
}

export default InteractiveCard;
