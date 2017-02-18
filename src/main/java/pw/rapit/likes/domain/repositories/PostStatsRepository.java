package pw.rapit.likes.domain.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pw.rapit.likes.domain.PostStats;

import java.util.List;

public interface PostStatsRepository extends MongoRepository<PostStats, String> {

    PostStats findById(String id);

    @Query(fields = "{_id:1, postUrl:1, createDate:1}")
    List<PostStats> findTop10ByOrderByCreateDateDesc();

    @Query("{likesStatusesCount:{$lt : 50}}")
    List<PostStats> getPostStatsToProcess();
}
