package App.student._lifestyle_dataset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentLifestyleDatasetApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentLifestyleDatasetApplication.class, args);
	}
}
