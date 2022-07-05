package org.negro.tournament.models;

import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.*;

@Entity
@Table(name="role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
	
	@Transient
	private static final long serialVersionUID = 8918037444218746209L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Transient
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	public Role(Long id, String name) { this.id = id; this.name = name; }
	public Role(Long id) { this.id = id; }

	@Override
	public String getAuthority() {
		return getName();
	}

}