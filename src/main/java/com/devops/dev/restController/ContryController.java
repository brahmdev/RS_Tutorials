package com.devops.dev.restController;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	public Map<String, List<Country>> greeting(String name) throws JsonProcessingException {

		List<Country> countryList = new ArrayList<Country>();
		countryList = countryDao.getAll();
		/*
		 * countryList.add(new Country("IN", "INDIA")); countryList.add(new
		 * Country("SE", "SWEDEN")); countryList.add(new Country("USA",
		 * "UNITED STATES OF AMERICA")); countryList.add(new Country("GB",
		 * "GREAT BRITAIN")); countryList.add(new Country("SL", "SRI LANKA"));
		 */
		Map<String, List<Country>> countryJSONDataMap = new HashMap<String, List<Country>>();
		countryJSONDataMap.put("data", countryList);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(countryJSONDataMap);
		return countryJSONDataMap;
	}

	@RequestMapping(value = "/countryAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createCountry(@RequestBody String jsonString) {
		System.out.println(jsonString);

		try {
			JsonNode rootNode = new ObjectMapper().readTree(new StringReader(jsonString));
			// Just like DOM, our data is in a hierarchy of node (in this case,
			// it is JsonNode)
			JsonNode actionField = rootNode.get("action");

			// the customerSessionId has a String value
			String actionFieldValue = actionField.asText();

			System.out.println("actionFieldValue is:" + actionFieldValue);

			JsonNode dataField = rootNode.get("data");

			if (actionFieldValue.equalsIgnoreCase("create")) {
				createCountry(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateCountry(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				removeCountry(dataField);
			} else {
				throw new Exception("Invalid Action !!!");
			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	public Country createCountry(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		Country country = mapper.treeToValue(dataField.get("0"), Country.class);
		countryDao.save(country);
		System.out.println(country.getCountryCode() + " : " + country.getCountryName());
		return country;
	}

	@RequestMapping("/updateCountry")
	public void updateCountry(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Country country = mapper.treeToValue(entry.getValue(), Country.class);
			countryDao.update(country);
			System.out.println(country.getCountryCode() + " : " + country.getCountryName());
		}
	}

	@RequestMapping("/deleteCountry")
	public void removeCountry(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Country country = mapper.treeToValue(entry.getValue(), Country.class);
			countryDao.delete(country);
			System.out.println(country.getCountryCode() + " : " + country.getCountryName());
		}
	}
}
