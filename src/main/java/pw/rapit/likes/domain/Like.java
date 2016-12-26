package pw.rapit.likes.domain;

import java.util.Date;

public class Like {

    private Long count;

    private Date date;

    public Like(Long count) {
        this.count = count;
        this.date = new Date();
    }

    public long getCount() {
        return count;
    }

    public Date getDate() {
        return date;
    }
}
