package pw.rapit.likes.domain;


import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class JobStats {

    @Id
    private String id;

    private long processedPosts;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private long durationInMilliseconds;

    public JobStats(int processedPosts, LocalDateTime startDate, LocalDateTime endDate, long durationInMilliseconds) {
        this.processedPosts = processedPosts;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public String getId() {
        return id;
    }

    public long getProcessedPosts() {
        return processedPosts;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long getDurationInMilliseconds() {
        return durationInMilliseconds;
    }
}
