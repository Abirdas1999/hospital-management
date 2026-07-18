import { Outlet } from "react-router-dom";
import AdminNavbar from "../components/Navbar/AdminNavbar";
import AdminSideBar from "../components/Navbar/AdminSideBar";
import AdminFooter from "../components/Footer/AdminFooter";
import styles from "./AdminLayout.module.css";

function AdminLayout() {
  return (
    <div className={styles.layout}>
      <AdminNavbar />

      <div className={styles.body}>
        <AdminSideBar />

        <main className={styles.main}>
          <Outlet />
        </main>
      </div>

      <AdminFooter />
    </div>
  );
}

export default AdminLayout;