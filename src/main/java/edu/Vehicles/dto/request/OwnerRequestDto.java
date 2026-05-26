package edu.Vehicles.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OwnerRequestDto {
	
	@NotBlank(message = "Name should not be blank")
	private String name;
	@NotBlank(message = "Address should not be blank")
	private String Address;

}
