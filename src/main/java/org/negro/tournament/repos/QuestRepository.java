package org.negro.tournament.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.negro.tournament.models.Quest;

@Repository
public interface QuestRepository extends JpaRepository<Quest,Long> {
	
	public Quest findAnyByName(String name);
}
