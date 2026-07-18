package com.server.model.doctor.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorProfileResponse {

    // ---------------- DOCTOR ID ----------------

    private Integer id;

    // ---------------- DOCTOR NAME ----------------

    private String name;

    // ---------------- EMAIL ----------------

    private String email;

    // ---------------- PHONE ----------------

    private String phone;

    // ---------------- DEPARTMENT ----------------

    private String department;

    // ---------------- QUALIFICATION ----------------

    private String qualification;

    // ---------------- EXPERIENCE ----------------

    private Integer experience;

    // ---------------- CONSULTATION FEE ----------------

    private Double consultationFee;

    // ---------------- ACCOUNT STATUS ----------------

    private String status;

    // ---------------- CREATED DATE ----------------

    private LocalDateTime createdAt;

}