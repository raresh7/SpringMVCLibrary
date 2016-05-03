package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.StudentDAO;
import entities.Student;

@Service
public class StudentDAOImpl implements StudentDAO{
	
	@Autowired
	public SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		getCurrentSession().save(student);

		
	}
	
	public String toString(){
		return "all good";
	}

	@Override
	public void updateStudent(Student modifiedStudent) {
		getCurrentSession().merge(modifiedStudent);
	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
