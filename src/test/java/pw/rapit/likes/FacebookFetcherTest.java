package pw.rapit.likes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FacebookFetcherTest {

    @Test
    public void shouldGetLikes() {
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");

        Like testLike = fetcher.getLikes("https://www.facebook.com/BuzzFeed/videos/10155273876115329/");
        System.out.print("Ilosc likeów " + testLike.getCount() + " data: " + testLike.getDate() + ".");
    }

}
