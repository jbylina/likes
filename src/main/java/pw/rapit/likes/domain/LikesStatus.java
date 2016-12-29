package pw.rapit.likes.domain;


import java.time.LocalDateTime;

public class LikesStatus {

    private Long count;

    private LocalDateTime date;

    public LikesStatus(Long count) {
        this.count = count;
        this.date = LocalDateTime.now();
    }

    public long getCount() {
        return count;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
