package com.krisanov.codenest.lesson.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The CodeFragment entity represents a snippet of code in the application. It provides support for a brief description and
 * specifies the programming language of the code fragment.
 *
 * @author Maxim Krisanov
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "code_fragments")
public class CodeFragment {

    /**
     * Unique identifier of the code fragment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Non required description of the code fragment.
     */
    @Nullable
    private String description;

    /**
     * Raw code of the fragment. Must not be blank.
     */
    @NotBlank(message = "Code must not be blank")
    private String code;

    /**
     * Programming language used in the code fragment.
     * This is represented as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "code_language", nullable = false)
    private CodeLanguage codeLanguage;
}
