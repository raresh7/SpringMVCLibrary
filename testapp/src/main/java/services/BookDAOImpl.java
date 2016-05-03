package services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BookDAO;
import entities.Book;

@Service
public class BookDAOImpl  implements BookDAO{

	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Book getBookById(int id) {
		return (Book) getCurrentSession().getNamedQuery("fetchBookById").setParameter("id", id).list().get(0);
	}

	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		List<Book> blist = new ArrayList<Book>();
		blist = getCurrentSession().getNamedQuery("fetchAllBooks").list();
		return blist;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Book> getAvailableBooks() {
		List<Book> blist = new ArrayList<Book>();
		blist = getCurrentSession().getNamedQuery("fetchAvailableBooks").list();
		System.out.println("we have " + blist.size() + " books in the db");
		return blist;
	}

	@Override
	@Transactional
	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		
	}


	@Override
	@Transactional
	public void editBook(Book modifiedBook) {
		getCurrentSession().merge(modifiedBook);
	}


	@Override
	@Transactional
	public void deleteBook(int id) {
		getCurrentSession().delete(getBookById(id));
	}


	@Override
	@Transactional
	public void addBook(Book newBook) {
		getCurrentSession().save(newBook);
	}

}
