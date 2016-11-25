package pw.rapit.likes;

import com.restfb.*;
import com.restfb.experimental.api.Posts;
import com.restfb.types.Page;
import com.restfb.types.Post;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.StringUtils.endsWith;

public class FacebookFetcher {

    LoggedInFacebookClient fbClient;

    public FacebookFetcher(String appId, String secretKey){
        fbClient = new LoggedInFacebookClient(appId, secretKey);
    }

    public Like getLikes(String url) {

        if (endsWith(url, "/")) {
            url = substringBeforeLast(url, "/");
        }

        String postId = url.substring(url.lastIndexOf("/")+1,url.length());

        Post post = fbClient.fetchObject(postId, Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        return new Like(likesTotalCount);
    }

    public List getPostsLinks(String pageUrl, int hours){
        Page page =  fbClient.fetchObject(pageUrl, Page.class);
        Connection<Post> pageFeed = fbClient.fetchConnection(page.getId() + "/feed", Post.class);

        List<Post> urlArray;

        urlArray = new ArrayList<Post>(pageFeed.getData());
        /*
        while (pageFeed.hasNext()) {
            urlArray.add(pageFeed.getNextPageUrl());
            pageFeed = fbClient.fetchConnectionPage(pageFeed.getNextPageUrl(),Post.class);
        }
        */
        //removes posts older than 1 hour
        Date now = new Date();
        List<Post> temp = new ArrayList<Post>(urlArray);
        for(Post post : temp){
            if(now.getTime() - post.getCreatedTime().getTime() > hours*60*60*1000)
                urlArray.remove(post);
        }
        return urlArray;
    }

    public class LoggedInFacebookClient extends DefaultFacebookClient {

        public LoggedInFacebookClient(String appId, String appSecret) {
            AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
            this.accessToken = accessToken.getAccessToken();
        }

    }
}
