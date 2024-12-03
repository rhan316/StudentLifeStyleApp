package App.student._lifestyle_dataset.controller;

import App.student._lifestyle_dataset.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAvgController {

	private final StudentService service;

	@Autowired
	public StudentAvgController(StudentService service) {
		this.service = service;
	}

	@GetMapping("/avg-sleep")
	public double getAvgSleepHoursPerDay() {
		return service.getAvgSleepHoursPerDay();
	}

	@GetMapping("/avg-study")
	public double getAvgStudyHoursPerDay() {
		return service.getAvgStudyHoursPerDay();
	}

	@GetMapping("/avg-social")
	public double getAvgSocialHoursPerDay() {
		return service.getAvgSocialHoursPerDay();
	}

	@GetMapping("/avg-all-gpa")
	public double getAvgAllGPA() {
		return service.getAvgAllGPA();
	}

	@GetMapping("/avg-phys")
	public double getAvgPhysicalHoursPerDay() {
		return service.getAvgPhysicalHoursPerDay();
	}

}
