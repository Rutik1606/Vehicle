package edu.Vehicles.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.Vehicles.dto.request.OwnerRequestDto;
import edu.Vehicles.dto.request.UpdateAddressDto;
import edu.Vehicles.dto.response.OwnerResponseDto;

public interface OwnerService  {

	public OwnerResponseDto getById(Long id);
	
	public List<OwnerResponseDto> getAll();
	
	public OwnerResponseDto findByNameAndAddress(String name, String address);
	
	public OwnerResponseDto getLatest();
	
	public List<OwnerResponseDto> findByName(String keyword);
	
	//Pagination Method for Owner
	public Page<OwnerResponseDto> getOwner(Pageable p);
	
	public OwnerResponseDto addOwner(OwnerRequestDto dto);
	
	public OwnerResponseDto updateOwnerDetails(Long id, OwnerRequestDto dto);
	
	public OwnerResponseDto updateAddress(Long id,UpdateAddressDto dto);
	
	public void deleteOwner(Long id);
	
}
