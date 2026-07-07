import api from "./axios";

export const getDoctors = () => api.get("/api/doctors");

export const saveDoctor = (doctor) =>
    api.post("/api/doctors", doctor);

export const updateDoctor = (id, doctor) =>
    api.put(`/api/doctors/${id}`, doctor);

export const deleteDoctor = (id) =>
    api.delete(`/api/doctors/${id}`);