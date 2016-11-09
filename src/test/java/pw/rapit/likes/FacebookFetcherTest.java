package pw.rapit.likes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FacebookFetcherTest {

    @Test
    public void shouldGetLikes() {
        FacebookFetcher fetcher = new FacebookFetcher();

        fetcher.getLikes("https://www.facebook.com/BuzzFeed/videos/10155273876115329/");
    }

}
