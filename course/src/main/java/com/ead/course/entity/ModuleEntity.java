package com.ead.course.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_MODULES")
public class ModuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID moduleId;

	@Column(nullable = false, length = 150)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CourseEntity course;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<LessonEntity> lessons;
}
