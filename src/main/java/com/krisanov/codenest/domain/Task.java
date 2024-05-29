package com.krisanov.codenest.domain;

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
 * The Task class represents a task entity in the database, associated with a specific lesson.
 *
 * @see Lesson
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {

    /**
     * The unique identifier of the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the task.
     */
    @NotNull(message = "Task's title must not be null.")
    @NotBlank(message = "Task's title must not be blank.")
    private String title;

    /**
     * The text content of the task, describing what needs to be done.
     */
    @NotNull(message = "Task's text must not be null.")
    @NotBlank(message = "Task's text must not be blank.")
    private String text;

    /**
     * The answer to the task.
     */
    @NotNull(message = "Task's answer must not be null.")
    private String answer;

    /**
     * The lesson associated with this task.
     */
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}
