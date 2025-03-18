package in.sbp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.sbp.main.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
	Student findByEmail(String email);
}
