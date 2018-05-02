package ru.ama0.book.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookWithChapters {
    @Id
    private Long id;
    private String title;
    private String author;
    private String chapter;

}
