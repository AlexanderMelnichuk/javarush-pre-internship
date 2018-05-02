package ru.ama0.book.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ama0.book.dto.BookWithChapters;

@SqlResultSetMapping(
        name = "BookWithChaptersMapping",
        classes = @ConstructorResult(
                targetClass = BookWithChapters.class,
                columns = {
                        @ColumnResult(name = "title"),
                        @ColumnResult(name = "author"),
                        @ColumnResult(name = "chapter")}))
@NamedNativeQuery(
        name = "Book.findBookChapters",
        query = "select b.title, b.author, c.title as chapter " +
                "from book b inner join chapter c on c.book_id = b.id " +
                "where c.title like :chapter " +
                "order by c.title DESC",
        resultSetMapping = "BookWithChaptersMapping")

@Entity
@Table(name = "book")
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Chapter> chapters = new ArrayList<>();

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