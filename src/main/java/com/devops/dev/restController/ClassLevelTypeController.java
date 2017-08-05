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

import com.devops.dev.dao.ClassLevelTypeDao;
import com.devops.dev.domainObject.Board;
import com.devops.dev.domainObject.ClassLevel;
import com.devops.dev.domainObject.ClassLevelType;
import com.devops.dev.domainObject.DropDownJSONType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class ClassLevelTypeController {

	@Autowired
	private ClassLevelTypeDao classLevelTypeDao;

	@RequestMapping("/classLevelTypeList")
	public String classLevelTypeList(String name) throws JsonProcessingException {

		List<ClassLevelType> classLevelTypeList = new ArrayList<ClassLevelType>();
		classLevelTypeList = classLevelTypeDao.getAll();
		
		Map<String, List<ClassLevelType>> classLevelTypeJSONDataMap = new HashMap<String, List<ClassLevelType>>();
		classLevelTypeJSONDataMap.put("data", classLevelTypeList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(classLevelTypeJSONDataMap);
		return jsonInString;
	}

	@RequestMapping(value = "/classLevelTypeAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createClassLevelType(@RequestBody String jsonString) {
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
				createClassLevelType(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateClassLevelType(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				removeClassLevelType(dataField);
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

	public ClassLevelType createClassLevelType(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		ClassLevelType classLevelType = mapper.treeToValue(dataField.get("0"), ClassLevelType.class);
		classLevelTypeDao.save(classLevelType);
		System.out.println(classLevelType.getClassLevelTypeId() + " : " + classLevelType.getClassName() + " " + classLevelType.getLanguage());
		return classLevelType;
	}

	@RequestMapping("/updateClassLevelType")
	public void updateClassLevelType(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			ClassLevelType classLevelType = mapper.treeToValue(entry.getValue(), ClassLevelType.class);
			classLevelTypeDao.update(classLevelType);
			System.out.println(classLevelType.getClassLevelTypeId() + " : " + classLevelType.getClassName() + " " + classLevelType.getLanguage());
		}
	}

	@RequestMapping("/deleteClassLevelType")
	public void removeClassLevelType(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			ClassLevelType classLevelType = mapper.treeToValue(entry.getValue(), ClassLevelType.class);
			classLevelTypeDao.delete(classLevelType);
			System.out.println(classLevelType.getClassLevelTypeId() + " : " + classLevelType.getClassName() + " " + classLevelType.getLanguage());
		}
	}
	
	@RequestMapping("/getBoards")
	public String getBoards() throws JsonProcessingException {

		List<Board> boardList = new ArrayList<Board>();
		boardList = classLevelTypeDao.getAllBoards();
		
		List<DropDownJSONType> jsonDropDownLoist = new ArrayList<DropDownJSONType>();
		for(Board board : boardList) {
			DropDownJSONType json = new DropDownJSONType();
			json.setValue(String.valueOf(board.getBoardId()));
			json.setLabel(board.getBoardName());
			jsonDropDownLoist.add(json);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(jsonDropDownLoist);
		return jsonInString;
	}
	
	@RequestMapping("/getClassLevel")
	public String getClassLevel() throws JsonProcessingException {

		List<ClassLevel> classLevelList = new ArrayList<ClassLevel>();
		classLevelList = classLevelTypeDao.getAllClassLevel();
		
		List<DropDownJSONType> jsonDropDownLoist = new ArrayList<DropDownJSONType>();
		for(ClassLevel classLevel : classLevelList) {
			DropDownJSONType json = new DropDownJSONType();
			json.setValue(String.valueOf(classLevel.getClassLevelId()));
			json.setLabel(classLevel.getDescription());
			jsonDropDownLoist.add(json);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(jsonDropDownLoist);
		return jsonInString;
	}
}
