package pw.rapit.likes.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class PostStats {

    @Id
    private String id;

    private String postUrl;

    private List<LikesStatus> likesStatuses;

    public PostStats(String postUrl) {
        this.postUrl = postUrl;
        this.likesStatuses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public List<LikesStatus> getLikesStatuses() {
        return likesStatuses;
    }

    @Override
    public String toString() {
        return "PostStats{" +
                "id='" + id + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", likesStatuses=" + likesStatuses +
                '}';
    }
}
