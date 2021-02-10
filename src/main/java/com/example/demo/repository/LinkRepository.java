package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Link;;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
	Link findByMetadataId(int metadataId);
}
