package com.joy.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Order {

	@Column(name = "position", columnDefinition = "Integer default '0'")
	private int position;
}
