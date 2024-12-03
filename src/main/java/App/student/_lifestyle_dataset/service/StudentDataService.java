package App.student._lifestyle_dataset.service;

import App.student._lifestyle_dataset.dao.StudentRepository;
import App.student._lifestyle_dataset.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDataService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentDataService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable("data")
	public List<Student> getData() {
		return studentRepository.findAll();
	}

	public List<Student> getAllData() {
		return getData();
	}

	public List<Student> getAmountOfData(int amount) {
		return getData().stream()
				.limit(amount)
				.toList();
	}

	public int numberOfStudents() {
		return getData().size();
	}

	public Student getStudentById(int id) {
		return getData().get(id - 1);
	}
}
