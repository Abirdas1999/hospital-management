import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
    fetchDoctors,
    saveDoctor,
    updateDoctor,
    deleteDoctor,
} from "../../redux/slices/doctorSlice";
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


    // ---------------- REDUX ----------------

    const dispatch = useDispatch();

    const { doctors, loading, error } = useSelector(
        (state) => state.doctor
    );

    useEffect(() => {
        dispatch(fetchDoctors());
    }, [dispatch]);



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

    // ---------------- HANDLE SUBMIT ----------------

    const handleSubmit = async (doctor) => {

        try {

            if (isEdit) {

                await dispatch(
                    updateDoctor({
                        id: selectedDoctor.id,
                        doctor,
                    })
                ).unwrap();

            } else {

                await dispatch(saveDoctor(doctor)).unwrap();

            }

            setIsModalOpen(false);
            setSelectedDoctor(null);
            setIsEdit(false);

        } catch (error) {

            console.error(error);

        }
    };


    // ---------------- HANDLE DELETE ----------------

    const confirmDelete = async () => {

        try {

            await dispatch(deleteDoctor(selectedDoctor.id)).unwrap();

            setIsDeleteOpen(false);
            setSelectedDoctor(null);

        } catch (error) {

            console.error(error);

        }
    };


    // ---------------- LOADING ----------------

    if (loading) {

        return <h2>Loading doctors...</h2>;

    }

    // ---------------- ERROR ----------------

    if (error) {

        return <h2>Failed to load doctors.</h2>;

    }
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