import { useState } from "react";
import {
    FaUserMd,
    FaPlus,
    FaSearch,
    FaUsers,
    FaHospital,
} from "react-icons/fa";
import DoctorTable from "../../components/Doctor/DoctorTable";
import DoctorModal from "../../components/Doctor/DoctorModal";
import DeleteDoctorModal from "../../components/Doctor/DeleteDoctorModal";
import styles from "./Css/AdminDoctor.module.css";

function AdminDoctor() {

    const [search, setSearch] = useState("");
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isDeleteOpen, setIsDeleteOpen] = useState(false);
    const [selectedDoctor, setSelectedDoctor] = useState(null);
    const [isEdit, setIsEdit] = useState(false);

    const [doctors, setDoctors] = useState([
        {
            id: 1,
            name: "Dr. John Smith",
            department: "Cardiology",
            experience: "12 Years",
            phone: "+91 9876543210",
            status: "Active",
        },
        {
            id: 2,
            name: "Dr. Emily Watson",
            department: "Neurology",
            experience: "8 Years",
            phone: "+91 9123456780",
            status: "Active",
        },
        {
            id: 3,
            name: "Dr. Michael Brown",
            department: "Orthopedic",
            experience: "10 Years",
            phone: "+91 9988776655",
            status: "Inactive",
        },
    ]);

    const filteredDoctors = doctors.filter((doctor) =>
        doctor.name.toLowerCase().includes(search.toLowerCase())
    );

    const handleAdd = () => {
        setSelectedDoctor(null);
        setIsEdit(false);
        setIsModalOpen(true);
    };

    const handleEdit = (doctor) => {
        setSelectedDoctor(doctor);
        setIsEdit(true);
        setIsModalOpen(true);
    };

    const handleDelete = (doctor) => {
        setSelectedDoctor(doctor);
        setIsDeleteOpen(true);
    };

    const handleSubmit = (doctor) => {
        if (isEdit) {
            setDoctors((prev) =>
                prev.map((d) =>
                    d.id === selectedDoctor.id ? { ...doctor, id: d.id } : d
                )
            );
        } else {
            setDoctors((prev) => [
                ...prev,
                {
                    ...doctor,
                    id: Date.now(),
                },
            ]);
        }

        setIsModalOpen(false);
        setSelectedDoctor(null);
    };

    const confirmDelete = () => {
        setDoctors((prev) =>
            prev.filter((doctor) => doctor.id !== selectedDoctor.id)
        );

        setIsDeleteOpen(false);
        setSelectedDoctor(null);
    };

    return (
        <div className={`container-fluid ${styles.page}`}>

            {/* Header */}

            <div className={styles.header}>

                <div>

                    <h1>
                        <FaUserMd />
                        Doctor Management
                    </h1>

                    <p>
                        Manage doctors, departments and availability.
                    </p>

                </div>

                <button className={styles.addBtn} onClick={handleAdd}>

                    <FaPlus />

                    <span>Add Doctor</span>

                </button>

            </div>

            {/* Statistics */}

            <div className={styles.statsGrid}>

                <div className={styles.statCard}>

                    <FaUsers className={styles.icon} />

                    <h2>{doctors.length}</h2>

                    <p>Total Doctors</p>

                </div>

                <div className={styles.statCard}>

                    <FaHospital className={styles.icon} />

                    <h2>{new Set(doctors.map((doctor) => doctor.department)).size}</h2>

                    <p>Departments</p>

                </div>

                <div className={styles.statCard}>

                    <FaUserMd className={styles.icon} />

                    <h2>{doctors.filter((doctor) => doctor.status === "Active").length}</h2>

                    <p>Active Doctors</p>

                </div>

            </div>

            {/* Search */}

            <div className={styles.searchSection}>

                <div className={styles.searchBox}>

                    <FaSearch />

                    <input
                        type="text"
                        placeholder="Search doctor..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />

                </div>

            </div>

            <DoctorTable
                doctors={filteredDoctors}
                onEdit={handleEdit}
                onDelete={handleDelete}
            />

            <DoctorModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSubmit={handleSubmit}
                initialData={selectedDoctor}
                isEdit={isEdit}
            />

            <DeleteDoctorModal
                isOpen={isDeleteOpen}
                doctor={selectedDoctor}
                onClose={() => setIsDeleteOpen(false)}
                onConfirm={confirmDelete}
            />

        </div>
    );
}

export default AdminDoctor;