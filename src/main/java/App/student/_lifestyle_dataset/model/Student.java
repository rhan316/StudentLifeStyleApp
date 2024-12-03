package App.student._lifestyle_dataset.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	private Long studentId;

	private Double studyHoursPerDay;
	private Double extracurricularHoursPerDay;
	private Double sleepHoursPerDay;
	private Double socialHoursPerDay;
	private Double physicalActivityHoursPerDay;
	private Double gpa;
	private StressLevel stressLevel;
}
