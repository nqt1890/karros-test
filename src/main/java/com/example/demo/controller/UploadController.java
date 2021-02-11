package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.Trackpoint;
import com.example.demo.domain.Waypoint;
import com.example.demo.repository.GPSRepository;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.repository.TrackRepository;
import com.example.demo.repository.WaypointRepository;
import com.example.demo.service.XmlService;

@Controller
public class UploadController {
	@Autowired
	XmlService xmlService;

	@Autowired
	GPSRepository gpsRepo;
	
	@Autowired
	MetadataRepository metadataRepo;

	@Autowired
	TrackRepository trackRepo;

	@Autowired
	WaypointRepository waypointRepo;

	@GetMapping("/upload")
	public String uploadPage(String name, Model model) {
		model.addAttribute("name", name);
		return "upload";
	}

	@PostMapping("/upload")
	public RedirectView uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("user_id") int userId)
			throws IOException {
		InputStream initialStream = file.getInputStream();

		String str = xmlService.xmlFileToString(initialStream);
		GPS gps = xmlService.deserializeFromXmlString(str);
		gps.setUserId(userId);
		
		Metadata me = gps.getMetadata();
		me.setGps(gps);
		Link link = me.getLink();
		link.setMetadata(me);
		
		Track tr = gps.getTrk();
		tr.setGps(gps);
		for (Trackpoint tp : tr.getTrkpt()) {
			tp.setTrack(tr);
		}

		List<Waypoint> wps = gps.getWpt();
		for (Waypoint wp : wps) {
			wp.setGps(gps);
		}

		gps = gpsRepo.save(gps);
		return new RedirectView("upload");
	}
}
