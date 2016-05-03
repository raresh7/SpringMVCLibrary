package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TransDAO;
import entities.Transaction;

@Service
public class TransDAOImpl implements TransDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Transaction> getAllTrans() {
		return getCurrentSession().getNamedQuery("fetchAllTrans").list();
	}

	@Override
	@Transactional
	public void deleteTrans(int id) {
		getCurrentSession().delete(getTransById(id));		
	}

	@Override
	@Transactional
	public Transaction getTransById(int id) {
		return (Transaction) getCurrentSession().getNamedQuery("fetchTransById").setParameter("id", id).list().get(0);
	}

	@Override
	@Transactional
	public void editTrans(Transaction modifiedTrans) {
		getCurrentSession().merge(modifiedTrans);
		
	}

	@Override
	@Transactional
	public void addTrans(Transaction newTrans) {
		getCurrentSession().save(newTrans);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Transaction> activeTrans() {
		return getCurrentSession().getNamedQuery("fetchActiveTrans").list();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void returnBooks(List<Integer> ids, LocalDate date) {
		List <Transaction> receiveTrans = new ArrayList<Transaction>();
		receiveTrans = (List<Transaction>)getCurrentSession().getNamedQuery("fetchTransByIdList").setParameterList("idlist", ids).list();
		for(int i = 0; i < receiveTrans.size();i++){
			receiveTrans.get(i).setDateOfReturn(date);
			getCurrentSession().merge(receiveTrans.get(i));
		}
	}

	
}
