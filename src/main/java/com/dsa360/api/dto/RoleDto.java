package com.dsa360.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RAM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	private String id;
	@NotBlank(message = "Role name is required")
	@Size(min = 3, max = 30, message = "Role name should be between 3 and 30 characters")
	@Pattern(regexp = "^[A-Z_]+$", message = "Role name should contain only uppercase letters and underscores (e.g., ADMIN, USER_ROLE)")
	private String name;

}
