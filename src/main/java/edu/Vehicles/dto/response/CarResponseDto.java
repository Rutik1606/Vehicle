package edu.Vehicles.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {

	private long id;
	private String ownerName;
	private String carName;
	private String modelName;
	private double price;
	
}
