package com.newhouse.wframe.core.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.newhouse.wframe.core.entity.User.ListUserView;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity(name="tdepartment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Department implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837805998152905245L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", unique=true, nullable=false, insertable=true, updatable=true)
	private String name;
	
	
	public Department() {
		super();
	}   
	public Department(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	
	@JsonView(ListUserView.class)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
