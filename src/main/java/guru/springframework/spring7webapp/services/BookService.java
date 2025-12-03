package guru.springframework.spring7webapp.services;

import guru.springframework.spring7webapp.domain.Book;

public interface BookService {
    /**
     *
     * {@return all books from the repository}
     */
    Iterable<Book> findAll();
}
