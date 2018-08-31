package hello;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * MongoTemplate is more flexible compared to MongoRepository
 */
public interface QuoteDAL {
    Quote save(Quote quote);

    List<Quote> findAll();

    boolean exist(Quote quote);

    Quote findById(String id);

    Quote findOneByCelebrity(String celebrity);

    List<Quote> findAllByCelebrity(String celebrity);

    Quote update(Quote quote);

    void delete(Quote quote);
}
