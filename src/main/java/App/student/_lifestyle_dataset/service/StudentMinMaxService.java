package App.student._lifestyle_dataset.service;

import App.student._lifestyle_dataset.dao.StudentRepository;
import App.student._lifestyle_dataset.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StudentMinMaxService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentMinMaxService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable("data")
	public List<Student> getData() {
		return studentRepository.findAll();
	}

	@Cacheable("studyStats")
	public DoubleSummaryStatistics studyStats() {
		return getData().stream()
				.mapToDouble(Student::getStudyHoursPerDay)
				.summaryStatistics();
	}

	@Cacheable("sleepStats")
	public DoubleSummaryStatistics sleepStats() {
		return getData().stream()
				.mapToDouble(Student::getSleepHoursPerDay)
				.summaryStatistics();
	}

	@Cacheable("socialStats")
	public DoubleSummaryStatistics socialStats() {
		return getData().stream()
				.mapToDouble(Student::getSocialHoursPerDay)
				.summaryStatistics();
	}

	@Cacheable("extraCurrStats")
	public DoubleSummaryStatistics extraCurrStats() {
		return getData().stream()
				.mapToDouble(Student::getExtracurricularHoursPerDay)
				.summaryStatistics();
	}

	@Cacheable("physicalStats")
	public DoubleSummaryStatistics physiacalStats() {
		return getData().stream()
				.mapToDouble(Student::getPhysicalActivityHoursPerDay)
				.summaryStatistics();
	}

	public double getStudyMin() {
		return studyStats().getMin();
	}

	public double getStudyMax() {
		return studyStats().getMax();
	}

	public double getSleepMin() {
		return sleepStats().getMin();
	}

	public double getSleepMax() {
		return sleepStats().getMax();
	}

	public double getSocialMin() {
		return socialStats().getMin();
	}

	public double getSocialMax() {
		return socialStats().getMax();
	}

	public double getExtraCurrMin() {
		return extraCurrStats().getMin();
	}

	public double getExtraCurrMax() {
		return extraCurrStats().getMax();
	}

	public double getPhysicalMin() {
		return physiacalStats().getMin();
	}

	public double getPhysicalMax() {
		return physiacalStats().getMax();
	}
}
