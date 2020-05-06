package com.websystique.springmvc.model;

import java.math.BigDecimal;

//ola ta persistence exoun sxesi me ti basi
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//edw kanw validation sta stoixeia pou erxontai apo front end
//ara kanei validate to back end
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    //otan prospathisei kapoios na perasei mesa sto object kati pou den mporei na perasei skaei
    //diladi skaei sto new Employee h sto setName an paw na balw 51 characters name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=3, max=50)
	@Column(name = "NAME", nullable = false)
	private String name;

        //an allaksw kati me kwdika edw tha kanei alter table sti basi
        //an auta pou tha allaksw einai tou javax.persistance
        //ta upoloipa einai validations tou hibernate?
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "JOINING_DATE", nullable = false)
        //to @Column einai gia ti basi to nullable!
        //to @NotNull einai gia ti java to validation
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate joiningDate;

	@NotNull
	@Digits(integer=8, fraction=2)
        //8 xaraktires kai 2 dekadika
	@Column(name = "SALARY", nullable = false)
	private BigDecimal salary;
	
	@NotEmpty
        //notEmpty einai k auto sto hibernate
        //diladi not empty string to opoio einai <> null
        //uperkalyptei to notNull
	@Column(name = "SSN", unique=true, nullable = false)
        //unique einai monadiko san AFM
	private String ssn;

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

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joiningDate="
				+ joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
	}
	
	
	

}
