import {createContext, useState, useMemo} from "react";
import {createTheme} from "@mui/material/styles";

//color design tokens export
// @param mode - "light" or "dark"
export const tokens = (mode) => ({
    ...(mode === "dark"
        ? {
            grey: {
                100: "#e0e0e0",
                200: "#c2c2c2",
                300: "#a3a3a3",
                400: "#858585",
                500: "#666666",
                600: "#525252",
                700: "#3d3d3d",
                800: "#292929",
                900: "#141414",
            },
            primary: {
                100: "#d0d9ff",
                200: "#a1b3ff",
                300: "#7394ff",
                400: "#4476ff",
                500: "#1558ff",
                600: "#1248cc",
                700: "#103999",
                800: "#0d297f",
                900: "#091a66",
            },
            greenAccent: {
                100: "#dbf3ee",
                200: "#b5e4de",
                300: "#8ed5ce",
                400: "#68c6be",
                500: "#43b7ae",
                600: "#349493",
                700: "#26797a",
                800: "#195e60",
                900: "#0c4247",
            },
        }
        : {
            grey: {
                100: "#141414",
                200: "#292929",
                300: "#3d3d3d",
                400: "#525252",
                500: "#666666",
                600: "#858585",
                700: "#a3a3a3",
                800: "#c2c2c2",
                900: "#e0e0e0",
            },
            primary: {
                100: "#091a66",
                200: "#103999",
                300: "#1248cc",
                400: "#1558ff",
                500: "#4476ff",
                600: "#7394ff",
                700: "#a1b3ff",
                800: "#d0d9ff",
                900: "#ffffff", // changed from #ffffff to #ffffff
            },
            greenAccent: {
                100: "#0c4247",
                200: "#195e60",
                300: "#26797a",
                400: "#349493",
                500: "#43b7ae",
                600: "#68c6be",
                700: "#8ed5ce",
                800: "#b5e4de",
                900: "#dbf3ee",
            },
        }),
});


//mui theme settings
// @param mode - "light" or "dark"
//@returns {object} - theme settings
// This function creates a theme object based on the provided mode (light or dark).
export const themeSettings = (mode) => {
    const colors = tokens(mode);

    return{
        palette: {
            mode: mode,
            ...(mode === "dark"
                ? {
                    // palette values for dark mode
                    primary: {
                        main: colors.primary[500],
                    },
                    secondary: {
                        main: colors.greenAccent[500],
                    },
                    neutral: {
                        dark: colors.grey[700],
                        main: colors.grey[500],
                        light: colors.grey[100],
                    },
                    background: {
                        default: colors.primary[900],
                    },
                }
                : {
                    // palette values for light mode
                    primary: {
                        main: colors.primary[100],
                    },
                    secondary: {
                        main: colors.greenAccent[500],
                    },
                    neutral: {
                        dark: colors.grey[700],
                        main: colors.grey[500],
                        light: colors.grey[100],
                    },
                    background: {
                        default: colors.primary[600],
                    },
                }),
        },
        //set up the typography
        typography: {
            fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
            fontSize: 12,
            h1: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 40,
            },
            h2: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 32,
            },
            h3: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 24,
            },
            h4: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 20,
            },
            h5: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 16,
            },
            h6: {
                fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
                fontSize: 14,
            },
        },
    };
};

//context for color mode -  allows us to toggle between light and dark mode
// This context will be used to provide the color mode to the entire application
export const ColorModeContext = createContext({
    toggleColorMode: () => {},
});

//custom hook to use the color mode context
// This hook will be used to access the color mode and the function to toggle it
export const useMode = () => {
    const [mode, setMode] = useState("dark");// default mode is dark
    const colorMode = useMemo(
        () => ({
            toggleColorMode: () =>
                setMode((prev) => (prev === "light" ? "dark" : "light")),
        }),
        []
    );

    const theme = useMemo(() => createTheme(themeSettings(mode)), [mode]);// create theme based on the mode

    return [theme, colorMode];
}
