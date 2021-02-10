package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.Trackpoint;
import com.example.demo.domain.Waypoint;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.repository.TrackRepository;
import com.example.demo.repository.TrackpointRepository;
import com.example.demo.repository.WaypointRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class DataController {
	@Autowired
	TrackpointRepository trackpointRepo;

	@Autowired
	TrackRepository trackRepo;

	@Autowired
	WaypointRepository waypointRepo;

	@Autowired
	MetadataRepository metadataRepo;

	@GetMapping("/user/{id}/lastesttrack")
	public String lastestTracks(@PathVariable int id, @RequestParam int page) throws JsonProcessingException {
		Pageable pageable = PageRequest.of(page, 10);

		Track tr = trackRepo.findByUserId(id);
		List<Trackpoint> rs = trackpointRepo.findAllByTrackIdOrderByTimeDesc(tr.getId(), pageable);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(rs);
		return json;
	}

	@GetMapping("/user/{id}/detail")
	public String one(@PathVariable int id) throws JsonProcessingException {
		GPS gps = new GPS();
		Track tr = trackRepo.findByUserId(id);
		Metadata me = metadataRepo.findByUserId(id);
		List<Waypoint> wps = waypointRepo.findByUserId(id);

		gps.setMetadata(me);
		gps.setTrk(tr);
		gps.setWpt(wps);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(gps);
		return json;
	}

}
