import api from "./axios";

// ---------------- FETCH ALL DOCTORS ----------------

export const getDoctors = (page = 0, size = 5, sortBy = "id", direction = "asc") =>
    api.get("/doctor/fetch", {
        params: { page, size, sortBy, direction },
    });

// ---------------- FETCH DOCTOR BY ID ----------------

export const getDoctorById = (id) =>
    api.get(`/doctor/fetch/${id}`);

// ---------------- SAVE DOCTOR ----------------

export const saveDoctor = (doctor) =>
    api.post("/doctor/save", doctor);

// ---------------- UPDATE DOCTOR ----------------

export const updateDoctor = (id, doctor) =>
    api.put(`/doctor/update/${id}`, doctor);

// ---------------- DELETE DOCTOR ----------------

export const deleteDoctor = (id) =>
    api.delete(`/doctor/delete/${id}`);

// ---------------- SEARCH DOCTORS ----------------

export const searchDoctors = (params) =>
    api.get("/doctor/search", { params });

// ---------------- RESET PASSWORD ----------------

export const resetDoctorPassword = (id, password) =>
    api.put(`/doctor/reset-password/${id}`, password);