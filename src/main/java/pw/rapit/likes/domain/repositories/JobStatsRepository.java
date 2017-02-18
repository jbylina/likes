package pw.rapit.likes.domain.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pw.rapit.likes.domain.JobStats;

public interface JobStatsRepository extends MongoRepository<JobStats, String>, JobStatsRepositoryCustom {
}
