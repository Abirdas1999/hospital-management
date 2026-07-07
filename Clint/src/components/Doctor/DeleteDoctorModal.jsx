import { FaTrash } from "react-icons/fa";
import styles from "./Css/DeleteDoctorModal.module.css";

function DeleteDoctorModal({
  isOpen,
  doctor,
  onClose,
  onConfirm,
}) {
  if (!isOpen) return null;

  return (
    <div className={styles.overlay}>
      <div className={styles.modal}>
        <div className={styles.icon}>
          <FaTrash />
        </div>

        <h2>Delete Doctor?</h2>

        <p>
          Are you sure you want to delete <strong>{doctor?.name}</strong>?
        </p>

        <span className={styles.warning}>
          This action cannot be undone.
        </span>

        <div className={styles.actions}>
          <button
            className={styles.cancelBtn}
            onClick={onClose}
          >
            Cancel
          </button>

          <button
            className={styles.deleteBtn}
            onClick={onConfirm}
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
}

export default DeleteDoctorModal;
