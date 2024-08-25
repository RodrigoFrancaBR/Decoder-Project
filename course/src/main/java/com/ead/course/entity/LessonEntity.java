package com.ead.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_LESSONS")
public class LessonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID lessonId;

	@Column(nullable = false, length = 150)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false)
	private String videoUrl;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime creationDate;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ModuleEntity module;

}
