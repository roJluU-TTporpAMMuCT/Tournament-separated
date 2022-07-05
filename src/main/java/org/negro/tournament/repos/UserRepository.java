package org.negro.tournament.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.negro.tournament.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findAnyByUsername(String username);
}
