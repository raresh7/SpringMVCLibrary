package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@NamedQueries({
	@NamedQuery(
	name = "fetchAllBooks",
	query = "FROM Book b"
	),
	
	@NamedQuery(
	name = "fetchBookById",
	query = "FROM Book b WHERE id = :id"
	),
	
	@NamedQuery(
	name = "fetchAvailableBooks",
	query = "FROM Book b WHERE b.id NOT IN "
			+ 								"(SELECT t.book.id FROM Transaction t WHERE t.dateOfReturn = null)"
	)
})


@Entity
@Table(name="book_lib")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String author;
	private String isbn;
	private String state;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="book")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
