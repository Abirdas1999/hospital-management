import { useRef } from "react";
import { Link } from "react-router-dom";
import styles from "./Css/AdminLogin.module.css";

function AdminLogin() {

  const cardRef = useRef(null);

  const animationFrame = useRef(null);

  const current = useRef({
    x: 0,
    y: 0,
  });

  const target = useRef({
    x: 0,
    y: 0,
  });

  const animate = () => {

    current.current.x += (target.current.x - current.current.x) * 0.12;
    current.current.y += (target.current.y - current.current.y) * 0.12;

    const card = cardRef.current;

    card.style.setProperty(
      "--rotateX",
      `${current.current.y}deg`
    );

    card.style.setProperty(
      "--rotateY",
      `${current.current.x}deg`
    );

    animationFrame.current = requestAnimationFrame(animate);
  };

  const handleMouseMove = (e) => {

    const card = cardRef.current;

    const rect = card.getBoundingClientRect();

    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    const centerX = rect.width / 2;
    const centerY = rect.height / 2;

    target.current.x = ((x - centerX) / centerX) * 1.5;
    target.current.y = -((y - centerY) / centerY) * 1.5;

    if (!animationFrame.current) {
      animate();
    }
  };

  const handleMouseLeave = () => {

    target.current.x = 0;
    target.current.y = 0;
  };

  return (
    <div className={`container-fluid ${styles.page}`}>

      <div
        ref={cardRef}
        className={styles.outerForm}
        onMouseMove={handleMouseMove}
        onMouseLeave={handleMouseLeave}
      >

        <div className={styles.formHeader}>

          <div className={styles.logo}>
            🏥
          </div>

          <div className={styles.heading}>
            <h2>Login</h2>
            <p>Welcome Back to Admin</p>
          </div>

        </div>

        <form className={styles.form}>

          <div className="mb-4">
            <input
              type="text"
              className={`form-control ${styles.input}`}
              placeholder="Email or Username"
            />
          </div>

          <div className="mb-4">
            <input
              type="password"
              className={`form-control ${styles.input}`}
              placeholder="Password"
            />
          </div>

          <button
            type="submit"
            className={styles.loginBtn}
          >
            Login
          </button>

        </form>

        <div className={styles.links}>
          <Link to="/">
            Back to Home
          </Link>
        </div>

        <div className={styles.footer}>
          <a href="#">Terms & Conditions</a>
          <a href="#">Privacy Policy</a>
        </div>

      </div>

    </div>
  );
}

export default AdminLogin;