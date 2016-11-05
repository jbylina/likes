package pw.rapit.likes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

public class FacebookFetcher {


    void getLikes() {
        String accessToken = "EAACEdEose0cBACwf5ZBve9YXyLa0yvgZB8r5FyW7hwCwmhpHBGVHZBeXpngV6MkjNOAgKmFZB4eWuupOnwewxZAtFxVCrxrSu1M6DdYJY0X4SrJSki83jkoxJmgcOM0hTZAPcZBa48PctoPUmLWVvglZAFOGrV25fzWpeLlZCvcdpNZAXhVdxZBV3SQ";

        FacebookClient fbClient = new DefaultFacebookClient(accessToken);

        Post post = fbClient.fetchObject("1019734961433937", Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        Post.Comments comments = fbClient.fetchObject(post.getId() + "/comments", Post.Comments.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long commentsTotalCount = comments.getTotalCount();

        System.out.println(likesTotalCount);
    }


}
