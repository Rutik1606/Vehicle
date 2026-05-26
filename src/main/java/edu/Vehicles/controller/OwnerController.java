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
import edu.Vehicles.dto.request.OwnerRequestDto;
import edu.Vehicles.dto.request.UpdateAddressDto;
import edu.Vehicles.dto.response.ApiResponseDto;
import edu.Vehicles.dto.response.OwnerResponseDto;
import edu.Vehicles.dto.response.PagedResponseDto;
import edu.Vehicles.service.OwnerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> getById(@PathVariable Long id){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Owner Fetch Successfully",ownerService.getById(id)));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseDto<List<OwnerResponseDto>>> getAll(){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Owner Fetch Successfully",ownerService.getAll()));
	}
	
	@GetMapping({"/pagination"})
	public ResponseEntity<ApiResponseDto<PagedResponseDto<OwnerResponseDto>>> getProducts(@ParameterObject Pageable p){
		Page<OwnerResponseDto> owners = ownerService.getOwner(p); 
		return ResponseEntity.ok(ApiResponseDto.success(200,"Product Fetch Successfully", PagedResponseDto.buildPage(owners)));
	}
	
	@GetMapping({"/search-By-Name-And-Address"})
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> getByNameAndAddress(@RequestParam String name,@RequestParam String address){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Owner Fetch Successfully",ownerService.findByNameAndAddress(name, address)));
	}
	
	@GetMapping({"/search-By-Name"})
	public ResponseEntity<ApiResponseDto<List<OwnerResponseDto>>> getByName(@RequestParam String Keyword){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"Owner fetch Succesfully", ownerService.findByName(Keyword)));
	}
	
	@GetMapping({"/latest"})
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> getLatest(){
		return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK.value(),"fetch Latest Record", ownerService.getLatest()));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> addOwner(@RequestBody @Valid OwnerRequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.OK.value(),"Owner Add Successfully",ownerService.addOwner(dto)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> updateOwner(@PathVariable Long id, @RequestBody @Valid OwnerRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED.value(),
				"Owner Updated Succesfully",ownerService.updateOwnerDetails(id, dto) ));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseDto<OwnerResponseDto>> updateAddress(@PathVariable Long id, @RequestBody @Valid UpdateAddressDto dto) {
	 return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED.value(),
				"Address Updated Succesfully", ownerService.updateAddress(id, dto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		ownerService.deleteOwner(id);
		return ResponseEntity.noContent().build();
	}
}
