import {ColorModeContext, useMode } from "./theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes, Route} from "react-router-dom";
import Topbar from "./scenes/global/topbar";
import Dashboard from "./scenes/dashboard/index"; // Import the Dashboard component
// import Sidenav from "./scenes/global/Sidenav"; // Import the Sidebar component
// import Sidebar from "./scenes/global/sidebar";

function App() {
  const [theme, colorMode] = useMode(); // use the custom hook to get the theme and color mode

  return (
      <ColorModeContext.Provider value={colorMode}>
        <ThemeProvider theme={theme}>
          <CssBaseline /> {/* This component will apply the theme to the entire application */}
          <main className={"content"}>
            <Topbar /> {/* This component will be the top bar of the application */}
            {/*<Sidenav /> /!* This component will be the sidebar of the application *!/*/}
            {/* <Sidebar /> */}
            <Routes>
              <Route path="/" element={<Dashboard />} /> {/* This route will render the Dashboard component */}
              {/* Add more routes here as needed */}
            </Routes>

          </main>
        </ThemeProvider>
      </ColorModeContext.Provider>
  );
}

export default App;
