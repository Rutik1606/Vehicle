package edu.Vehicles.controller;

import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.Vehicles.dto.request.CarRequestDto;
import edu.Vehicles.dto.request.UpdatePriceDto;
import edu.Vehicles.dto.response.ApiResponseDto;
import edu.Vehicles.dto.response.CarResponseDto;
import edu.Vehicles.dto.response.PagedResponseDto;
import edu.Vehicles.service.CarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarsController {
	
	@Autowired
	private CarService carservice;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<CarResponseDto>> getById(@PathVariable Long id){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Car Fetch Successfully",carservice.getById(id)));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseDto<List<CarResponseDto>>> getAll(){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Car Fetch Successfully",carservice.getAll()));
	}
	
	@GetMapping("/pagenation")
	public ResponseEntity<ApiResponseDto<PagedResponseDto<CarResponseDto>>> getProducts(@ParameterObject Pageable p){
		Page<CarResponseDto> Cars = carservice.getCar(p); 
		return ResponseEntity.ok(ApiResponseDto.success(200,"Product Fetch Successfully", PagedResponseDto.buildPage(Cars)));
	}
	
	@GetMapping("/search-By-Name-And-Price")
	public ResponseEntity<ApiResponseDto<CarResponseDto>> getByNameAndPrice(@RequestParam String name, double price){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Car Fetch Successfully",carservice.findByCarNameAndPrice(name,price)));
	}
	
	@GetMapping("/Search-By-Name")
	public ResponseEntity<ApiResponseDto<List<CarResponseDto>>> getByName(@RequestParam String Keyword){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Car fetch Succesfully", carservice.findByName(Keyword)));
	}
	
	@GetMapping("/latest")
	public ResponseEntity<ApiResponseDto<CarResponseDto>> getLatest(){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"fetch Latest Record", carservice.getLatest()));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<CarResponseDto>> addCar(@RequestBody @Valid CarRequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.OK.value(),"Car Add Successfully",carservice.addCar(dto)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<CarResponseDto>> updateCar(@PathVariable Long id, @RequestBody @Valid CarRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED.value(),
				"Car Updated Succesfully",carservice.updateCarDetails(id, dto) ));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseDto<CarResponseDto>> updateAddress(@PathVariable Long id, @RequestBody @Valid UpdatePriceDto dto) {
	 return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED.value(),
				"Address Updated Succesfully", carservice.updatePrice(id, dto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		carservice.deleteCar(id);
		return ResponseEntity.noContent().build();
	}

}
