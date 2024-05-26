package com.krisanov.codenest.lesson.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a single paragraph within a lesson.
 * A paragraph is considered a part of the lesson content,
 * and links back to its parent lesson.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_paragraphs")
public class Paragraph {

    /**
     * The unique identifier for a paragraph. Automatically generated upon creation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The actual content text of the paragraph.
     * Must not be null or blank.
     */
    @NotNull(message = "Paragraph's text must not be null.")
    @NotBlank(message = "Paragraph's text must not be blank.")
    @Column(columnDefinition = "TEXT")
    private String text;

    /**
     * The order or position of the paragraph in the lesson. Must not be null.
     */
    @NotNull(message = "Paragraph's position must not be null.")
    private Integer position;

    /**
     * The lesson this paragraph is a part of. Must not be null.
     * This is linked back to the parent lesson.
     */
    @NotNull(message = "Paragraph's lesson must not be null.")
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}
