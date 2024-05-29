package com.krisanov.codenest.domain;

import jakarta.annotation.Nullable;
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
 * The LessonContent class represents an entity in the database specific to the content of a lesson.
 *
 * @see Lesson
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_content")
public class LessonContent {

    /**
     * The unique identifier of the lesson content.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The paragraph of the lesson content.
     */
    @NotNull(message = "Paragraph must not be null.")
    @NotBlank(message = "Paragraph must not be blank.")
    private String paragraph;

    /**
     * Code associated with the lesson content.
     */
    @Nullable
    private String code;

    /**
     * The language of the code associated with the lesson content.
     */
    @Nullable
    @Column(name = "code_language")
    private String codeLanguage;

    /**
     * The lesson associated with this content.
     */
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}
