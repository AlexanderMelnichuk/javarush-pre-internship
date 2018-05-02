package ru.ama0.book;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.entity.Book;
import ru.ama0.book.entity.Chapter;
import ru.ama0.book.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaConfiguration.class})
@ActiveProfiles("unittest")
@Transactional
@Rollback
public class BaseTest {

    @Autowired // Can also be @Resource instead of @Autowired
    private BookRepository bookRepository;

    private Book book;
    private Book book2;

    @Before
    public void setUp() {
        book = bookRepository.save(new Book("Testing title", "Testing description",
                "Testing author", "Testing isbn", 2018));
        book2 = bookRepository.save(new Book("Testing second book", "Second description",
                "Second author", "Second isbn", 2015));
    }

    @After
    public void tearDown() {
        bookRepository.deleteById(book.getId());
        bookRepository.deleteById(book2.getId());
    }

    @Test
    public void bookAdditionTest() {
        assertEquals(1, bookRepository.findByPrintYearBetween(2017, 2019).size());
    }

    @Test
    public void bookSelectInTest() {
        assertEquals(1, bookRepository.findByPrintYearIn(2015, 2019).size());
        assertEquals(2, bookRepository.findByTitleLikeAndPrintYearIn("Testing%", 2015, 2018).size());
    }


    @Test
    public void bookRemovalTest() {
        assertEquals(2, bookRepository.findAll().size());
        bookRepository.deleteById(book.getId());
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void addingChapterTest() {
        book.getChapters().add(new Chapter("Chapter 1. The test begins!"));
        book.getChapters().add(new Chapter("Chapter 2. The test continues..."));
        bookRepository.save(book);
    }

    @Test
    public void customQuery() {
        assertEquals(1, bookRepository.findSpecificBook(book.getId()).size());
    }
}
