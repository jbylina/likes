package pw.rapit.likes;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class LikeStats {

    @Id
    private String id;

    private String postTitle;

    private List<Like> likes;

    public LikeStats(String postTitle) {
        this.postTitle = postTitle;
        this.likes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public List<Like> getLikes() {
        return likes;
    }
}
