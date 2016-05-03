package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Student;

@Repository
public interface StudentDAO {
	
	public void addStudent(Student student);
	public void updateStudent(Student modifiedStudent);
	public void deleteStudent(Student student);
	public List<Student> getStudents();
	
}
