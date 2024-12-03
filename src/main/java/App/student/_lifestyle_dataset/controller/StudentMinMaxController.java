package App.student._lifestyle_dataset.controller;

import App.student._lifestyle_dataset.service.StudentMinMaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentMinMaxController {

	private final StudentMinMaxService service;

	@Autowired
	public StudentMinMaxController(StudentMinMaxService service) {
		this.service = service;
	}

	@GetMapping("/study-min")
	public double getMinStudyHours() {
		return service.getStudyMin();
	}

	@GetMapping("/study-max")
	public double getMaxStudyHours() {
		return service.getStudyMax();
	}

	@GetMapping("/sleep-min")
	public double getMinSleepHours() {
		return service.getSleepMin();
	}

	@GetMapping("/sleep-max")
	public double getMaxSleepHours() {
		return service.getSleepMax();
	}

	@GetMapping("/social-min")
	public double getMinSocialHours() {
		return service.getSocialMin();
	}

	@GetMapping("/social-max")
	public double getMaxSocialHours() {
		return service.getSocialMax();
	}

	@GetMapping("/curr-min")
	public double getMinExtraCurrHours() {
		return service.getExtraCurrMin();
	}

	@GetMapping("/curr-max")
	public double getMaxExtraCurrHours() {
		return service.getExtraCurrMax();
	}

	@GetMapping("/phys-min")
	public double getMinPhysicalHours() {
		return service.getPhysicalMin();
	}

	@GetMapping("/phys-max")
	public double getMaxPhysicalHours() {
		return service.getPhysicalMax();
	}
}
