package dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Transaction;

@Repository
public interface TransDAO {

	public List<Transaction> getAllTrans();
	public Transaction getTransById(int id);
	public void deleteTrans(int id);
	public void editTrans(Transaction modifiedTrans);
	public void addTrans(Transaction newTrans);
	public List<Transaction> activeTrans();
	public void returnBooks(List<Integer> ids, LocalDate date);
}
