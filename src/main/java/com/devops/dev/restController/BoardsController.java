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

import com.devops.dev.dao.BoardDao;
import com.devops.dev.domainObject.Board;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BoardsController {

	@Autowired
	private BoardDao boardDao;

	@RequestMapping("/boardList")
	public Map<String, List<Board>> greeting(String name) throws JsonProcessingException {

		List<Board> boardList = new ArrayList<Board>();
		boardList = boardDao.getAll();
		
		Map<String, List<Board>> boardJSONDataMap = new HashMap<String, List<Board>>();
		boardJSONDataMap.put("data", boardList);
		//ObjectMapper mapper = new ObjectMapper();
		//String jsonInString = mapper.writeValueAsString(boardJSONDataMap);
		return boardJSONDataMap;
	}

	@RequestMapping(value = "/boardAction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String createBoard(@RequestBody String jsonString) {
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
				createBoard(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("edit")) {
				updateBoard(dataField);
			} else if (actionFieldValue.equalsIgnoreCase("remove")) {
				removeBoard(dataField);
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

	public Board createBoard(JsonNode dataField) throws JsonProcessingException, Exception {
		// JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		Board Board = mapper.treeToValue(dataField.get("0"), Board.class);
		boardDao.save(Board);
		System.out.println(Board.getBoardId() + " : " + Board.getBoardName());
		return Board;
	}

	@RequestMapping("/updateBoard")
	public void updateBoard(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Board board = mapper.treeToValue(entry.getValue(), Board.class);
			boardDao.update(board);
			System.out.println(board.getBoardId() + " : " + board.getBoardName());
		}
	}

	@RequestMapping("/deleteBoard")
	public void removeBoard(JsonNode dataField) throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Iterator<Entry<String, JsonNode>> nodes = dataField.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			System.out.println("key --> " + entry.getKey() + " value-->" + entry.getValue());
			Board Board = mapper.treeToValue(entry.getValue(), Board.class);
			boardDao.delete(Board);
			System.out.println(Board.getBoardId() + " : " + Board.getBoardName());
		}
	}
}
