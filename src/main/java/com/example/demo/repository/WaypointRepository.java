package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Waypoint;

@Repository
public interface WaypointRepository extends CrudRepository<Waypoint, Long> {
	List<Waypoint> findByGpsId(long gpsId);
}
