package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.example.demo.domain.GPS;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class XmlService {

	public GPS deserializeFromXmlString(String str) throws JsonParseException, JsonMappingException, IOException {
		XmlMapper xmlMapper = new XmlMapper();
		GPS gps = xmlMapper.readValue(str, GPS.class);
		return gps;
	}

	public String xmlFileToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		System.out.println(sb.toString());
		return sb.toString();
	}
}
