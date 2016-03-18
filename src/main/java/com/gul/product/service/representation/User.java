package com.gul.product.service.representation;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.User.findUser",
            query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password"
    )
})
public class User {
	
	private String username;
	private String password;
	
	public User(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
