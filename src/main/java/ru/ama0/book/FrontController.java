package ru.ama0.book;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import ru.ama0.book.entity.Book;
import ru.ama0.book.repository.BookRepository;

@Controller
public class FrontController {
	public static final int PAGE_SIZE = 5;

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/")
	public String getIndex() {
		return "redirect:/page/1";
	}

	@GetMapping("/page")
	public String getPageNull() {
		return "redirect:/page/1";
	}

	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	public String getBookPage(@PathVariable Integer pageNumber, String search, Model model) {

		if ((pageNumber > 1) && (pageNumber > bookRepository.getMaxPage(PAGE_SIZE))) {
			return "redirect:/page/" + bookRepository.getMaxPage(PAGE_SIZE);
		}

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "author");

		Page<Book> page;
		if (search == null || search == "") {
			search = null;
			page = bookRepository.findAll(request);
		} else
			page = bookRepository.findByTitleLikeOrderByAuthorAsc(search, request);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("search", search);
		model.addAttribute("page", page);
		model.addAttribute("books", page.getContent());
		model.addAttribute("startPage", begin);
		model.addAttribute("endPage", end);
		model.addAttribute("currentPage", current);

		return "/pages";
	}

	@RequestMapping(value = "/create")
	@ResponseBody
	public Book newBook(String title, String description, String author, String isbn, Integer printYear) {
		Book book = new Book(title, description, author, isbn, 2018);
		return bookRepository.saveAndFlush(book);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("bookId", 0);
		model.addAttribute("author", "");
		model.addAttribute("description", "");
		model.addAttribute("title", "");
		model.addAttribute("isbn", "");
		model.addAttribute("printYear", Calendar.getInstance().get(Calendar.YEAR));
		return "/editor";
	}

	@RequestMapping(value = "/edit/{bookId}", method = RequestMethod.GET)
	public String getEditor(@PathVariable Long bookId, Integer page, Model model) {
		Book book;
		if (bookId == 0) {
			book = new Book();
		} else {
			book = bookRepository.findOne(bookId);
		}

		model.addAttribute("author", book.getAuthor());
		model.addAttribute("description", book.getDescription());
		model.addAttribute("title", book.getTitle());
		model.addAttribute("isbn", book.getIsbn());
		model.addAttribute("printYear", book.getPrintYear());

		model.addAttribute("page", page);
		return "/editor";
	}

	@RequestMapping(value = "/edit/{bookId}", method = RequestMethod.POST)
	public String edit(Long bookId, String author, String title, String description, String isbn, Integer printYear,
			Integer page, Model model) {
		Book book;
		if (bookId == 0) {
			book = new Book();
			if (author != null)
				book.setAuthor(author);
		} else {
			book = bookRepository.findOne(bookId);
		}

		if (title != null)
			book.setTitle(title);
		if (description != null)
			book.setDescription(description);
		if (isbn != null)
			book.setIsbn(isbn);
		if (printYear != null)
			book.setPrintYear(printYear);

		book.setReadAlready(false);

		bookRepository.saveAndFlush(book);

		if (page == null || page <= 0)
			page = 1;
		return "redirect:/page/" + page;
	}

	@RequestMapping(value = "/markRead/{bookId}")
	public String markRead(@PathVariable Long bookId, Integer page, String search) {
		if (page == null || page <= 0)
			page = 1;
		Book book = bookRepository.getOne(bookId);
		book.flipReadAlready();
		bookRepository.saveAndFlush(book);
		String redirect = "redirect:/page/" + page;
		if (search != null)
			try {
				redirect += "?search=" + UriUtils.encodeQuery(search, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return redirect;
	}

	@RequestMapping(value = "/delete/{bookId}")
	public String delete(@PathVariable Long bookId, Integer page) {
		if (page == null || page <= 0)
			page = 1;

		bookRepository.delete(bookId);
		if ((page > 1) && (page > bookRepository.getMaxPage(PAGE_SIZE))) {
			return "redirect:/page/" + bookRepository.getMaxPage(PAGE_SIZE);
		}
		return "redirect:/page/" + page;
	}

	@RequestMapping("/allbooks")
	@ResponseBody
	public List<Book> getAllBooks(Integer page) {
		return bookRepository.findAll();
	}

	@RequestMapping("/year")
	@ResponseBody
	public List<Book> getBooksByPrintYear() {
		List<Book> books = bookRepository.findByPrintYearBetween(1995, 1998);
		return books;
	}

	@RequestMapping("/author")
	@ResponseBody
	public List<Book> getBooksByAuthor() {
		List<Book> books = bookRepository.findByAuthor();
		return books;
	}

	@RequestMapping(value = "/set", produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public Integer setAuthor() {
		return bookRepository.setAuthor("Дэн Dan", 1L);
	}

}
