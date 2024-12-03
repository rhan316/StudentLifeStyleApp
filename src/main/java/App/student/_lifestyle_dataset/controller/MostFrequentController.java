package App.student._lifestyle_dataset.controller;

import App.student._lifestyle_dataset.service.MostFrequentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MostFrequentController {

	private final MostFrequentService service;

	@Autowired
	public MostFrequentController(MostFrequentService service) {
		this.service = service;
	}

	@GetMapping("mf-sleep")
	public double mostFrequentSleep() {
		return service.sleepHoursPerDay();
	}

	@GetMapping("mf-study")
	public double mostFrequentStudy() {
		return service.studyHoursPerDay();
	}

	@GetMapping("/map")
	public Map<Double, Long> getStudyMapHours() {
		return service.getStudyMap();
	}
}
