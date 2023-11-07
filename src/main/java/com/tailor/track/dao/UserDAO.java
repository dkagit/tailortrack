package com.tailor.track.dao;

import com.tailor.track.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;

@Component
public class UserDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Create or Update a user (upsert)
    public User save(User user) {
        return mongoTemplate.save(user);
    }


    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }
    public void deleteUser(String userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        mongoTemplate.remove(query,User.class);
    }
}
