package com.eca.catalog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APARTMENT")
@Builder
public class Apartments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APARTMENT_ID")
	private Long apartmentId;

	@Column(name = "APART_NAME")
	private String name;

	@Column(name = "SUP_VISOR_NAME")
	private String superVisorName;

	@Column(name = "TOTAL_FLOOR")
	private int totalFloor;

	@Column(name = "SUP_VISOR_CONTACT")
	private String superVisorContact;

	@Column(name = "REG_DATE")
	private Date registerDate;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "apartments", cascade = {CascadeType.ALL})
    private Address address;

}
