package com.server.util;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {

    // ---------------- STATUS ----------------

    private Integer status;

    // ---------------- MESSAGE ----------------

    private String message;

    // ---------------- DATA ----------------

    private T data;

    // ---------------- TIMESTAMP ----------------

    private LocalDateTime timestamp = LocalDateTime.now();

}