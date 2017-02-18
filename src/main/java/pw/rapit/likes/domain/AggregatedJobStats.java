package pw.rapit.likes.domain;

public class AggregatedJobStats {

    private Long totalProcessedPosts;

    private Long totalJobDuration;

    public AggregatedJobStats(Long totalProcessedPosts, Long totalJobDuration) {
        this.totalProcessedPosts = totalProcessedPosts;
        this.totalJobDuration = totalJobDuration;
    }

    public Long getTotalProcessedPosts() {
        return totalProcessedPosts;
    }

    public Long getTotalJobDuration() {
        return totalJobDuration;
    }
}
