package pw.rapit.likes;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeStatsRepository extends MongoRepository<LikeStats, String> {

    LikeStats findById(String id);
}
