import { useState } from "react";

function SignUp({ setPage }) {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");

  const handleSignup = async () => {
    const user = {
      login,
      password,
    };

    try {
      const res = await fetch("http://localhost:8080/api/auth/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
      });

      if (res.ok) {
        alert("User created successfully");
        setPage("signUp"); 
      } else {
        const msg = await res.text();
        alert(msg);
      }

    } catch (error) {
      console.error(error);
      alert("Server error");
    }
  };

  return (
    <div>
      <h2>Signup</h2>

      <input
        placeholder="email"
        type="email"
        value={login}
        onChange={(e) => setLogin(e.target.value)}
      />

      <input
        placeholder="password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={handleSignup}>Create account</button>
    </div>
  );
}

export default SignUp;