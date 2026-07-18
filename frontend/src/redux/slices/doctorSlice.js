import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import {
	getDoctors,
	saveDoctor as saveDoctorApi,
	updateDoctor as updateDoctorApi,
	deleteDoctor as deleteDoctorApi,
} from "../../api/doctorApi";

// ---------------- INITIAL STATE ----------------

const initialState = {
	doctors: [],
	loading: false,
	error: null,
};

// ---------------- FETCH ALL DOCTORS ----------------

export const fetchDoctors = createAsyncThunk(
	"doctor/fetchDoctors",

	async (_, thunkAPI) => {
		try {
			const response = await getDoctors();

			return response.data.data;
		} catch (error) {
			return thunkAPI.rejectWithValue(
				error.response?.data || "Something went wrong",
			);
		}
	},
);

// ---------------- SAVE DOCTOR ----------------

export const saveDoctor = createAsyncThunk(
	"doctor/saveDoctor",

	async (doctor, thunkAPI) => {
		try {
			const response = await saveDoctorApi(doctor);

			thunkAPI.dispatch(fetchDoctors());

			return response.data;
		} catch (error) {
			return thunkAPI.rejectWithValue(
				error.response?.data || "Something went wrong",
			);
		}
	},
);

// ---------------- UPDATE DOCTOR ----------------

export const updateDoctor = createAsyncThunk(
	"doctor/updateDoctor",

	async ({ id, doctor }, thunkAPI) => {
		try {
			const response = await updateDoctorApi(id, doctor);

			thunkAPI.dispatch(fetchDoctors());

			return response.data;
		} catch (error) {
			return thunkAPI.rejectWithValue(
				error.response?.data || "Something went wrong",
			);
		}
	},
);
// ---------------- DELETE DOCTOR ----------------

export const deleteDoctor = createAsyncThunk(
	"doctor/deleteDoctor",

	async (id, thunkAPI) => {
		try {
			const response = await deleteDoctorApi(id);

			thunkAPI.dispatch(fetchDoctors());

			return response.data;
		} catch (error) {
			return thunkAPI.rejectWithValue(
				error.response?.data || "Something went wrong",
			);
		}
	},
);

// ---------------- SLICE ----------------

const doctorSlice = createSlice({
	name: "doctor",

	initialState,

	reducers: {},

	extraReducers: (builder) => {
		builder

			// ---------------- FETCH DOCTORS ----------------

			.addCase(fetchDoctors.pending, (state) => {
				state.loading = true;
				state.error = null;
			})

			.addCase(fetchDoctors.fulfilled, (state, action) => {
				state.loading = false;
				state.doctors = action.payload.content;
			})

			.addCase(fetchDoctors.rejected, (state, action) => {
				state.loading = false;
				state.error = action.payload;
			})

			// ---------------- SAVE DOCTOR ----------------

			.addCase(saveDoctor.pending, (state) => {
				state.loading = true;
				state.error = null;
			})

			.addCase(saveDoctor.fulfilled, (state) => {
				state.loading = false;
			})

			.addCase(saveDoctor.rejected, (state, action) => {
				state.loading = false;
				state.error = action.payload;
			})

			// ---------------- UPDATE DOCTOR ----------------

			.addCase(updateDoctor.pending, (state) => {
				state.loading = true;
				state.error = null;
			})

			.addCase(updateDoctor.fulfilled, (state) => {
				state.loading = false;
			})

			.addCase(updateDoctor.rejected, (state, action) => {
				state.loading = false;
				state.error = action.payload;
			})

			// ---------------- DELETE DOCTOR ----------------

			.addCase(deleteDoctor.pending, (state) => {
				state.loading = true;
				state.error = null;
			})

			.addCase(deleteDoctor.fulfilled, (state) => {
				state.loading = false;
			})

			.addCase(deleteDoctor.rejected, (state, action) => {
				state.loading = false;
				state.error = action.payload;
			});
	},
});

// ---------------- EXPORT ----------------

export default doctorSlice.reducer;
