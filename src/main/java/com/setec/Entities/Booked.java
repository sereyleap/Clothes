package com.setec.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="tbl_booked")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booked {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String phoneNumber;
	private String email;
	private String date;
	private String time;
	private int person;
}
