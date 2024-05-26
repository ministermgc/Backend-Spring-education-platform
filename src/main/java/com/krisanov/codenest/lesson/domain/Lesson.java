package com.krisanov.codenest.lesson.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The Lesson class represents a single lesson which consists of paragraphs of text and fragments of code.
 * Each lesson has a unique title, a description, an image URL, and timestamps for creation and updates.
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
     * The unique identifier for a lesson. Generated automatically upon creation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the lesson; unique and not null. Must be between 10 and 40 characters.
     */
    @Size(min = 10, max = 40, message = "Lesson title must be between 10 and 40 characters.")
    @Column(unique = true, nullable = false)
    private String title;

    /**
     * A description of the lesson, between 10 and 100 characters.
     */
    @Size(min = 10, max = 100, message = "Lesson description must be between 10 and 100 characters.")
    @Column(nullable = false)
    private String description;

    /**
     * The paragraphs that constitute the lesson. This cannot be null and is linked to the Paragraph class.
     */
    @NotNull(message = "Lesson's paragraphs must not be null.")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Paragraph> paragraphs = new ArrayList<>();

    /**
     * The fragments of code included in the lesson. This cannot be null and is linked to the CodeFragment class.
     */
    @NotNull(message = "Lesson's code fragments must not be null.")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<CodeFragment> codeFragments = new ArrayList<>();

    /**
     * A URL to the image associated with this lesson.
     */
    @NotBlank(message = "Lesson image url must not be blank.")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    /**
     * The timestamp of when the lesson was created. This is generated automatically.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * The timestamp of when the lesson was last updated. This is generated automatically.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
