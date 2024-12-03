package App.student._lifestyle_dataset.service;

import App.student._lifestyle_dataset.dao.StudentRepository;
import App.student._lifestyle_dataset.model.StressLevel;
import App.student._lifestyle_dataset.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable("data")
	public List<Student> getData() {
		return studentRepository.findAll();
	}

	public List<Student> getData(int amount) {
		return getData().stream()
				.limit(amount)
				.toList();
	}

	public double getAvgSleepHoursPerDay() {
		return getData().stream()
				.mapToDouble(Student::getSleepHoursPerDay)
				.average()
				.orElse(0.0);
	}

	public double getAvgStudyHoursPerDay() {
		return getData().stream()
				.mapToDouble(Student::getStudyHoursPerDay)
				.average()
				.orElse(0.0);
	}

	public double getAvgSocialHoursPerDay() {
		return getData().stream()
				.mapToDouble(Student::getSocialHoursPerDay)
				.average()
				.orElse(0.0);
	}

	public double getAvgAllGPA() {
		return getData().stream()
				.mapToDouble(Student::getGpa)
				.average()
				.orElse(0.0);
	}

	public double getAvgPhysicalHoursPerDay() {
		return getData().stream()
				.mapToDouble(Student::getPhysicalActivityHoursPerDay)
				.average()
				.orElse(0.0);
	}

	public Map<StressLevel, Long> getStressLevels() {
		return getData().stream()
				.collect(Collectors.groupingBy(Student::getStressLevel, Collectors.counting()));
	}

	public Map<StressLevel, Double> getStressLevelsPercent() {
		int studentsSize = getData().size();
		return getData().stream()
				.collect(Collectors.groupingBy(Student::getStressLevel,
					Collectors.collectingAndThen(Collectors.counting(), count -> (count * 100.0) / studentsSize)
						));
	}

}
