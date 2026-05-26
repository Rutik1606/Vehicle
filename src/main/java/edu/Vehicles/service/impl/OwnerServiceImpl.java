package edu.Vehicles.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.Vehicles.dto.request.OwnerRequestDto;
import edu.Vehicles.dto.request.UpdateAddressDto;
import edu.Vehicles.dto.response.OwnerResponseDto;
import edu.Vehicles.entity.Owner;
import edu.Vehicles.exception.NotFoundException;
import edu.Vehicles.mapper.OwnerMapper;
import edu.Vehicles.repository.OwnerRepository;
import edu.Vehicles.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	private OwnerRepository ownerRepository;
	private OwnerMapper mapper;
	
	
	public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper mapper) {
		super();
		this.ownerRepository = ownerRepository;
		this.mapper = mapper;
	}

	@Override
	public OwnerResponseDto getById(Long id) {
		Owner o = ownerRepository.findById(id).orElseThrow(() -> new NotFoundException("Owner Not Found"));
		return mapper.toDto(o);
	}

	@Override
	public List<OwnerResponseDto> getAll() {
		List<Owner> owners = ownerRepository.findAll();
		return owners.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public OwnerResponseDto findByNameAndAddress(String name, String address) {
		Owner o = ownerRepository.findByNameAndAddress(name, address).orElseThrow(()-> new NotFoundException("Owner not found"));
		return mapper.toDto(o);
	}

	@Override
	public OwnerResponseDto getLatest() {
		Owner o = ownerRepository.findTopByOrderByIdDesc().orElseThrow(()-> new NotFoundException("Owner Not Found"));
		return mapper.toDto(o);
	}

	@Override
	public List<OwnerResponseDto> findByName(String keyword) {
		List<Owner> owners = ownerRepository.findByNameContainingIgnoreCase(keyword);
		return owners.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<OwnerResponseDto> getOwner(Pageable p) {
		Page<Owner> owners = ownerRepository.findAll(p);
		return owners.map(mapper::toDto);
	}

	@Override
	public OwnerResponseDto addOwner(OwnerRequestDto dto) {
		Owner o = mapper.toEntity(dto);
		Owner saved = ownerRepository.save(o);
		return mapper.toDto(saved);
	}

	@Override
	public OwnerResponseDto updateOwnerDetails(Long id, OwnerRequestDto dto) {
	    Owner o = ownerRepository.findById(id).orElseThrow(() -> new NotFoundException("Owner not found"));
	    o.setName(dto.getName());
	    o.setAddress(dto.getAddress());
	    Owner saved = ownerRepository.save(o);
	    return mapper.toDto(saved);
	}

	@Override
	public OwnerResponseDto updateAddress(Long id, UpdateAddressDto dto) {
		Owner o = ownerRepository.findById(id).orElseThrow(()-> new NotFoundException("Owner Not Found"));
		o.setAddress(dto.getAddress());
		Owner updated = ownerRepository.save(o);
		return mapper.toDto(updated);
	}

	@Override
	public void deleteOwner(Long id) {
		ownerRepository.deleteById(id);
	}

}
