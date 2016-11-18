package pw.rapit.likes;

import com.restfb.*;
import com.restfb.types.Page;
import com.restfb.types.Post;

public class FacebookFetcher {

    LoggedInFacebookClient fbClient;

    public FacebookFetcher(String appId, String secretKey){
        fbClient = new LoggedInFacebookClient(appId, secretKey);
    }

    public Like getLikes(String url) {
        //some url's have / as last char
        if(url.substring(url.length()-1,url.length()).equals("/"))
            url = url.substring(1,url.length()-1);
        String postId = url.substring(url.lastIndexOf("/")+1,url.length());

        Post post = fbClient.fetchObject(postId, Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        return new Like(likesTotalCount);



    }

    public String[] getPostsLinks(String pageUrl){
        Page page =  fbClient.fetchObject(pageUrl, Page.class);
        Connection<Post> pageFeed = fbClient.fetchConnection(page.getId() + "/feed", Post.class);
        return new String[0];
    }

    public class LoggedInFacebookClient extends DefaultFacebookClient {

        public LoggedInFacebookClient(String appId, String appSecret) {
            AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
            this.accessToken = accessToken.getAccessToken();
        }

    }
}
