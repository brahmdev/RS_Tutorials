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

import com.devops.dev.dao.ClassLevelDao;
import com.devops.dev.domainObject.ClassLevel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ClassLevelController {

	@Autowired
	private ClassLevelDao classLevelDao;

	@RequestMapping("/classLevelList")
	public Map<String, List<ClassLevel>> greeting(String name) throws JsonProcessingException {

		List<ClassLevel> classLevelList = new ArrayList<ClassLevel>();
		classLevelList = classLevelDao.getAll();
		
		Map<String, List<ClassLevel>> classLevelJSONDataMap = new HashMap<String, List<ClassLevel>>();
		classLevelJSONDataMap.put("data", classLevelList);
		//ObjectMapper mapper = new ObjectMapper();
		//String jsonInString = mapper.writeValueAsString(boardJSONDataMap);
		return classLevelJSONDataMap;
	}

	@RequestMapping(value = "/classLevelAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createClassLevel(@RequestBody String jsonString) {
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
				createClassLevel(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateClassLevel(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				removeClassLevel(dataField);
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

	public ClassLevel createClassLevel(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		ClassLevel classLevel = mapper.treeToValue(dataField.get("0"), ClassLevel.class);
		classLevelDao.save(classLevel);
		System.out.println(classLevel.getClassLevelId() + " : " + classLevel.getDescription());
		return classLevel;
	}

	@RequestMapping("/updateClassLevel")
	public void updateClassLevel(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			ClassLevel classLevel = mapper.treeToValue(entry.getValue(), ClassLevel.class);
			classLevelDao.update(classLevel);
			System.out.println(classLevel.getClassLevelId() + " : " + classLevel.getDescription());
		}
	}

	@RequestMapping("/deleteClassLevel")
	public void removeClassLevel(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			ClassLevel classLevel = mapper.treeToValue(entry.getValue(), ClassLevel.class);
			classLevelDao.delete(classLevel);
			System.out.println(classLevel.getClassLevelId() + " : " + classLevel.getDescription());
		}
	}
}
