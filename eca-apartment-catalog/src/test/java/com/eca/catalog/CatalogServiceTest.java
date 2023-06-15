package com.eca.catalog;

import com.eca.catalog.dto.AddressDTO;
import com.eca.catalog.dto.ApartmentDTO;
import com.eca.catalog.entity.Address;
import com.eca.catalog.entity.Apartments;
import com.eca.catalog.exception.EcaCustomerServiceException;
import com.eca.catalog.repository.AddressRepository;
import com.eca.catalog.repository.CatalogRepository;
import com.eca.catalog.service.impl.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @InjectMocks
    CatalogService catalogService;

    @Mock
    CatalogRepository catalogRepository;

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(catalogService,"modelMapper",modelMapper);
    }

    @Test
    void test_getApartments_valid_exception() {
        when(catalogRepository.findAll()).thenThrow(new RuntimeException("Exception"));
        Exception ex = Assertions.assertThrows(EcaCustomerServiceException.class, ()-> catalogService.getApartments());
        Assertions.assertTrue((ex).getMessage().contains("Exception"));
    }
    @Test
    void test_getApartmentById_valid_exception() {
        when(catalogRepository.findByApartmentId(1L)).thenThrow(new RuntimeException("Exception"));
        Exception ex = Assertions.assertThrows(EcaCustomerServiceException.class, ()-> catalogService.getApartmentById(1));
        Assertions.assertTrue((ex).getMessage().contains("Exception"));
    }

    @Test
    void test_persistApartments() {
        var address = Address.builder()
                .addressId(1L)
                .stateName("JK")
                .build();
        var apartments = Apartments.builder()
                .apartmentId(101L)
                .name("Test")
                .superVisorContact("test")
                .totalFloor(1)
                .address(address)
                .build();
        when(catalogRepository.save(any())).thenReturn(apartments);
        when(addressRepository.save(any())).thenReturn(address);
        var apartmentDTO = new ApartmentDTO();
        apartmentDTO.setAddress(new AddressDTO());
        Assertions.assertEquals(1, catalogService.persistApartments(apartmentDTO));
    }

    @Test
    void test_persistApartments_failed() {
        var apartments = Apartments.builder().build();
        when(catalogRepository.save(any())).thenReturn(apartments);
        var apartmentDTO = new ApartmentDTO();
        assertThatThrownBy(() -> {
                    catalogService.persistApartments(apartmentDTO);
                }).isInstanceOf(EcaCustomerServiceException.class);
        Mockito.verify(addressRepository,times(0)).save(any());
    }

    @Test
    void test_getApartmentById() {
        var address = Address.builder()
                .addressId(1L)
                .stateName("JK")
                .build();
        var apartments = Apartments.builder()
                .apartmentId(101L)
                .name("Test")
                .superVisorContact("test")
                .totalFloor(1)
                .address(address)
                .build();
        when(catalogRepository.findByApartmentId(1L)).thenReturn(apartments);
        assertThat(catalogService.getApartmentById(1))
                .isNotNull();
    }
}
