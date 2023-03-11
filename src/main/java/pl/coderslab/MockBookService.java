package pl.coderslab;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MockBookService implements  BookService{

    private List<Book> books;

    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book( "9788324631766", "Thiniking in Java", "Bruce Eckel", "Helion", "programming"));
        books.add(new Book( "9788324627738", "Rusz glowa Java.", "Sierra Kathy,	Bates Bert", "Helion", "programming"));
        books.add(new Book( "9780130819338", "Java 2.	Podstawy", "Cay Horstmann,	Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void add(Book book) {
        book.setId();
        books.add(book);
    }

    @Override
    public Optional<Book> get(Long id) {
        return books.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            books.remove(this.get(id).get());
        }
    }


    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = books.indexOf(this.get(book.getId()).get());
            books.set(indexOf, book);
        }
    }


}
