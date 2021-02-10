package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Track;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
	Track findByUserId(int userId);
}
