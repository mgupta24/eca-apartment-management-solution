package com.eca.catalog.service;

import java.util.List;

import com.eca.catalog.dto.ApartmentDTO;

public interface ICatalogService {

    public List<ApartmentDTO> getApartments();
    
    public int persistApartments(ApartmentDTO apartments);
    
    public ApartmentDTO getApartmentById(long id);

    
}
