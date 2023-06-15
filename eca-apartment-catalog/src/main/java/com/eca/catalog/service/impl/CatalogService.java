package com.eca.catalog.service.impl;

import com.eca.catalog.dto.ApartmentDTO;
import com.eca.catalog.entity.Address;
import com.eca.catalog.entity.Apartments;
import com.eca.catalog.exception.EcaCustomerServiceException;
import com.eca.catalog.repository.AddressRepository;
import com.eca.catalog.repository.CatalogRepository;
import com.eca.catalog.service.ICatalogService;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CatalogService implements ICatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	ModelMapper modelMapper;

	@SuppressWarnings("serial")
	@Override
	public List<ApartmentDTO> getApartments() {
		Type listType = new TypeToken<List<ApartmentDTO>>() {}.getType();
		try {
			log.info("inside the CatalogService::getApartments");
			return modelMapper.map(catalogRepository.findAll(), listType);
		} catch (Exception e) {
			log.error("error inside the CatalogService::getApartments", e);
			throw new EcaCustomerServiceException(e);
		}
	}

	@Override
	public int persistApartments(ApartmentDTO apartmentDTO) {
		try {
			log.info("inside the CatalogService::persistApartments");
			var apartments = modelMapper.map(apartmentDTO, Apartments.class);
			apartments.setRegisterDate(new Date());
			var perApart = catalogRepository.save(apartments);
			Address address = modelMapper.map(apartmentDTO.getAddress(), Address.class);
			address.setApartments(perApart);
			addressRepository.save(address);
			log.info("insert success::persistApartments");
			return 1;
		} catch (Exception e) {
			log.error("error inside the CatalogService::persistApartments", e);
			throw new EcaCustomerServiceException(e);
		}
	}

	@Override
	public ApartmentDTO getApartmentById(long id) {
		try {
			log.info("inside the CatalogService::getApartmentById {} ", id);
			var apartments = catalogRepository.findByApartmentId(id);
			log.info("CatalogService::getApartmentById response {} ",apartments);
			return modelMapper.map(apartments, ApartmentDTO.class);
		} catch (Exception e) {
			log.error("error inside the CatalogService::getApartmentById", e);
			throw new EcaCustomerServiceException(e);
		}
	}
}
