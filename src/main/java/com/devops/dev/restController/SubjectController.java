package com.devops.dev.restController;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.devops.dev.domainObject.DropDownJSONType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.devops.dev.dao.ClassLevelTypeDao;
import com.devops.dev.dao.SubjectDao;
import com.devops.dev.domainObject.ClassLevelType;
import com.devops.dev.domainObject.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class SubjectController {

	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private ClassLevelTypeDao classLevelTypeDao;

	@RequestMapping("/subjectList")
	public String classLevelTypeList(String name) throws JsonProcessingException {

		List<Subject> subjectTypeList = new ArrayList<Subject>();
		subjectTypeList = subjectDao.getAll();
		
		Map<String, List<Subject>> classLevelTypeJSONDataMap = new HashMap<String, List<Subject>>();
		classLevelTypeJSONDataMap.put("data", subjectTypeList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(classLevelTypeJSONDataMap);
		return jsonInString;
	}

	@RequestMapping(value = "/subjectAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createSubjectType(@RequestBody String jsonString) {
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
				createSubject(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateSubject(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				//removeSubjectType(dataField);
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

	public Subject createSubject(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		Subject subject = mapper.treeToValue(dataField.get("0"), Subject.class);
		ClassLevelType classLevelType = classLevelTypeDao.getClassLevelType(subject.getClassLevelType().getClassLevel().getClassLevelId(),subject.getClassLevelType().getBoard().getBoardId(), subject.getClassLevelType().getClassName(), subject.getClassLevelType().getLanguage());
		subject.setClassLevelType(classLevelType);
		subjectDao.save(subject);
		//System.out.println(subject.getSubjectTypeId() + " : " + classLevelType.getClassName() + " " + classLevelType.getLanguage());
		return subject;
	}

	@RequestMapping("/updateSubject")
	public void updateSubject(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Subject subject = mapper.treeToValue(entry.getValue(), Subject.class);
			ClassLevelType classLevelType = classLevelTypeDao.getClassLevelType(subject.getClassLevelType().getClassLevel().getClassLevelId(),subject.getClassLevelType().getBoard().getBoardId(), subject.getClassLevelType().getClassName(), subject.getClassLevelType().getLanguage());
			subject.setClassLevelType(classLevelType);
			subjectDao.update(subject);
			//System.out.println(classLevelType.getSubjectTypeId() + " : " + classLevelType.getClassName() + " " + classLevelType.getLanguage());
		}
	}

	@RequestMapping("/getSubjectNameListByStandard")
	public String getSubjectNameListByStandard(@RequestParam("csrfmiddlewaretoken") String csrfmiddlewaretoken, @RequestParam("keyToSearch") String keyToSearch) throws JsonProcessingException {

		List<Subject> classLevelTypeList = new ArrayList<Subject>();
		classLevelTypeList = classLevelTypeDao.getSubjectForStandard(keyToSearch);
		List<String> addedClassName = new ArrayList<String>();
		List<DropDownJSONType> jsonDropDownList = new ArrayList<DropDownJSONType>();
		for(Subject subject : classLevelTypeList) {
			if(!addedClassName.contains(subject.getSubjectName())) {
				DropDownJSONType json = new DropDownJSONType();
				json.setValue(String.valueOf(subject.getSubjectId()));
				json.setLabel(subject.getSubjectName());
				jsonDropDownList.add(json);
			}
			addedClassName.add(subject.getSubjectName());
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(jsonDropDownList);
		return jsonInString;
	}
}
