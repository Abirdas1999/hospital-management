import styles from "./AdminFooter.module.css";

function AdminFooter() {
  return (
    <footer className={styles.footer}>
      <p>
        © 2026 <span>Hospital Management System</span> • Version 1.0
      </p>
    </footer>
  );
}

export default AdminFooter;