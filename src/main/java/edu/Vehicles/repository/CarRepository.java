package edu.Vehicles.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.Vehicles.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	//Created for find the car by Name and price
	  Optional<Car> findByCarNameAndPrice(String name, double price);
	  
	  //created for find the latest car
	  Optional<Car> findTopByOrderByIdDesc();
	  
	  //Created for find by name
	  List<Car> findByCarNameContainingIgnoreCase(String keyword);
}
