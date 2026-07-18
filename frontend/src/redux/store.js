import { configureStore } from "@reduxjs/toolkit";

import authReducer from "./slices/authSlice";
import doctorReducer from "./slices/doctorSlice";

export const store = configureStore({
	reducer: {
		auth: authReducer,
		doctor: doctorReducer,
	},
});

export default store;