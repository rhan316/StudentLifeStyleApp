package App.student._lifestyle_dataset.controller;

import App.student._lifestyle_dataset.model.Student;
import App.student._lifestyle_dataset.service.StudentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentDataController {

	private final StudentDataService service;

	@Autowired
	public StudentDataController(StudentDataService service) {
		this.service = service;
	}

	@GetMapping("/data-all")
	public List<Student> getAllData() {
		return service.getAllData();
	}

	@GetMapping("/data/{amount}")
	public List<Student> getAmountOfData(@PathVariable int amount) {
		return service.getAmountOfData(amount);
	}

	@GetMapping("/students")
	public int numberOfStudents() {
		return service.numberOfStudents();
	}

	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		return service.getStudentById(id);
	}
}
