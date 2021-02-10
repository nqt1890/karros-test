package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Metadata;

@Repository
public interface MetadataRepository extends CrudRepository<Metadata, Long> {
	Metadata findByUserId(int userId);
}