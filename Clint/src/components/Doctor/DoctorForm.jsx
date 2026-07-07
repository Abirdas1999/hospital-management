import { useState } from "react";
import styles from "./Css/DoctorForm.module.css";

function DoctorForm({
	initialData,
	onSubmit,
	onCancel,
	isEdit,
}) {
	const [doctor, setDoctor] = useState(
		initialData || {
			name: "",
			email: "",
			phone: "",
			department: "",
			qualification: "",
			experience: "",
			fee: "",
			status: "Active",
		}
	);

	const handleChange = (e) => {
		const { name, value } = e.target;

		setDoctor((prev) => ({
			...prev,
			[name]: value,
		}));
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		onSubmit(doctor);
	};

	return (
		<form
			className={styles.form}
			onSubmit={handleSubmit}
		>
			<div className={styles.grid}>

				<div className={styles.field}>
					<label>Doctor Name</label>

					<input
						type="text"
						name="name"
						value={doctor.name}
						onChange={handleChange}
						placeholder="Dr. John Smith"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Email</label>

					<input
						type="email"
						name="email"
						value={doctor.email}
						onChange={handleChange}
						placeholder="doctor@gmail.com"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Phone</label>

					<input
						type="text"
						name="phone"
						value={doctor.phone}
						onChange={handleChange}
						placeholder="+91 9876543210"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Department</label>

					<select
						name="department"
						value={doctor.department}
						onChange={handleChange}
						required
					>
						<option value="">
							Select Department
						</option>

						<option>Cardiology</option>
						<option>Neurology</option>
						<option>Orthopedic</option>
						<option>Pediatrics</option>
						<option>Dermatology</option>
						<option>ENT</option>
						<option>Oncology</option>
						<option>Gynecology</option>
						<option>General Medicine</option>

					</select>
				</div>

				<div className={styles.field}>
					<label>Qualification</label>

					<input
						type="text"
						name="qualification"
						value={doctor.qualification}
						onChange={handleChange}
						placeholder="MBBS, MD"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Experience</label>

					<input
						type="number"
						name="experience"
						value={doctor.experience}
						onChange={handleChange}
						placeholder="10"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Consultation Fee</label>

					<input
						type="number"
						name="fee"
						value={doctor.fee}
						onChange={handleChange}
						placeholder="800"
						required
					/>
				</div>

				<div className={styles.field}>
					<label>Status</label>

					<select
						name="status"
						value={doctor.status}
						onChange={handleChange}
					>
						<option>Active</option>
						<option>Inactive</option>
					</select>
				</div>

			</div>

			<div className={styles.actions}>

				<button
					type="button"
					className={styles.cancelBtn}
					onClick={onCancel}
				>
					Cancel
				</button>

				<button
					type="submit"
					className={styles.saveBtn}
				>
					{isEdit
						? "Update Doctor"
						: "Save Doctor"}
				</button>

			</div>

		</form>
	);
}

export default DoctorForm;