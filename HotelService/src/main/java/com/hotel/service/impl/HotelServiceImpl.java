package com.hotel.service.impl;

import com.hotel.Repositories.HotelRespository;
import com.hotel.entity.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRespository hotelRespository;



    @Override
    public Hotel create(Hotel hotel) {

        String hotelid=UUID.randomUUID().toString();
        hotel.setId(hotelid);
        return  hotelRespository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRespository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found !!"));
    }
}
