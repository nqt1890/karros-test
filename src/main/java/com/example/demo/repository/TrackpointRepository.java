package com.example.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Trackpoint;

@Repository
public interface TrackpointRepository extends CrudRepository<Trackpoint, Long> {
	public List<Trackpoint> findAllByTrackIdOrderByTimeDesc(long trackId, Pageable pageable);
}
