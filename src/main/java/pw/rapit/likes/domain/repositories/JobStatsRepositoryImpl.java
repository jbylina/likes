package pw.rapit.likes.domain.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import pw.rapit.likes.domain.AggregatedJobStats;
import pw.rapit.likes.domain.JobStats;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class JobStatsRepositoryImpl implements JobStatsRepositoryCustom {

    private final MongoOperations operations;

    @Autowired
    public JobStatsRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public AggregatedJobStats overallJobStats() {

        final String totalProcessedPosts = "totalProcessedPosts";
        final String totalJobDuration = "totalJobDuration";

        Aggregation aggregation = newAggregation(
                group()
                        .sum("processedPosts").as(totalProcessedPosts)
                        .sum("durationInMilliseconds").as(totalJobDuration)
        );

        return operations.aggregate(aggregation, JobStats.class, AggregatedJobStats.class)
                .getUniqueMappedResult();
    }
}
