package pw.rapit.likes;

import org.springframework.data.annotation.Id;

public class LikeStats {

    @Id
    private String id;

    private String postId;

    public LikeStats(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "LikeStats{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
