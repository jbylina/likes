package pw.rapit.likes.service;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pw.rapit.likes.domain.Like;
import pw.rapit.likes.domain.PostStats;
import pw.rapit.likes.domain.PostStatsRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Component
public class FacebookFetcher {

    LoggedInFacebookClient fbClient;

    @Autowired
    PostStatsRepository postStatsRepository;

    public FacebookFetcher() {
        fbClient = new LoggedInFacebookClient("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");
    }

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

    public Like getLikesById(String postId) {


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
    @Scheduled(fixedRate = 10000)
    public void runJob(){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<PostStats> ourList =  postStatsRepository.findAll();
        int i = 0;
        for(i = 0; i < ourList.size();i++){
            PostStats stats  = ourList.get(i);
            Like currentLike = getLikes(stats.getPostUrl());
            stats.getLikes().add(currentLike);
            postStatsRepository.save(stats);

            System.out.println(stats.toString());

            List<String> likesString = stats.getLikes().stream()
                    .map(like -> "date: " + like.getDate() + "like count: " + like.getCount())
                    .collect(Collectors.toList());

            System.out.println(String.join(",", likesString));
        }

    }


    public class LoggedInFacebookClient extends DefaultFacebookClient {

        public LoggedInFacebookClient(String appId, String appSecret) {
            AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
            this.accessToken = accessToken.getAccessToken();
        }

    }
}

