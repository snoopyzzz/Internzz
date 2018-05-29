package com.zz.entity;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "t_employee", schema = "internzz", catalog = "")
public class Employee extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1079786642824940712L;


	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "entry_time", nullable = false)		//入职时间
    private Date entry_time;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "promoted_time", nullable = false)	//升职时间
    private Date promoted_time;
    
    @Column(name = "level", nullable = false, columnDefinition=" INT default 1 ")	//职级
    private Integer level;

	@ManyToOne
	@JoinColumn(name="department_id", nullable = false)
	@Basic(fetch=FetchType.EAGER)
	
	
	
	private Department department;

	public Date getPromoted_time() {
		return promoted_time;
	}

	public void setPromoted_time(Date promoted_time) {
		this.promoted_time = promoted_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	@JsonIgnore	//在使用List<Employee>转换成json格式的数据传递给前台传递对象时，有多对一属性的加上@JsonIgnore注解，否则会报错（因为两边都在访问）
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
