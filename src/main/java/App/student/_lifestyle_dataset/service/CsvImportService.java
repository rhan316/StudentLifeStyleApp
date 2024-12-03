package App.student._lifestyle_dataset.service;

import App.student._lifestyle_dataset.dao.StudentRepository;
import App.student._lifestyle_dataset.model.StressLevel;
import App.student._lifestyle_dataset.model.Student;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService implements ApplicationRunner {

	private final StudentRepository studentRepository;
	private static final String CSV_FILE_PATH = "src/main/java/App/student/_lifestyle_dataset/csv/student_lifestyle_dataset.csv";

	@Autowired
	public CsvImportService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		var students = getCachedStudents();
		studentRepository.saveAll(students);
	}

	@Cacheable("students")
	public List<Student> getCachedStudents() {
		return readCsvData();
	}

	private List<Student> readCsvData() {
		List<Student> students = new ArrayList<>();

		try (var csvReader = new CSVReader(new FileReader(CsvImportService.CSV_FILE_PATH))) {

			String[] rowData;
			csvReader.readNext(); // Pomijamy nagłówek

			while ((rowData = csvReader.readNext()) != null) {
				Long studentId = Long.parseLong(rowData[0]);
				var student = getStudent(rowData, studentId);
				students.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	private static Student getStudent(String[] rowData, Long studentId) {
		var studyHoursPerDay = Double.parseDouble(rowData[1]);
		var extracurricularHoursPerDay = Double.parseDouble(rowData[2]);
		var sleepHoursPerDay = Double.parseDouble(rowData[3]);
		var socialHoursPerDay = Double.parseDouble(rowData[4]);
		var physicalActivityHoursPerDay = Double.parseDouble(rowData[5]);
		var gpa = Double.parseDouble(rowData[6]);
		var stressLevel = rowData[7];

		return new Student(
				studentId, studyHoursPerDay, extracurricularHoursPerDay, sleepHoursPerDay,
				socialHoursPerDay, physicalActivityHoursPerDay, gpa, StressLevel.valueOf(stressLevel.toUpperCase())
		);
	}
}
