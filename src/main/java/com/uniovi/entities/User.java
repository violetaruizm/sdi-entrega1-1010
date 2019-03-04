package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.uniovi.entities.types.Role;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	private String surname;
	
	private String password;
	
	@Transient
	private String passwordConfirm;
	private double money;
	private boolean valid;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "owner")
	private Set<Sale> publishedSales = new HashSet<Sale>();
	
	@OneToMany(mappedBy = "buyer")
	private Set<Sale> boughtSales = new HashSet<Sale>();
	
	@OneToMany(mappedBy = "sender")
	private Set<Message> sentMessages = new HashSet<Message>();
	
	@OneToMany(mappedBy = "receiver")
	private Set<Message> receivedMessages = new HashSet<Message>();
	
	public User() {
		
	}

	public long getId() {
		return id;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String repassword) {
		this.passwordConfirm = repassword;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Sale> getPublishedSales() {
		return publishedSales;
	}

	public void setPublishedSales(Set<Sale> publishedSales) {
		this.publishedSales = publishedSales;
	}

	public Set<Sale> getBoughtSales() {
		return boughtSales;
	}

	public void setBoughtSales(Set<Sale> boughtSales) {
		this.boughtSales = boughtSales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", password=" + password
				+ ", repassword=" + passwordConfirm + ", money=" + money + ", valid="
				+ valid + ", role=" + role + ", publishedSales="
				+ publishedSales + ", boughtSales=" + boughtSales + "]";
	}
	

}
