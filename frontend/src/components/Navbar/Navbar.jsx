import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BiLogIn } from "react-icons/bi";
import styles from "./Navbar.module.css";

function Navbar() {

  const [isOpen, setIsOpen] = useState(false);

  /* Lock body scroll while mobile menu is open */
  useEffect(() => {

    if (isOpen) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "auto";
    }

    return () => {
      document.body.style.overflow = "auto";
    };

  }, [isOpen]);

  const toggleMenu = () => {
    setIsOpen((prev) => !prev);
  };

  const closeMenu = () => {
    setIsOpen(false);
  };

  return (
    <>
      <nav className={`navbar navbar-expand-lg ${styles.customNavbar}`}>

        <div className="container">

          {/* Logo */}
          <Link
            to="/"
            className={styles.logo}
            onClick={closeMenu}
          >
            <span className={styles.logoPlaceholder}>🏥</span>

            {/* Future Logo */}
            {/* <img src={logo} alt="Hospital Logo" className={styles.logoImg} /> */}
          </Link>

          {/* Navigation */}
          <div
            className={`${styles.navMenu} ${
              isOpen ? styles.navMenuOpen : ""
            }`}
          >

            <Link
              to="/"
              className={styles.navLink}
              onClick={closeMenu}
            >
              Home
            </Link>

            <Link
              to="/about"
              className={styles.navLink}
              onClick={closeMenu}
            >
              About
            </Link>

            <Link
              to="/services"
              className={styles.navLink}
              onClick={closeMenu}
            >
              Services
            </Link>

            <Link
              to="/doctors"
              className={styles.navLink}
              onClick={closeMenu}
            >
              Doctors
            </Link>

            <Link
              to="/contact"
              className={styles.navLink}
              onClick={closeMenu}
            >
              Contact
            </Link>

            {/* Mobile Login Button */}
            <Link
              to="/login"
              className={styles.mobileBtn}
              onClick={closeMenu}
            >
              <BiLogIn />
              <span>Login</span>
            </Link>

          </div>

          {/* Hamburger */}
          <button
            type="button"
            className={`${styles.menuBtn} ${
              isOpen ? styles.open : ""
            }`}
            onClick={toggleMenu}
            aria-label="Toggle Navigation"
          >
            <span className={styles.bar}></span>
            <span className={styles.bar}></span>
            <span className={styles.bar}></span>
          </button>

        </div>

      </nav>

      {/* Background Overlay */}
      <div
        className={`${styles.menuOverlay} ${
          isOpen ? styles.menuOverlayOpen : ""
        }`}
        onClick={closeMenu}
      ></div>
    </>
  );
}

export default Navbar;