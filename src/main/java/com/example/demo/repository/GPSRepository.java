package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.GPS;

@Repository
public interface GPSRepository extends CrudRepository<GPS, Long> {
	public GPS findByUserId(long userId);
}
