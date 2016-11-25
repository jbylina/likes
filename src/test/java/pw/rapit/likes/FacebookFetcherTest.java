package pw.rapit.likes;

import com.restfb.types.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class FacebookFetcherTest {

    @Test
    public void shouldGetLikes() {
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        Like testLike = fetcher.getLikes("https://www.facebook.com/BuzzFeed/videos/10155273876115329/");
        System.out.print("Ilosc likeów " + testLike.getCount() + " data: " + testLike.getDate() + ".");
    }

    @Test
    public void shouldGetPosts(){
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        //returns linkedlist
        List<Post> urlArray = fetcher.getPostsLinks("https://www.facebook.com/BuzzFeed", 20);

        for(Post a : urlArray)
            System.out.println(a.toString());

    }

}
