package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Transaction;
import entities.User;

@Repository
public interface UserDAO {

	public User getUser(String userName);
	public List<User> getAllUsers();
	public User getUserById(int id);
	public void editUser(User modifiedUser);
	public void deteleUser(int id);
	public void addUser(User newUser);
	public List<Transaction> getTransForUser(User user);
	public List<Transaction> getActiveTransForUser(User user);
}
