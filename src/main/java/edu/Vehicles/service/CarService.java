package edu.Vehicles.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.Vehicles.dto.request.CarRequestDto;
import edu.Vehicles.dto.request.UpdatePriceDto;
import edu.Vehicles.dto.response.CarResponseDto;

public interface CarService {

	public CarResponseDto getById(Long id);

	public List<CarResponseDto> getAll();

	public CarResponseDto findByCarNameAndPrice(String name, double price);

	public CarResponseDto getLatest();

	public List<CarResponseDto> findByName(String keyword);

	// Pagination
	public Page<CarResponseDto> getCar(Pageable pageable);

	public CarResponseDto addCar(CarRequestDto dto);

	public CarResponseDto updateCarDetails(Long id, CarRequestDto dto);

	public CarResponseDto updatePrice(Long id, UpdatePriceDto dto);

	public void deleteCar(Long id);
}