package edu.Vehicles.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.Vehicles.dto.request.CarRequestDto;
import edu.Vehicles.dto.response.CarResponseDto;
import edu.Vehicles.entity.Car;

@Component
public class CarMapper {

	@Autowired
	private ModelMapper mapper;

	// Entity -> Response DTO
	public CarResponseDto dto(Car car) {

		CarResponseDto dto = mapper.map(car, CarResponseDto.class);
		if (car.getOwner() != null) {
			dto.setOwnerName(car.getOwner().getName());
		}
		return dto;
	}

	//set new car to owner
	public Car toEntity(CarRequestDto dto) {

	    Car car = new Car();

	    car.setCarName(dto.getCarName());
	    car.setModelName(dto.getModelName());
	    car.setPrice(dto.getPrice());

	    return car;
	}

    // Update Existing Entity
    public void map(CarRequestDto dto, Car car) {
        mapper.map(dto, car);
    }
}