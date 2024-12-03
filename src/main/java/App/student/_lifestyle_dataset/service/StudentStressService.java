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
public class StudentStressService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentStressService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable("data")
	public List<Student> getData() {
		return studentRepository.findAll();
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
