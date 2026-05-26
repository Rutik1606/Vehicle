package edu.Vehicles.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CarRequestDto {

	private Long ownerId;
	@NotBlank(message = "Car name should not be blank")
	private String carName;
	@NotBlank(message = "Model name should not be blank")
	private String modelName;
	@Positive(message = "Price must be greater than 0")
	private Double price;

}
