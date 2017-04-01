package com.devops.dev.restController;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dev.dao.CountryDao;
import com.devops.dev.domainObject.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ContryController {
	
	@Autowired
	private CountryDao countryDao;

	@RequestMapping("/countryList")
	public Map<String, List<Country>> greeting(String name) {

		List<Country> countryList = new ArrayList<Country>();
		countryList = countryDao.getAll();
		/*countryList.add(new Country("IN", "INDIA"));
		countryList.add(new Country("SE", "SWEDEN"));
		countryList.add(new Country("USA", "UNITED STATES OF AMERICA"));
		countryList.add(new Country("GB", "GREAT BRITAIN"));
		countryList.add(new Country("SL", "SRI LANKA"));*/
		Map<String, List<Country>> countryJSONDataMap = new HashMap<String, List<Country>>();
		countryJSONDataMap.put("data", countryList);
		return countryJSONDataMap;
	}

	@RequestMapping(value = "/createCountry", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createCountry(@RequestBody String jsonString) {
		System.out.println(jsonString);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = new ObjectMapper().readTree(new StringReader(jsonString));
			// Just like DOM, our data is in a hierarchy of node (in this case,
			// it is JsonNode)
			JsonNode actionField = rootNode.get("action");

			// the customerSessionId has a String value
			String actionFieldValue = actionField.asText();

			System.out.println("actionFieldValue is:" + actionFieldValue);

			JsonNode dataField = rootNode.get("data");

			// JSON from String to Object
			Country country = mapper.treeToValue(dataField.get("0"), Country.class);
			countryDao.save(country);
			System.out.println(country.getCountryCode() + " : " + country.getCountryName());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	@RequestMapping("/updateCountry")
	public Country updateCountry(String data) {
		System.out.println(data);
		Country country = new Country();
		return country;
	}

	@RequestMapping("/deleteCountry")
	public void removeCountry(String data) {

		System.out.println(data);
	}
}
