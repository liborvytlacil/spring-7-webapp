package guru.springframework.spring7webapp.bootstrap;

import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBootstrap implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBootstrap.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public DataBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book = new Book();
        book.setTitle("Spring in Action");
        book.setIsbn("978-1617292237");
        book = bookRepository.save(book);

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Cleese");
        author = authorRepository.save(author);

        /* normally we would add the book to the author's books collection as well (bi-directional relationship), but
        we do not need to as the book collection in author's entity has mappedBy property set. I.e. it is the book entity that owns the relationship.
         */
        book.getAuthors().add(author);
        //author.getBooks().add(book);
        //authorRepository.save(author);
        book = bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Wrox Press");
        publisher.setAddress("100 Main Street");
        publisher.setCity("Boston");
        publisher.setState("Boston");
        publisher.setZipcode("02115");
        publisher = publisherRepository.save(publisher);

        book.setPublishers(publisher);
        book = bookRepository.save(book);


        LOGGER.info("Data Bootstrap Complete");
        LOGGER.info("Publishers: {}", publisherRepository.count());
        LOGGER.info("Authors: {}", authorRepository.count());
        LOGGER.info("Books: {}", bookRepository.count());


    }
}
