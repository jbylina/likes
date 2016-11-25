package pw.rapit.likes;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class PostStats {

    @Id
    private String id;

    private String postTitle;

    private List<Like> likes;

    public PostStats(String postTitle) {
        this.postTitle = postTitle;
        this.likes = new ArrayList<>();
    }

    public PostStats(String postTitle, String postId) {
        this.postTitle = postTitle;
        this.likes = new ArrayList<>();
        this.id = postId;
    }

    public PostStats(PostStats postStats){
        this.postTitle = postStats.getPostTitle();
        this.id = postStats.getId();
        this.likes = postStats.getLikes();
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
