package org.negro.tournament.controllers;


import java.net.URLDecoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import org.negro.compiler.CompilationException;
import org.negro.tournament.models.*;
import org.negro.tournament.services.QuestService;

@RestController
public class QuestController {
	
	@Autowired
	private QuestService qServ;
	
	@GetMapping("quest/{qName}")
	public Quest getQuest(@PathVariable String qName) {
		Quest q = qServ.loadQuest(qName);
		q.setUserok(null);
		q.setTestCode(null);
		return q;
	}
	
	@PostMapping("quest/{qName}")
	public String postQuest(@PathVariable String qName, @RequestBody Solve solve, Authentication user) throws Exception {
		Quest q = qServ.loadQuest(qName);
		if(q == null) return null;

		try {
			return new JSONObject(qServ.testQuest(user.getName(), solve.getCode(), q) ).toString();
		}catch(CompilationException e) {
			return new JSONObject().put("compileError", e.getMessage() ).toString();
		}
	}
	
	@PostMapping("/create")
	public String postCreate(@RequestBody String questsolvejs, Authentication user) throws Exception {
		JSONObject json = new JSONObject(URLDecoder.decode(questsolvejs, "UTF-8"));
		Quest q = new ObjectMapper().readValue(json.getJSONObject("quest").toString(), Quest.class);

		try {
			return new JSONObject(qServ.createQuest(user.getName(), json.getString("code"), q) ).toString();
		}catch(CompilationException e) {
			return new JSONObject().put("compileError", e.getMessage() ).toString();
		}
	}
}
