package pw.rapit.likes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

public class FacebookFetcher {


    void getLikes() {
        String accessToken = "EAACEdEose0cBAAwbZBU0gyIgSwsjPBLHakKkLfA0g5IqvIZBX95dnbApCgFdDXEBoandQeI14vrbkMYrCZAdzGlt9jCPunFaahcFDd08RuPFOjVrkJe3e9dE729kdgbzts0ZAizwXHjSQa2buaR4LyO8fZBgt4MPd4arcC7ZBrg1zzAQSWtvCa";

        FacebookClient fbClient = new DefaultFacebookClient(accessToken);

        Post post = fbClient.fetchObject("1019734961433937", Post.class);
        Post.Likes likes = fbClient.fetchObject(post.getId() + "/likes", Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long likesTotalCount = likes.getTotalCount();

        Post.Comments comments = fbClient.fetchObject(post.getId() + "/comments", Post.Comments.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));
        long commentsTotalCount = comments.getTotalCount();

        System.out.println(likesTotalCount);
        //dodałem komentarz
    }


}
