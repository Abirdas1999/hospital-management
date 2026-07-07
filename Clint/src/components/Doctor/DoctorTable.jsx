import { FaEdit, FaTrash } from "react-icons/fa";
import styles from "./Css/DoctorTable.module.css";

function DoctorTable({
	doctors,
	onEdit,
	onDelete
}) {
	return (
		<div className={styles.tableCard}>
			<table className={styles.table}>
				<thead>
					<tr>
						<th>Name</th>
						<th>Department</th>
						<th>Experience</th>
						<th>Phone</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>

				<tbody>
					{doctors.length === 0 ? (
						<tr>
							<td
								colSpan="6"
								className={styles.empty}
							>
								No doctors found.
							</td>
						</tr>
					) : (
						doctors.map((doctor) => (
							<tr key={doctor.id}>
								<td>{doctor.name}</td>

								<td>{doctor.department}</td>

								<td>{doctor.experience} Years</td>

								<td>{doctor.phone}</td>

								<td>
									<span
										className={
											doctor.status === "Active"
												? styles.active
												: styles.inactive
										}
									>
										{doctor.status}
									</span>
								</td>

								<td>
									<div className={styles.actionButtons}>
										<button
											className={styles.editBtn}
											onClick={() => onEdit(doctor)}
										>
											<FaEdit />
										</button>

										<button
											className={styles.deleteBtn}
											onClick={() => onDelete(doctor)}
										>
											<FaTrash />
										</button>
									</div>
								</td>
							</tr>
						))
					)}
				</tbody>
			</table>
		</div>
	);
}

export default DoctorTable;