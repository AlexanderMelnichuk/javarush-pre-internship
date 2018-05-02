package ru.ama0.book.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table
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

	public Book() {
	}
	
	public Book(String title, String description, String author, String isbn, Integer printYear) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.isbn = isbn;
		this.printYear = printYear;
		this.readAlready = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPrintYear() {
		return printYear;
	}

	public void setPrintYear(Integer printYear) {
		this.printYear = printYear;
	}

	public Boolean getReadAlready() {
		return readAlready;
	}

	public void setReadAlready(Boolean readAlready) {
		this.readAlready = readAlready;
	}
	
	public void flipReadAlready() {
		this.readAlready = !this.readAlready;
	}
}