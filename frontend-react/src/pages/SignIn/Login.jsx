import { useState } from "react";
import { loginUser } from "../../services/authService";
import "./Login.css";

function Login({ onSuccess, onSignUpClick }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [step, setStep] = useState("email");
  const [error, setError] = useState({});
  const [loading, setLoading] = useState(false);

  const handleContinue = async () => {
    if (step === "email") {
      if (!email.trim()) {
        setError({ login: "Please enter your email" });
        return;
      }
      setError({});
      setStep("password");
      return;
    }
    setLoading(true);
    setError({});
    try {
      const data = await loginUser(email, password);
      localStorage.setItem("token", data.token);
      onSuccess(data.login);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <div className="login-left">
        <div className="login-left-overlay" />
        <div className="login-left-content">
          <p className="login-left-tag">E-commerce Platform</p>
          <h1 className="login-left-heading">
            Shop with<br />confidence,<br />pay with ease.
          </h1>
        </div>
      </div>

      <div className="login-right">
        <div className="login-card">
          <h2 className="login-title">
            {step === "email" ? "Sign in" : "Enter password"}
          </h2>

          <p className="login-sub">
            {step === "email" ? "Enter your email to continue" : (
              <span>
                Signing in as <strong>{email}</strong>{" "}
                <button
                  className="change-btn"
                  onClick={() => { setStep("email"); setError({}); }}
                >
                  Change
                </button>
              </span>
            )}
          </p>

          {step === "email" ? (
            <>
              <input
                key="email"
                className="login-input"
                type="email"
                placeholder="Email"
                value={email}
                autoFocus
                onChange={(e) => setEmail(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleContinue()}
              />
              {error.login && <p className="login-error">{error.login}</p>}
            </>
          ) : (
            <>
              <input
                key="password"
                className="login-input"
                type="password"
                placeholder="Password"
                value={password}
                autoFocus
                onChange={(e) => setPassword(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleContinue()}
              />
              {error.password && <p className="login-error">{error.password}</p>}
            </>
          )}

          <button
            className="continue-btn"
            onClick={handleContinue}
            disabled={loading}
          >
            {loading ? "Signing in…" : "Continue"}
          </button>

          <p className="login-switch">
            Don't have an account?{" "}
            <button className="switch-btn" onClick={onSignUpClick}>
              Sign up
            </button>
          </p>
        </div>
        <p className="login-footer">© 2026 Nexora. All rights reserved.</p>
      </div>
    </div>
  );
}

export default Login;