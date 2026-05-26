package edu.Vehicles.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.Vehicles.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	  
	  //Created for find the owner by address
	  Optional<Owner> findByNameAndAddress(String name, String address);
	  
	  //created for find the latest Owner
	  Optional<Owner> findTopByOrderByIdDesc();
	  
	  //Created for find by name
	  List<Owner> findByNameContainingIgnoreCase(String keyword);
	  
	  

}
