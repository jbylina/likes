package pw.rapit.likes.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class ProcessPostRequest {

    @NotEmpty
    @JsonProperty("post_url")
    private String postUrl;

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
