package pw.rapit.likes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;

public class FacebookFetcher {


    void getLikes() {
        String accessToken = "EAAZAOUsPZBGV8BADUfh0CtigHv7KpwhIj9GL2sQ27NldSNlfeZC6QS1nZBou6T9C6lB2C3Ejm7oWZACHcyBZAvZAGvqssPRP2E5NTnRY5r5iVliDcBVTCw0yH3peKhmnavb7HVolXZCn6vmZAEbtvL2ZBmY4cfMntZCWy03VmpVKooNBfbxpFMZAZANGx";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);

        //post id:10155273876115329           buzzfeed chichacho cubs wins         https://www.facebook.com/BuzzFeed/videos/10155273876115329/
        Post post = fbClient.fetchObject("10155273876115329", Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        System.out.println(likesTotalCount);

    }


}
