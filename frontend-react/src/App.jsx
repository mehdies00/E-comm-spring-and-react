import { useState } from "react";
import Nav from "./components/Navbar/nav";
import SignIn from "./pages/SignIn/Login";
import SignUp from "./pages/SignUp/SignUp";
import Dashboard from "./pages/Dashboard/Dashboard";

function App() {
  const [user, setUser] = useState(
    localStorage.getItem("token") ? "user" : null
  );
  const [page, setPage] = useState("signin");

  const handleSuccess = (login) => setUser(login);

  const handleLogout = () => {
    localStorage.removeItem("token");
    setUser(null);
    setPage("signin");
  };

  return (
    <>
      <Nav
        user={user}
        onLoginClick={() => setPage("signin")}
        onLogout={handleLogout}
      />

      {user && <Dashboard />}

      {!user && page === "signin" && (
        <SignIn
          onSuccess={handleSuccess}
          onSignUpClick={() => setPage("signup")}
        />
      )}

      {!user && page === "signup" && (
        <SignUp
          onSuccess={handleSuccess}
          onLoginClick={() => setPage("signin")}
        />
      )}
    </>
  );
}

export default App;