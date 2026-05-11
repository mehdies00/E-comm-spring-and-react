import { useState } from "react";
import "./AuthNavbar.css";

function AuthNavbar({ user, onLogout }) {
  const [query, setQuery] = useState("");

  const handleSearch = (e) => {
    e.preventDefault();
    console.log("Searching:", query);
  };

  return (
    <nav className="auth-navbar">
      <span className="navbar-logo">⬡ Nexora</span>

      <ul className="navbar-links">
        <li><a href="#">Home</a></li>
        <li><a href="#">Catalogue</a></li>
        <li><a href="#">Orders</a></li>
      </ul>

      <form className="navbar-search" onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="Search products..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <button type="submit">⌕</button>
      </form>

      <div className="navbar-user">
        <span className="user-avatar">{user?.charAt(0).toUpperCase()}</span>
        <button className="logout-btn" onClick={onLogout}>Log out</button>
      </div>
    </nav>
  );
}

export default AuthNavbar;