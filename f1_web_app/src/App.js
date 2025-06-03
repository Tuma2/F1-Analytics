import { ColorModeContext, useMode } from "./theme";
import { CssBaseline, Grid, ThemeProvider } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/topbar";
import Dashboard from "./scenes/dashboard/index";
import Card from "./components/Card";
import Driver from "./pages/Driver";
import CardList from "./components/CardList";

function App() {
  const [theme, colorMode] = useMode();

  return (
      <ColorModeContext.Provider value={colorMode}>
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <main className={"content"}>
            <Topbar />
            <Routes>
              <Route
                  path="/"
                  element={
                    <>
                      <Dashboard />
                        <CardList />
                    </>
                  }
              />
              <Route path="/driver" element={<Driver />} />
            </Routes>
          </main>
        </ThemeProvider>
      </ColorModeContext.Provider>
  );
}

export default App;
