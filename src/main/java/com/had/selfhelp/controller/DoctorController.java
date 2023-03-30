package com.had.selfhelp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.had.selfhelp.entity.Doctor;
import com.had.selfhelp.entity.Patient;
import com.had.selfhelp.entity.Workout;
import com.had.selfhelp.entity.Workout_instance;
import com.had.selfhelp.service.DoctorService;
import com.had.selfhelp.service.WorkoutService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private DoctorService doctorService;
	private WorkoutService workoutService;
	
	@Autowired
	public DoctorController(DoctorService doctorService, WorkoutService workoutService) {
		this.doctorService = doctorService;
		this.workoutService = workoutService;
	}

	@PostMapping("/")
	public Doctor addDoctor(@RequestBody Doctor theDoctor) {
		theDoctor.setDoctor_id(0);
		doctorService.save(theDoctor);
		return theDoctor;
	}
	
	@GetMapping("/patient/{doctorId}")
	public List<Patient> getPatientList(@PathVariable(name = "doctorId") int doctorId) {
		return doctorService.findPatients(doctorId);
	}
	
	@PostMapping("/login")
	public Doctor login(@RequestBody Doctor d) {
		return doctorService.login(d);
	}
	
	@PostMapping("/workout/{patientId}")
	public List<Workout_instance> addWorkout(@PathVariable(name = "patientId") int patientId, @RequestBody List<Workout> workoutList) {
		System.out.print("Starting the addition of workout instance\n");
		return workoutService.addWorkoutInstances(workoutList, patientId);
	}
	
	@GetMapping("/workout/{patientId}")
	public List<Workout> getWorkoutNotAssigned(@PathVariable(name = "patientId") int patientId) {
		 return workoutService.findWorkoutNotAssigned(patientId);
	}

}
