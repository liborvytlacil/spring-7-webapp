package guru.springframework.spring7webapp.services;

import guru.springframework.spring7webapp.domain.Author;

public interface AuthorService {
    /**
     *
     * {@return all authors from the repository}
     */
    Iterable<Author> findAll();
}
