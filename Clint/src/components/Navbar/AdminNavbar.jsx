import { Link } from "react-router-dom";
import {
    BiBell,
    BiChevronDown,
    BiUserCircle,
} from "react-icons/bi";
import styles from "./AdminNavbar.module.css";

function AdminNavbar() {
    return (
        <header className={styles.navbar}>

            <div className={styles.left}>

                {/* Logo */}

                <Link to="/admin" className={styles.logo}>

                    <span className={styles.logoPlaceholder}>
                        🏥
                    </span>

                    {/* Future Logo */}
                    {/* <img src={logo} alt="Logo" className={styles.logoImg} /> */}

                </Link>

                {/* Title */}

                <div className={styles.brand}>

                    <h2>Hospital Management</h2>

                    <span>Admin Dashboard</span>

                </div>

            </div>

            {/* Right Side */}

            <div className={styles.right}>

                {/* Notification */}

                <button
                    type="button"
                    className={styles.iconBtn}
                >

                    <BiBell />

                    <span className={styles.notification}></span>

                </button>

                {/* Profile */}

                <div className={styles.profile}>

                    <div className={styles.avatar}>

                        <BiUserCircle />

                    </div>

                    <div className={styles.userInfo}>

                        <h4>Admin</h4>

                        <span>Super Admin</span>

                    </div>

                    <BiChevronDown className={styles.arrow} />

                </div>

            </div>

        </header>
    );
}

export default AdminNavbar;