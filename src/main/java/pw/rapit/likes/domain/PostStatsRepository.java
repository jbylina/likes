package pw.rapit.likes.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostStatsRepository extends MongoRepository<PostStats, String> {

    PostStats findById(String id);

    @Query("{likesStatusesCount:{$lt : 50}}")
    List<PostStats> getPostStatsToProcess();
}
