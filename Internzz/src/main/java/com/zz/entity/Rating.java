package com.zz.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "t_rating", schema = "internzz", catalog = "")
public class Rating extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8422919991464004564L;
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "requirements", nullable = false)	//升职要求（单位：天数）
    private Integer requirements;
    
    @Column(name = "previous_level")	//先前的等级
    private String previous_level;

	@ManyToOne
	@JoinColumn(name="department_id", nullable = false)
	@Basic(fetch=FetchType.EAGER)
	private Department department;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getRequirements() {
		return requirements;
	}

	public void setRequirements(Integer requirements) {
		this.requirements = requirements;
	}

	public String getPrevious_level() {
		return previous_level;
	}

	public void setPrevious_level(String previous_level) {
		this.previous_level = previous_level;
	}
	@JsonIgnore
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
