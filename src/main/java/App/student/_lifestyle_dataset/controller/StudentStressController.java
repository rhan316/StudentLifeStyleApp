package App.student._lifestyle_dataset.controller;

import App.student._lifestyle_dataset.model.StressLevel;
import App.student._lifestyle_dataset.service.StudentStressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentStressController {

	private final StudentStressService service;

	@Autowired
	public StudentStressController(StudentStressService service) {
		this.service = service;
	}

	@GetMapping("/stress")
	public Map<StressLevel, Long> getStressLevels() {
		return service.getStressLevels();
	}

	@GetMapping("/stress-per")
	public Map<StressLevel, Double> getStressLevelsPercent() {
		return service.getStressLevelsPercent();
	}
}
