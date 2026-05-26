package edu.Vehicles.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.Vehicles.dto.request.CarRequestDto;
import edu.Vehicles.dto.request.UpdatePriceDto;
import edu.Vehicles.dto.response.CarResponseDto;
import edu.Vehicles.entity.Car;
import edu.Vehicles.entity.Owner;
import edu.Vehicles.exception.NotFoundException;
import edu.Vehicles.mapper.CarMapper;
import edu.Vehicles.repository.CarRepository;
import edu.Vehicles.repository.OwnerRepository;
import edu.Vehicles.service.CarService;

@Service
public class CarServiceimpl implements CarService{
	
	private CarRepository carRepository;
	private OwnerRepository ownerRepository;
	private CarMapper mapper;
	

	public CarServiceimpl(CarRepository carRepository, OwnerRepository ownerRepository, CarMapper mapper) {
		super();
		this.carRepository = carRepository;
		this.ownerRepository = ownerRepository;
		this.mapper = mapper;
	}

	@Override
	public CarResponseDto getById(Long id) {
		Car c = carRepository.findById(id).orElseThrow(()-> new NotFoundException("car not found"));
		return mapper.dto(c);
	}

	@Override
	public List<CarResponseDto> getAll() {
		List<Car> cars = carRepository.findAll();
		return cars.stream().map(mapper::dto).collect(Collectors.toList());
	}

	@Override
	public CarResponseDto findByCarNameAndPrice(String name, double price) {
		Car c = carRepository.findByCarNameAndPrice(name, price).orElseThrow(()-> new NotFoundException("Car Not Found"));
		return mapper.dto(c);
	}

	@Override
	public CarResponseDto getLatest() {
		Car c = carRepository.findTopByOrderByIdDesc().orElseThrow(()-> new NotFoundException("Car Not Found"));
		return mapper.dto(c);
	}

	@Override
	public List<CarResponseDto> findByName(String keyword) {
		List<Car> cars = carRepository.findByCarNameContainingIgnoreCase(keyword);
		return cars.stream().map(mapper::dto).collect(Collectors.toList());
	}

	@Override
	public Page<CarResponseDto> getCar(Pageable pageable) {
		Page<Car> cars = carRepository.findAll(pageable);
		return cars.map(mapper::dto);
	}

	@Override
	public CarResponseDto addCar(CarRequestDto dto) {

	    Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(() -> new NotFoundException("Owner Not Found"));
	    Car c = mapper.toEntity(dto);
	    c.setOwner(owner);
	    Car saved = carRepository.save(c);
	    return mapper.dto(saved);
	}

	@Override
	public CarResponseDto updateCarDetails(Long id, CarRequestDto dto) {

	    Car c = carRepository.findById(id)
	            .orElseThrow(() -> new NotFoundException("Car Not Found"));

	    Owner owner = ownerRepository.findById(dto.getOwnerId())
	            .orElseThrow(() -> new NotFoundException("Owner Not Found"));

	    // Manual field update
	    c.setCarName(dto.getCarName());
	    c.setModelName(dto.getModelName());
	    c.setPrice(dto.getPrice());

	    // Relationship update
	    c.setOwner(owner);

	    Car saved = carRepository.save(c);

	    return mapper.dto(saved);
	}

	@Override
	public CarResponseDto updatePrice(Long id, UpdatePriceDto dto) {
		Car c = carRepository.findById(id).orElseThrow(()-> new NotFoundException("Car Not Found"));
		c.setPrice(dto.getPrice());
		Car Updated = carRepository.save(c);
		return mapper.dto(Updated);
	}

	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

}
