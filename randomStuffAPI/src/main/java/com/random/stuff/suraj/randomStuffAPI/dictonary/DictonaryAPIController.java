package com.random.stuff.suraj.randomStuffAPI.dictonary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.random.stuff.suraj.randomStuffAPI.pojo.Defination;
import com.random.stuff.suraj.randomStuffAPI.pojo.Meaning;
import com.random.stuff.suraj.randomStuffAPI.pojo.Phonetic;
import com.random.stuff.suraj.randomStuffAPI.pojo.Result;

@RestController("/")
public class DictonaryAPIController {

	@Value("${dictonary.api.address}")
	private String dictonaryApiAddress;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/dictonary/{word}")
	public ResponseEntity<List<Result>> getMeaning(@PathVariable String word)
			throws JsonMappingException, JsonProcessingException {

		String jsonResult = restTemplate
				.getForObject(dictonaryApiAddress + "/" + word, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		String result = jsonResult;
		JsonNode resultNode = null;

		JsonNode rootNode = objectMapper.readTree(result);
		List<Result> li = new ArrayList<Result>();

		for (JsonNode node : rootNode) {
			Result rs = new Result();

			rs.setWord(node.path("word").asText());
			rs.setPhonetic(node.path("phonetic").asText());

			JsonNode phoneticsNode = node.at("/phonetics");
			List<Phonetic> lip = new ArrayList<Phonetic>();
			for (JsonNode jsonNode : phoneticsNode) {
				Phonetic ph = new Phonetic();
				ph.setText(jsonNode.path("text").asText());
				ph.setAudio(jsonNode.path("audio").asText());
				lip.add(ph);
			}

			rs.setPhonetics(lip);
			rs.setOrigin(node.path("origin").asText());

			JsonNode meaningsNode = node.at("/meanings");
			List<Meaning> lmin = new ArrayList<Meaning>();
			for (JsonNode meaning : meaningsNode) {

				Meaning mn = new Meaning();
				mn.setPartOfSpeech(meaning.path("partOfSpeech").asText());

				JsonNode defNode = meaning.at("/definitions");
				List<Defination> ldf = new ArrayList<Defination>();
				for (JsonNode def : defNode) {
					Defination df = new Defination();
					df.setDefinition(def.path("definition").asText());
					ldf.add(df);
				}
				mn.setDefinitions(ldf);
				lmin.add(mn);

			}
			rs.setMeanings(lmin);
			li.add(rs);
		}

		return ResponseEntity.ok(li);

	}
	
	@GetMapping("/dictonary/simple/{word}")
	public ResponseEntity<List<Result>> getSimpleMeaning(@PathVariable String word){
		
		ArrayList<Result> results  = restTemplate.getForObject(dictonaryApiAddress + "/" + word, ArrayList.class);
		
		System.out.println(results.get(0));
		return ResponseEntity.ok(results);
	}

}
