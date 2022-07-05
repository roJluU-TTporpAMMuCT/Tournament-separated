package org.negro.tournament.services;

import java.time.LocalDateTime;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.negro.compiler.*;
import org.negro.tournament.models.Quest;
import org.negro.tournament.models.Solve;
import org.negro.tournament.models.User;
import org.negro.tournament.repos.QuestRepository;
import org.negro.tournament.repos.SolveRepository;

@Service
public class QuestService {
	
	@Autowired
	private InMemoryJavaCompiler compiler;
	
	@Autowired
	private UserService uServ;
	
	@Autowired
	private SolveRepository sRep;
	
	@Autowired
	private QuestRepository qRep;
	
	public Quest loadQuest(String name) {
		return qRep.findAnyByName(name);
	}
	
	@SuppressWarnings("deprecation")
	public Result runQuest(String username, String code, Quest q) throws Exception {
		compiler.compile(username + "." + q.getClassName(), "package " + username + "; " +  code);
		Class<?> preTestRes = compiler.compile(q.getClassName() + "Test", "import " + username + ".*;" + q.getPreTestCode() );
		Class<?> testRes = compiler.compile(q.getClassName() + "Test2", "import " + username + ".*;" + q.getTestCode() );
		final Result[] res = new Result[1];
		Thread task = new Thread(() -> res[0] = JUnitCore.runClasses(preTestRes, testRes) );
		task.start(); task.join(16000); task.stop();
		if(res[0] == null)
			throw new CompilationException("Run time is over 16 seconds");
		return res[0];
	}
	
	public Result createQuest(String username, String code, Quest q) throws Exception {
		if(qRep.findAnyByName(q.getName() ) != null)
			throw new CompilationException("Quest name has already in use");
		Result res = runQuest(username, code, q);
		if(res.wasSuccessful() ) {
			User user = uServ.loadUserByUsername(username);
			q.setUserok(user);
			q = qRep.save(q);
			sRep.save(new Solve(null, code, LocalDateTime.now(), user, q) );
		}
		return res;
	}
	
	public Result testQuest(String username, String code, Quest q) throws Exception {
		Result res = runQuest(username, code, q);
		if(res.wasSuccessful() )
			sRep.save(new Solve(null, code, LocalDateTime.now(), uServ.loadUserByUsername(username), q ) );
		return res;
	}

}
