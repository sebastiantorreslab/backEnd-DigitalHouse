package com.app.service.impl;

import com.app.dto.AddressDto;
import com.app.entity.Address;
import com.app.repository.AddressRepository;
import com.app.service.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements IAddressService  {

    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private final AddressRepository addressRepository;

    @Autowired
    ObjectMapper mapper;


    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<AddressDto> findAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        List<AddressDto> addressDtoList = new ArrayList<>();
        for(Address address: addressList){
            AddressDto addressDto = mapper.convertValue(address,AddressDto.class);
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        if(addressDto != null){
            Address address = mapper.convertValue(addressDto,Address.class);
            addressRepository.save(address);
        }

    }


    @Override
    public Optional<AddressDto> findAddressById(Long id) {
        AddressDto addressDto = null;
       Address address = addressRepository.findById(id).orElse(null);
       if(address != null){
         addressDto = mapper.convertValue(address,AddressDto.class);
       }
        return Optional.ofNullable(addressDto);
    }

    @Override
    public void updateAddress(AddressDto addressDto) {

    }

    @Override
    public void deleteAddress(Long id) {

    }
}
