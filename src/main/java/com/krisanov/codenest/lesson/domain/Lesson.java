package com.krisanov.codenest.lesson.domain;

import com.krisanov.codenest.task.domain.Task;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The Lesson entity represents an educational unit in the system with a unique title, description,
 * text content, related code fragment, and a series of tasks. This class also records creation and
 * update timestamps.
 *
 * @author Maxim Krisanov
 * @version 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lessons")
public class Lesson {

  /**
   * Unique identifier of the lesson.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Title of the lesson, must be unique and between 10 and 40 characters.
   */
  @Size(min = 10, max = 40, message = "Lesson title must be between 10 and 40 characters")
  @Column(unique = true, nullable = false)
  private String title;

  /**
   * Description of the lesson, must be between 10 and 100 characters.
   */
  @Size(min = 10, max = 100, message = "Lesson description must be between 10 and 100 characters")
  @Column(nullable = false)
  private String description;

  /**
   * Text content of the lesson, must not be null.
   */
  @NotNull(message = "Lesson text content must not be null")
  @Column(name = "text_content", nullable = false, columnDefinition = "text")
  private String textContent;

  /**
   * Code fragment associated with the lesson. Non required
   */
  @Nullable
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "code_fragment_id", referencedColumnName = "id")
  private CodeFragment codeFragment;

  /**
   * URL of the lesson's image. Must not be blank.
   */
  @NotBlank(message = "Lesson image url must not be blank")
  @Column(name = "image_url", nullable = false)
  private String imageUrl;

  /**
   * The timestamp when the lesson was created.
   * It's automatically managed by Hibernate (not modifiable).
   */
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Timestamp createdAt;

  /**
   * The timestamp when the lesson was last updated.
   * It's automatically managed by Hibernate.
   */
  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;

  /**
   * List of tasks associated with this lesson.
   */
  @Builder.Default
  @OneToMany(mappedBy = "lesson", orphanRemoval = true)
  private List<Task> tasks = new ArrayList<>();
}
