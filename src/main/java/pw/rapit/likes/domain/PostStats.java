package pw.rapit.likes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class PostStats {

    @Id
    private String id;

    private String postUrl;

    @JsonIgnore
    private String pageId;

    @JsonIgnore
    private String postId;

    private List<LikesStatus> likesStatuses;

    public PostStats(String postUrl, String pageId, String postId) {
        this.postUrl = postUrl;
        this.pageId = pageId;
        this.postId = postId;
        this.likesStatuses = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getPageId() {
        return pageId;
    }

    public String getPostId() {
        return postId;
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
