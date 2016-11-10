package pw.rapit.likes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;

public class FacebookFetcher {

    LoggedInFacebookClient fbClient;

    public FacebookFetcher(String appId, String secretKey){
        fbClient = new LoggedInFacebookClient(appId, secretKey);
    }

    void getLikes(String url) {
        //some url's have / as last char
        if(url.substring(url.length()-1,url.length()).equals("/"))
            url = url.substring(1,url.length()-1);
        String postId = url.substring(url.lastIndexOf("/")+1,url.length());

        Post post = fbClient.fetchObject(postId, Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        System.out.println(likesTotalCount);



    }

    public class LoggedInFacebookClient extends DefaultFacebookClient {

        public LoggedInFacebookClient(String appId, String appSecret) {
            AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
            this.accessToken = accessToken.getAccessToken();
        }

    }
}
