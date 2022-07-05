package org.negro.tournament.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Table(name="quest")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
	
	@Id @GeneratedValue private Long id;
	
	@Size(min=3, max=20)
	private String name;
	
	@Column(length = 128) private String className;
	
	@Column(columnDefinition="text") private String condition;
	
	@Column(columnDefinition="text") private String preCode;
	
	@Column(columnDefinition="text") private String preTestCode;
		
	@Column(columnDefinition="text") private String testCode;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userok_id")
	private User userok;
}
