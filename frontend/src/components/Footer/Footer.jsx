import { Link } from "react-router-dom";
import styles from "./Footer.module.css";

function Footer() {
  return (
    <footer className={styles.footer}>

      <div className={`container ${styles.footerContainer}`}>

        {/* Logo & About */}
        <div className={styles.column}>
          <div className={styles.logo}>
            <span className={styles.logoPlaceholder}>🏥</span>

            {/* Future Logo */}
            {/* <img src={logo} alt="Hospital Logo" className={styles.logoImg} /> */}
          </div>

          <h4>Hospital Management</h4>

          <p>
            Providing trusted healthcare services with modern technology,
            experienced doctors, and patient-centered care.
          </p>
        </div>

        {/* Services */}
        <div className={styles.column}>
          <h4>Services</h4>

          <Link to="/services">General Medicine</Link>
          <Link to="/services">Cardiology</Link>
          <Link to="/services">Neurology</Link>
          <Link to="/services">Emergency Care</Link>
          <Link to="/services">Laboratory</Link>
        </div>

        {/* Navigation */}
        <div className={styles.column}>
          <h4>Navigation</h4>

          <Link to="/">Home</Link>
          <Link to="/about">About</Link>
          <Link to="/services">Services</Link>
          <Link to="/doctors">Doctors</Link>
          <Link to="/contact">Contact</Link>
        </div>

        {/* Help & Legal */}
        <div className={styles.column}>
          <h4>Help & Legal</h4>

          <Link to="/">Terms & Conditions</Link>
          <Link to="/">Privacy Policy</Link>
          <Link to="/contact">Help Center</Link>
          <Link to="/contact">Support</Link>
          <Link to="/contact">Contact Us</Link>
        </div>

      </div>

      <div className={styles.subFooter}>
        <p>
          Maintained by <span>Abir</span> | © 2026 Hospital Management System.
          All Rights Reserved.
        </p>
      </div>

    </footer>
  );
}

export default Footer;