package ru.ama0.book.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.dto.BookWithChapters;

@Transactional
@Repository
public interface BookWithChaptersRepository extends JpaRepository<BookWithChapters, Long> {

/*
    @Query (nativeQuery = true,
            value = "select c.id, b.title, b.author, c.title as chapter " +
            "from book b inner join chapter c on c.book_id = b.id " +
            "where c.title like :chapter " +
            "order by c.title DESC")
    List<BookWithChapters> findBookChapters(@Param("chapter") String chapter);
*/

    @Query (nativeQuery = true,
            value = "select c.id, b.title, b.author, c.title as chapter " +
                    "from book b inner join chapter c on c.book_id = b.id " +
                    "order by c.title DESC")
    List<BookWithChapters> findBookChapters();
}

