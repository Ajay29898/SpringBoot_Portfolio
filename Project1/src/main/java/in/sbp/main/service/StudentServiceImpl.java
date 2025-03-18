package in.sbp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sbp.main.entity.Student;
import in.sbp.main.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository stdRepository;

	@Override
	public boolean registerStudent(Student std) 
	{
		try
		{
			stdRepository.save(std);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Student loginStudent(String email, String password) 
	{
		Student validStd = stdRepository.findByEmail(email);
		
		if(validStd != null && validStd.getPassword().equals(password))
		{
			return validStd;
		}
		return null;
	}
}
