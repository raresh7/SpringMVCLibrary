package services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDAO;
import entities.Transaction;
import entities.User;

@Service
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUser(String userName) {
		List <User> users = getCurrentSession().getNamedQuery("fetchUserByName").setString("userName", userName).list();
		if(users.isEmpty())
			return null;
		User tempUser = users.get(0);
		Hibernate.initialize(tempUser.getTransactionList());
		return tempUser;
	}
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return getCurrentSession().getNamedQuery("fetchAllUsers").list();
	}
	@Override
	@Transactional
	public User getUserById(int id) {
		User user = (User)getCurrentSession().getNamedQuery("fetchUserById").setInteger("id", id).list().get(0);
		return user;
	}
	@Override
	@Transactional
	public void editUser(User modifiedUser) {
		getCurrentSession().merge(modifiedUser);
	}
	@Override
	@Transactional
	public void deteleUser(int id) {
		getCurrentSession().delete(getUserById(id));
	}
	@Override
	@Transactional
	public void addUser(User newUser) {
		getCurrentSession().save(newUser);		
	}
	@Override
	@Transactional
	public List<Transaction> getTransForUser(User user) {
		
		Hibernate.initialize(user.getTransactionList());
		return user.getTransactionList(); 
	}
	@Override
	@Transactional
	public List<Transaction> getActiveTransForUser(User quser) {
		List<Transaction> allTrans = getTransForUser(quser);
		List<Transaction> relevantTrans = new ArrayList<Transaction>();
		Transaction temp;
		for(int i = 0; i<allTrans.size(); i++){
			temp = allTrans.get(i);
			if(temp.getDateOfReturn() == null)
				relevantTrans.add(temp);
		}
		return relevantTrans;
	}

}
