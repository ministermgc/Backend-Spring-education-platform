package com.krisanov.codenest.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The Lesson class represents a lesson entity in the database.
 * A lesson must have content and may have tasks associated with it.
 *
 * @see LessonContent
 * @see Task
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons")
public class Lesson {

    /**
     * The unique identifier of the lesson.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the lesson.
     */
    @NotNull(message = "Title must not be null.")
    @NotBlank(message = "Title must not be blank.")
    @Size(min = 10, max = 40, message = "Title must be between 10 and 40 characters.")
    private String title;

    /**
     * The description of the lesson.
     */
    @NotNull(message = "Description must not be null.")
    @NotBlank(message = "Description must not be blank.")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters.")
    private String description;

    /**
     * The url of the lesson's image.
     */
    @NotNull(message = "Image url must not be null.")
    @NotBlank(message = "Image url must not be blank.")
    private String imageUrl;

    /**
     * The content of the lesson.
     */
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<LessonContent> content;

    /**
     * A list of tasks associated with this lesson.
     */
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE)
    private List<Task> tasks;
}
