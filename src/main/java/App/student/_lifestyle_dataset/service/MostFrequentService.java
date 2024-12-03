package App.student._lifestyle_dataset.service;

import App.student._lifestyle_dataset.dao.StudentRepository;
import App.student._lifestyle_dataset.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MostFrequentService {

	private final StudentRepository studentRepository;

	@Autowired
	public MostFrequentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable("data")
	public List<Student> getData() {
		return studentRepository.findAll();
	}

	public double sleepHoursPerDay() {
		return getMostFrequentValue("sleep");
	}

	public double studyHoursPerDay() {
		return getMostFrequentValue("study");
	}

	public Map<Double, Long> getStudyMap() {
		return getData().stream()
				.sorted(Comparator.comparingDouble(Student::getStudyHoursPerDay))
				.collect(Collectors.groupingBy(Student::getStudyHoursPerDay, Collectors.counting()));
	}

	private double getMostFrequentValue(String name) {
		Map<Double, Long> frequencyMap = getData().stream()
				.collect(Collectors.groupingBy(student -> switch (name) {
					case "sleep" -> student.getSleepHoursPerDay();
					case "study" -> student.getStudyHoursPerDay();
					case "social" -> student.getSocialHoursPerDay();
					case "extra" -> student.getExtracurricularHoursPerDay();
					case "phys" -> student.getPhysicalActivityHoursPerDay();
					default -> throw new IllegalArgumentException("Invalid field name: " + name);
				}, Collectors.counting()));

		Optional<Map.Entry<Double, Long>> mostFrequentEntry = frequencyMap.entrySet().stream()
				.max(Map.Entry.comparingByValue());

		return mostFrequentEntry.map(Map.Entry::getKey).orElse(0.0);
	}
}
