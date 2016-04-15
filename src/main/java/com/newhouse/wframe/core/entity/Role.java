package com.newhouse.wframe.core.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.newhouse.wframe.core.entity.User.DetailUserView;
import com.newhouse.wframe.core.entity.User.ListUserView;

/**
 * Entity implementation class for Entity: UserRole
 *
 */
@Entity(name="trole")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Role implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6818015197954526351L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="value", unique=true, nullable=false, insertable=true, updatable=true)
	private String value;
	
	@Column(name="name", unique=true, nullable=false, insertable=true, updatable=true)
	private String name;
	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<User> users = new HashSet<User>();  

	public Role() {
		super();
	}   
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User>  users) {
		this.users = users;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@JsonView(DetailUserView.class)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
