package com.server.model.doctor.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorPasswordRequest {

    // ---------------- NEW PASSWORD ----------------

    @NotBlank(message = "Password is required")
    @Size(
            min = 6,
            max = 20,
            message = "Password must be between 6 and 20 characters"
    )
    private String password;

}