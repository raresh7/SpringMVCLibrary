package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import config.DateConverter;

@NamedQueries({
	@NamedQuery(
	name = "fetchAllTrans",
	query = "FROM Transaction t"
	),
	
	@NamedQuery(
	name = "fetchTransById",
	query = "FROM Transaction t WHERE t.id = :id"
	),
	
	@NamedQuery(
	name = "fetchTransByIdList",
	query = "FROM Transaction t WHERE t.id IN (:idlist)"
	),
	
	
	@NamedQuery(
	name = "fetchActiveTrans",
	query = "FROM Transaction t WHERE t.dateOfReturn = null"
	)
})


@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(targetEntity=Book.class)
	private Book book;
	@ManyToOne(targetEntity=User.class)
	private User user;
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate dateOfBorrow;
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate expectedDateOfReturn;
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate dateOfReturn;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getDateOfBorrow() {
		return dateOfBorrow;
	}
	public void setDateOfBorrow(LocalDate dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}
	public LocalDate getExpectedDateOfReturn() {
		return expectedDateOfReturn;
	}
	public void setExpectedDateOfReturn(LocalDate expectedDateOfReturn) {
		this.expectedDateOfReturn = expectedDateOfReturn;
	}
	public LocalDate getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
