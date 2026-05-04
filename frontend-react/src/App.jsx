import { useState } from "react";
import SignUp from "./components/SignUp";

function App() {
  const [page, setPage] = useState("signUp");

  return (
    <div>
      {page === "signUp" && <SignUp setPage={setPage} />}
    </div>
  );
}

export default App;