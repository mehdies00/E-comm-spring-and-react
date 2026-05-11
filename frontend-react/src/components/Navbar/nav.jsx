import GuestNavbar from "./GuestNavbar";
import AuthNavbar from "./AuthNavbar";

function Nav({ user, onLoginClick, onLogout }) {
  if (user) {
    return <AuthNavbar user={user} onLogout={onLogout} />;
  }
  return <GuestNavbar onLoginClick={onLoginClick} />;
}

export default Nav;