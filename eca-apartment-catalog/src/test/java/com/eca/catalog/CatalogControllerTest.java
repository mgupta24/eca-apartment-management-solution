package com.eca.catalog;

import com.eca.catalog.controller.CatalogController;
import com.eca.catalog.dto.AddressDTO;
import com.eca.catalog.dto.ApartmentDTO;
import com.eca.catalog.service.impl.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CatalogControllerTest {

	@InjectMocks
	private CatalogController catalogController;

	@Mock
	private CatalogService catalogService;

	@Test
	void test_getApartments_valid() {
		when(catalogService.getApartments()).thenReturn(getApartmentDetails());
		List<ApartmentDTO> apartmentDTOS = catalogController.getApartments();
		Assertions.assertNotNull(apartmentDTOS);

	}

	@Test
	void test_persistApartments_valid() {
		var apartmentDto = getApartmentDto();
		when(catalogService.persistApartments(apartmentDto)).thenReturn(1);
		var response = catalogController.persistApartments(apartmentDto);
		Assertions.assertEquals("succesfully register !!!", response);
	}

	@Test
	void test_getApartmentById_valid() {
		when(catalogService.getApartmentById(1)).thenReturn(getApartmentDto());
		var response = catalogController.getApartmentById(1L);
		Assertions.assertNotNull(response);
	}

	private List<ApartmentDTO> getApartmentDetails() {
		var apartmentDTOS = new ArrayList<ApartmentDTO>();
		var apartmentDto = getApartmentDto();
		apartmentDTOS.add(apartmentDto);
		return apartmentDTOS;
	}

	private ApartmentDTO getApartmentDto() {
		var apartmentDto = new ApartmentDTO();
		apartmentDto.setName("test apartment");
		apartmentDto.setSuperVisorContact("876542345");
		apartmentDto.setSuperVisorName("ann");
		apartmentDto.setTotalFloor(5);
		AddressDTO addressDto = new AddressDTO();
		addressDto.setCityName("test");
		addressDto.setCountryName("india");
		addressDto.setStreetName("rj nagar");
		apartmentDto.setAddress(addressDto);
		return apartmentDto;
	}

}
