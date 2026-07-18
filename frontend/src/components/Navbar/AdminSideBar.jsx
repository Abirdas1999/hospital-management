

import { NavLink } from "react-router-dom";
import {
    BiHomeAlt,
    BiUser,
    BiBriefcase,
    BiCalendar,
    BiLogOut,
} from "react-icons/bi";
import styles from "./AdminSideBar.module.css";

function AdminSideBar() {
    return (
        <aside className={styles.sidebar}>
            <div className={styles.menu}>
                <NavLink
                    to="/admin"
                    end
                    className={({ isActive }) =>
                        `${styles.link} ${isActive ? styles.active : ""}`
                    }
                >
                    <BiHomeAlt />
                    <span>Dashboard</span>
                </NavLink>

                <NavLink
                    to="/admin/doctors"
                    className={({ isActive }) =>
                        `${styles.link} ${isActive ? styles.active : ""}`
                    }
                >
                    <BiUser />
                    <span>Doctors</span>
                </NavLink>

                <NavLink
                    to="/admin/services"
                    className={({ isActive }) =>
                        `${styles.link} ${isActive ? styles.active : ""}`
                    }
                >
                    <BiBriefcase />
                    <span>Services</span>
                </NavLink>

                <NavLink
                    to="/admin/appointments"
                    className={({ isActive }) =>
                        `${styles.link} ${isActive ? styles.active : ""}`
                    }
                >
                    <BiCalendar />
                    <span>Appointments</span>
                </NavLink>
            </div>

            <div className={styles.bottom}>
                <button type="button" className={styles.logoutBtn}>
                    <BiLogOut />
                    <span>Logout</span>
                </button>
            </div>
        </aside>
    );
}

export default AdminSideBar;