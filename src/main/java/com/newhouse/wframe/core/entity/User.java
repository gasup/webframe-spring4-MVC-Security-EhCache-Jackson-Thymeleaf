package com.newhouse.wframe.core.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.newhouse.wframe.common.pojo.JsonResult.JsonResultView;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity(name="tuser")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User implements Serializable {
	
	public interface ListUserView extends JsonResultView{};
	public interface DetailUserView extends ListUserView{};
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605452670569979798L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")	
	private Long id;
	
	@Column(name="username", unique=true, nullable=false, insertable=true, updatable=true)
	private String username;
	
	@Column(name="password", nullable=false, insertable=true, updatable=true)
	private String password;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="tuser_role",
		joinColumns={
			@JoinColumn(name="user_id", referencedColumnName="id")
		},
		inverseJoinColumns={
			@JoinColumn(name="role_id", referencedColumnName="id")
		}
	)
	private Set<Role> roles = new HashSet<Role>();


	public User() {
		super();
	}
	
	@JsonView(ListUserView.class)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonView(ListUserView.class)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonView(DetailUserView.class)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@JsonView(ListUserView.class)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
