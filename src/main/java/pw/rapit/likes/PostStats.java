package pw.rapit.likes;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class PostStats {

    @Id
    private String id;

    private String postUrl;

    private List<Like> likes;

    public PostStats(String postUrl) {
        this.postUrl = postUrl;
        this.likes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public List<Like> getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "PostStats{" +
                "id='" + id + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", likes=" + likes +
                '}';
    }
}
