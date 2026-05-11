import "./GuestNavbar.css";

function GuestNavbar({ onLoginClick }) {
  return (
    <nav className="guest-navbar">
      <span className="navbar-logo">⬡ Nexora</span>
      <button className="navbar-signin-btn" onClick={onLoginClick}>
        Sign In
      </button>
    </nav>
  );
}

export default GuestNavbar;