package pw.rapit.likes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
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

    private int likesStatusesCount;

    private List<LikesStatus> likesStatuses;

    @CreatedDate
    private LocalDateTime createDate;

    public PostStats(String postUrl, String pageId, String postId) {
        this.postUrl = postUrl;
        this.pageId = pageId;
        this.postId = postId;
        this.likesStatusesCount = 0;
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

    public int getLikesStatusesCount() {
        return likesStatusesCount;
    }

    public List<LikesStatus> getLikesStatuses() {
        return likesStatuses;
    }

    public void addLikeStatus(LikesStatus status) {
        this.likesStatuses.add(status);
        this.likesStatusesCount++;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
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
