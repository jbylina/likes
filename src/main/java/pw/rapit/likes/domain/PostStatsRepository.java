package pw.rapit.likes.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostStatsRepository extends MongoRepository<PostStats, String> {

    PostStats findById(String id);
}
