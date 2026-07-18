import { IoClose } from "react-icons/io5";
import DoctorForm from "./DoctorForm";
import styles from "./Css/DoctorModal.module.css";

function DoctorModal({
	isOpen,
	onClose,
	onSubmit,
	initialData,
	isEdit,
}) {

	if (!isOpen) return null;

	return (
		<div className={styles.overlay}>

			<div className={styles.modal}>

				{/* Header */}

				<div className={styles.header}>

					<h2>
						{isEdit ? "Edit Doctor" : "Add Doctor"}
					</h2>

					<button
						className={styles.closeBtn}
						onClick={onClose}
					>
						<IoClose />
					</button>

				</div>

				{/* Form */}

				<DoctorForm
					initialData={initialData}
					onSubmit={onSubmit}
					onCancel={onClose}
					isEdit={isEdit}
				/>

			</div>

		</div>
	);
}

export default DoctorModal;