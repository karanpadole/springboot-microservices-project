package com.hotel.Repositories;

import com.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRespository  extends JpaRepository<Hotel,String> {
}
