package ru.ama0.book.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.entity.Book;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	  @Query("from Book b where b.printYear >= ?1 and b.printYear <= ?2")
	  List<Book> findByPrintYear(int from, int to);

	  List<Book> findByPrintYearBetween(int from, int to);
	  
	  @Query("from Book b where b.author like '%Дэн%'")
	  List<Book> findByAuthor();

	  List<Book> findByAuthor(String author);
	  
	  @Modifying
	  @Query("update Book b set b.author = :author where b.id = :id")
	  int setAuthor(@Param("author") String author, @Param("id") Long id);

	  @Modifying
	  void deleteById(Long id);
	  
	  default public long getMaxPage(int recordsPerPage) {
		  if (this.count() == 0 || this.count() <= recordsPerPage) 
			  return 1L;
		  return (this.count() - 1) / recordsPerPage + 1;
	  }

	  List<Book> findByPrintYearIn(Integer ... years);

	  List<Book> findByTitleLikeAndPrintYearIn(String title, Integer ... years);

	  Page<Book> findByTitleLikeOrderByAuthorAsc(String title, Pageable pageable);
}

