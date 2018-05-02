package ru.ama0.book.entity;

import java.util.Calendar;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue
	private Long id;
	private String title = "";
	private String description = "";
	private String author = "";
	private String isbn = "";
	private Integer printYear = Calendar.getInstance().get(Calendar.YEAR);
	private Boolean readAlready = false;

	public Book(String title, String description, String author, String isbn, Integer printYear) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.isbn = isbn;
		this.printYear = printYear;
		this.readAlready = false;
	}

	public void flipReadAlready() {
		this.readAlready = !this.readAlready;
	}
}