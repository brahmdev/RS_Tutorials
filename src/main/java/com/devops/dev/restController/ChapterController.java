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

import com.devops.dev.dao.ChapterDao;
import com.devops.dev.dao.ClassLevelTypeDao;
import com.devops.dev.dao.SubjectDao;
import com.devops.dev.domainObject.Chapter;
import com.devops.dev.domainObject.ClassLevelType;
import com.devops.dev.domainObject.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class ChapterController {

	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private ClassLevelTypeDao classLevelTypeDao;
	
	@RequestMapping("/chapterList")
	public String classLevelTypeList(String name) throws JsonProcessingException {

		List<Chapter> chapterTypeList = new ArrayList<Chapter>();
		chapterTypeList = chapterDao.getAll();
		
		Map<String, List<Chapter>> classLevelTypeJSONDataMap = new HashMap<String, List<Chapter>>();
		classLevelTypeJSONDataMap.put("data", chapterTypeList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonInString = mapper.writeValueAsString(classLevelTypeJSONDataMap);
		return jsonInString;
	}

	@RequestMapping(value = "/chapterAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createChapterType(@RequestBody String jsonString) {
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
				createChapter(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateChapter(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				//removeChapterType(dataField);
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

	public Chapter createChapter(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		Chapter chapter = mapper.treeToValue(dataField.get("0"), Chapter.class);
		ClassLevelType classLevelType = classLevelTypeDao.getClassLevelType(chapter.getSubject().getClassLevelType().getClassLevel().getClassLevelId(),chapter.getSubject().getClassLevelType().getBoard().getBoardId(), chapter.getSubject().getClassLevelType().getClassName(), chapter.getSubject().getClassLevelType().getLanguage());
		Subject subject = subjectDao.getSubject(chapter.getSubject().getSubjectName(),classLevelType.getClassLevelTypeId());
		subject.setClassLevelType(classLevelType);
		chapter.setSubject(subject);
		chapterDao.save(chapter);
		return chapter;
	}

	@RequestMapping("/updateChapter")
	public void updateChapter(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Chapter chapter = mapper.treeToValue(entry.getValue(), Chapter.class);
			ClassLevelType classLevelType = classLevelTypeDao.getClassLevelType(chapter.getSubject().getClassLevelType().getClassLevel().getClassLevelId(),chapter.getSubject().getClassLevelType().getBoard().getBoardId(), chapter.getSubject().getClassLevelType().getClassName(), chapter.getSubject().getClassLevelType().getLanguage());
			Subject subject = subjectDao.getSubject(chapter.getSubject().getSubjectName(),classLevelType.getClassLevelTypeId());
			subject.setClassLevelType(classLevelType);
			chapter.setSubject(subject);
			chapterDao.update(chapter);
		}
	}

	
}
