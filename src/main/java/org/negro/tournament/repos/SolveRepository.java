package org.negro.tournament.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.negro.tournament.models.Solve;

@Repository
public interface SolveRepository extends JpaRepository<Solve, Long> {}
