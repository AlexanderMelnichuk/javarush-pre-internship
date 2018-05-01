package ru.ama0.book;

import static org.junit.Assert.assertEquals;

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

    @Before
    public void setUp() {
        book = bookRepository.save(new Book("Testing title", "Testing description",
                "Testing author", "Testing isbn", 2018));
        bookRepository.save(new Book("Second book", "Second description",
                "Second author", "Second isbn", 2015));
    }

    @Test
    public void bookAdditionTest() {
        assertEquals(1, bookRepository.findByPrintYear(2017, 2019).size());
    }

    @Test
    public void bookRemovalTest() {
        assertEquals(2, bookRepository.findAll().size());
        bookRepository.deleteById(book.getId());
        assertEquals(1, bookRepository.findAll().size());
    }
}
