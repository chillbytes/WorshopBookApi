package pl.coderslab;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;

    public BookController(BookService bookService)  {
        this.bookService = bookService;
    }



    @GetMapping("")
    @ResponseBody
    public List<Book> getList() {
        return bookService.getBooks();
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book("9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        });
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }





    @PostMapping("/test")
    public void handlePostRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        int contentLength  = request.getContentLength();

        System.out.println("\n\n\n\n\nContent type: " + contentType + "length: " + contentLength + "\n\n\n\n\n\n");

    }













}

