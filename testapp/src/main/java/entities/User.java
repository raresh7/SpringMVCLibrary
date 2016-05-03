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
	name = "fetchUserByName",
	query = "FROM User u WHERE u.userName = :userName"
	),
	@NamedQuery(
	name = "fetchAllUsers",
	query = "FROM User u"
	)
	,
	@NamedQuery(
	name = "fetchUserById",
	query = "FROM User u WHERE u.id = :id"
	)
})

@Entity
@Table(name="libuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String ssn;
	private String address;
	private Boolean admin;
	private int accessLevel;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Transaction> transaction = new ArrayList<Transaction>();
	

	public List<Transaction> getTransactionList(){
		return transaction;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
