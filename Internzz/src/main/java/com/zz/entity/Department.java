package com.zz.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_department", schema = "internzz", catalog = "")
public class Department extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9177364521780894912L;

	
	@Id 
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")  
	private String id;


    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "address")
    private String address;
    
    @OneToMany(mappedBy="department",cascade={CascadeType.ALL},fetch=FetchType.EAGER)   
    private Set<Employee> user = new HashSet<Employee>();
    
    @OneToMany(mappedBy="department",cascade={CascadeType.ALL},fetch=FetchType.EAGER)   
    private Set<Rating> rating = new HashSet<Rating>();
  
    
    public Set<Rating> getRating() {
		return rating;
	}

	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Employee> getUser() {
		return user;
	}

	public void setUser(Set<Employee> user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
