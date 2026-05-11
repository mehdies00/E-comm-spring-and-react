import { useState } from "react";
import { signup } from "../../services/authService";
import "./SignUp.css";

function SignUp({ onSuccess, onLoginClick }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState({});
  const [loading, setLoading] = useState(false);

  const handleSignup = async () => {
    if (!email.trim()) {
      setError({ login: "Please enter your email" });
      return;
    }
    if (!password.trim()) {
      setError({ password: "Please enter your password" });
      return;
    }
    setLoading(true);
    setError({});
    try {
      const data = await signup(email, password);
      localStorage.setItem("token", data.token);
      onSuccess(data.login);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="signup-page">
      <div className="signup-left">
        <div className="signup-left-overlay" />
        <div className="signup-left-content">
          <p className="signup-left-tag">E-commerce Platform</p>
          <h1 className="signup-left-heading">
            Create your<br />account and<br />start shopping.
          </h1>
        </div>
      </div>

      <div className="signup-right">
        <div className="signup-card">
          <h2 className="signup-title">Create account</h2>
          <p className="signup-sub">Fill in your details to get started</p>

          <input
            className="signup-input"
            type="email"
            placeholder="Email"
            value={email}
            autoFocus
            onChange={(e) => setEmail(e.target.value)}
          />
          {error.login && <p className="signup-error">{error.login}</p>}

          <input
            className="signup-input"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && handleSignup()}
          />
          {error.password && <p className="signup-error">{error.password}</p>}

          <button
            className="signup-btn"
            onClick={handleSignup}
            disabled={loading}
          >
            {loading ? "Creating account…" : "Create account"}
          </button>

          <p className="signup-switch">
            Already have an account?{" "}
            <button className="switch-btn" onClick={onLoginClick}>
              Sign in
            </button>
          </p>
        </div>
        <p className="signup-footer">© 2026 Nexora. All rights reserved.</p>
      </div>
    </div>
  );
}

export default SignUp;