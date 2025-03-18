package in.sbp.main.service;

import in.sbp.main.entity.Student;

public interface StudentService 
{
	public boolean registerStudent(Student std);
	public Student loginStudent(String email, String password);
}
