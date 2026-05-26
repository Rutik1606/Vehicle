package edu.Vehicles.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.Vehicles.dto.request.OwnerRequestDto;
import edu.Vehicles.dto.response.OwnerResponseDto;
import edu.Vehicles.entity.Owner;

@Component
public class OwnerMapper {

	@Autowired
	private ModelMapper mapper;
	
	public OwnerResponseDto toDto(Owner owner) {
		return mapper.map(owner, OwnerResponseDto.class);
	}
	
	public Owner toEntity(OwnerRequestDto ownerRequestDto) {
		return mapper.map(ownerRequestDto, Owner.class);
	}
	
	public void map(OwnerRequestDto dto, Owner o){
		mapper.map(dto, o);
	}
}
