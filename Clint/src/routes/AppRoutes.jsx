import { BrowserRouter, Routes, Route } from "react-router-dom";
import About from "../pages/About/About";
import Home from "../pages/Home/Home";
import Contact from "../pages/Contact/Contact";
import Doctors from "../pages/Doctors/Doctors";
import Error from "../pages/Error/Error";
import Login from "../pages/Login/Login";
import Service from "../pages/Services/Service";
import MainLayout from "../layout/MainLayout";
import AdminLayout from "../layout/AdminLayout";
import AdminHome from "../pages/Admin/AdminHome";
import AdminLogin from "../pages/Admin/AdminLogin";
import AdminDoctor from "../pages/Admin/AdminDoctor";
import AdminService from "../pages/Admin/AdminService";
import AdminAppointment from "../pages/Admin/AdminAppointment";
function AppRoutes() {
    return (
        <BrowserRouter>

            <Routes>

                {/* Client Website */}

                <Route element={<MainLayout />}>

                    <Route path="/" element={<Home />} />
                    <Route path="/about" element={<About />} />
                    <Route path="/services" element={<Service />} />
                    <Route path="/doctors" element={<Doctors />} />
                    <Route path="/contact" element={<Contact />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/admin/login" element={<AdminLogin />} />


                </Route>

                {/* Admin */}

                {/* <Route path="/admin/login" element={<AdminLogin />} /> */}

                <Route path="/admin" element={<AdminLayout />}>

                    <Route index element={<AdminHome />} />

                    <Route path="doctors" element={<AdminDoctor />} />

                    <Route path="services" element={<AdminService />} />

                    <Route path="appointments" element={<AdminAppointment />} />

                </Route>

                {/* 404 */}

                <Route path="*" element={<Error />} />

            </Routes>

        </BrowserRouter>
    )
}

export default AppRoutes