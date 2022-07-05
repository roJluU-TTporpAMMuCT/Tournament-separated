package org.negro.tournament.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Data
@Table(name="solve")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Solve {
	
	@Id @GeneratedValue private Long id;
	
	@Column(columnDefinition="text") private String code;
		
	@Column private LocalDateTime solTime;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userok_id")
	private User userok;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="quest_id")
	private Quest quest;
}
