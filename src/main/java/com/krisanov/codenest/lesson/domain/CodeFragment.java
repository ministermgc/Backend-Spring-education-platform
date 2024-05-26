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
 * The CodeFragment class represents a section of code within a lesson.
 * A code fragment is a part of the lesson content and is linked back to its parent lesson.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_code_fragments")
public class CodeFragment {

    /**
     * The unique identifier for a code fragment. Automatically generated upon creation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The text of the code fragment content.
     * Must not be null or blank.
     */

    @NotNull(message = "Code must not be null.")
    @NotBlank(message = "Code must not be blank.")
    @Column(columnDefinition = "TEXT")
    private String code;

    /**
     * The order or position of the code fragment in the lesson. Must not be null.
     */
    @NotNull(message = "Code fragment's position must not be null.")
    private Integer position;

    /**
     * The lesson this code fragment is a part of. Must not be null.
     * This is linked back to the parent lesson.
     */
    @NotNull(message = "Code fragment's lesson must not be null.")
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}
