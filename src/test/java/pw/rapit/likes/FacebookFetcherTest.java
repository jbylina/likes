package pw.rapit.likes;

import com.restfb.types.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.rapit.likes.domain.Like;
import pw.rapit.likes.service.FacebookFetcher;

import java.util.List;

@RunWith(JUnit4.class)
public class FacebookFetcherTest {

    static final Logger LOG = LoggerFactory.getLogger(FacebookFetcherTest.class);

    @Test
    public void shouldGetLikes() {
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        Like testLike = fetcher.getLikes("https://www.facebook.com/Arsenal/videos/10154511203247713/");

        LOG.debug("Ilosc likeów {} data: {} ", testLike.getCount(), testLike.getDate());
    }

    @Test
    public void shouldGetPosts(){
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        //returns arraylist
        //todo: this should return list of objects that contains
        List<Post> urlArray = fetcher.getPostsLinks("https://www.facebook.com/BuzzFeed", 20);

        for(Post a : urlArray)
            System.out.println(a.toString());

    }

    @Test
    public void shouldGetLikesById(){
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        Like testLike = fetcher.getLikesById("10154425314162713");

        LOG.debug("Ilosc likeów {} data: {} ", testLike.getCount(), testLike.getDate());

    }

}
