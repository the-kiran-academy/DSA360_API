package com.dsa360.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RAM
 */
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity {
	@Id
	private String id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public RoleEntity(String id) {
		this.id = id;
	}

}
