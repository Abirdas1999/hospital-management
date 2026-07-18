package com.server.constants;

public final class ApiConstants {

	// ==========================================================
	// API VERSION
	// ==========================================================

	public static final String API = "/api/v1";

	// ==========================================================
	// MODULES
	// ==========================================================

	public static final String AUTH = API + "/auth";
	public static final String ADMIN = API + "/admin";
	public static final String DOCTOR = API + "/doctor";
	public static final String PATIENT = API + "/patient";
	public static final String APPOINTMENT = API + "/appointment";
	public static final String SERVICE = API + "/service";
	public static final String CONTACT = API + "/contact";

	// ==========================================================
	// COMMON ENDPOINTS
	// ==========================================================

	public static final String SAVE = "/save";
	public static final String FETCH = "/fetch";
	public static final String SEARCH = "/search";
	public static final String UPDATE = "/update";
	public static final String DELETE = "/delete";

	// ==========================================================
	// PATH VARIABLES
	// ==========================================================

	public static final String ID = "/{id}";

	// ==========================================================
	// AUTHENTICATION
	// ==========================================================

	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	public static final String CHANGE_PASSWORD = "/change-password";
	public static final String RESET_PASSWORD = "/reset-password";

	// ==========================================================
	// FUTURE FEATURES
	// ==========================================================

	public static final String FORGOT_PASSWORD = "/forgot-password";
	public static final String VERIFY_OTP = "/verify-otp";
	public static final String REFRESH_TOKEN = "/refresh-token";

	// ==========================================================
	// PRIVATE CONSTRUCTOR
	// ==========================================================

	private ApiConstants() {
		throw new IllegalStateException("Utility class");
	}

}