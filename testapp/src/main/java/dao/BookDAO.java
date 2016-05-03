package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Book;

@Repository
public interface BookDAO {

	public Book getBookById(int id);
	public List<Book> getAllBooks();
	public List<Book> getAvailableBooks();
	public void insertBook(Book book);
	public void editBook(Book modifiedBook);
	public void deleteBook(int id);
	public void addBook(Book newBook);
}
