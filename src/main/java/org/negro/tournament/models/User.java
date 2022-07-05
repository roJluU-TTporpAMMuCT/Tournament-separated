package org.negro.tournament.models;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="userok")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
	
	@Transient
	private static final long serialVersionUID = 8525736187089069828L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min = 2, max = 20)
	private String username;
	
	@Size(min = 2, max = 1000)
	private String password;
	
	@Size(min = 2, max = 20)
	@Transient
	private String rawPassword;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Role> roles;
	
	//Only java for now
	private String lang;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userok")
	private Set<Solve> solves;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userok")
	private Set<Quest> quests;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }
}
