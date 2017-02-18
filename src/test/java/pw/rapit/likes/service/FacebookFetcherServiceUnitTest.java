package pw.rapit.likes.service;

import com.restfb.FacebookClient;
import org.junit.Before;
import org.junit.Test;
import pw.rapit.likes.domain.repositories.JobStatsRepository;
import pw.rapit.likes.domain.repositories.PostStatsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class FacebookFetcherServiceUnitTest {


    private FacebookFetcherService testedObject;

    private PostStatsRepository postStatsRepository;

    private JobStatsRepository jobStatsRepository;

    private FacebookClient facebookClient;

    @Before
    public void setUp() throws Exception {
        facebookClient = mock(FacebookClient.class);
        postStatsRepository = mock(PostStatsRepository.class);
        jobStatsRepository = mock(JobStatsRepository.class);
        testedObject = new FacebookFetcherService(facebookClient, postStatsRepository, jobStatsRepository);
    }

    @Test
    public void shouldExtractPageName() throws Exception {

        List<String> inputs = Arrays.asList("https://www.facebook.com/CNNInternationalPoland/?fref=ts",
                "https://www.facebook.com/CNNInternationalPoland/",
                "https://www.facebook.com/CNNInternationalPoland",
                "http://www.facebook.com/CNNInternationalPoland",
                "CNNInternationalPoland",
                "facebook.com/CNNInternationalPoland",
                "facebook.com/CNNInternationalPoland/",
                "https://pl-pl.facebook.com/CNNInternationalPoland");

        String expectedFanpageName = "CNNInternationalPoland";

        inputs.forEach(input -> {
            String pageName = FacebookFetcherService.extractPageName(input);
            assertEquals(expectedFanpageName, pageName);
        });
    }

    @Test
    public void shouldExtractPostId() throws Exception {

        List<String> inputs = Arrays.asList("https://pl-pl.facebook.com/tylkomuzyka/posts/1290410664365151",
                "https://www.facebook.com/Arsenal/videos/1290410664365151/",
                "1290410664365151");

        String expectedPostId = "1290410664365151";

        inputs.forEach(input -> {
            String pageName = FacebookFetcherService.extractPostId(input);
            assertEquals(expectedPostId, pageName);
        });
    }
}
