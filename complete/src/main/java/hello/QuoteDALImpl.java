package hello;

import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDALImpl implements QuoteDAL {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public QuoteDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Quote save(Quote quote) {
        mongoTemplate.save(quote);
        return quote;
    }

    @Override
    public List<Quote> findAll() {
        return mongoTemplate.findAll(Quote.class);
    }

    @Override
    public boolean exist(Quote quote) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("celebrity").is(quote.getCelebrity()),
                Criteria.where("quote").is(quote.getQuote())
        ));
        return mongoTemplate.exists(query, Quote.class);
    }

    @Override
    public Quote findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Quote.class);
    }

    @Override
    public Quote findOneByCelebrity(String celebrity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("celebrity").is(celebrity));
        return mongoTemplate.findOne(query, Quote.class);
    }

    @Override
    public List<Quote> findAllByCelebrity(String celebrity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("celebrity").is(celebrity));
        return mongoTemplate.find(query, Quote.class);
    }

    /**
     * update via @id
     * @param quote quote to be updated
     * @return updated quote
     */
    @Override
    public Quote update(Quote quote) {
        mongoTemplate.save(quote);
        return quote;
    }

    @Override
    public void delete(Quote quote) {
        mongoTemplate.remove(quote);
    }
}
