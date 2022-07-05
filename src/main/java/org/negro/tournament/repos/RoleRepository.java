package org.negro.tournament.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.negro.tournament.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
