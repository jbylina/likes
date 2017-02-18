package pw.rapit.likes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pw.rapit.likes.domain.LikesStatus;
import pw.rapit.likes.domain.PostStats;
import pw.rapit.likes.domain.repositories.PostStatsRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class FacebookFetcherServiceIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(FacebookFetcherServiceIntegrationTest.class);

    @MockBean
    private PostStatsRepository statsRepository;

    @Autowired
    private FacebookFetcherService testedObject;

    @Test
    public void shouldGetLikes() {
        LikesStatus testLikesStatus = testedObject.getLikesStatus("20669912712", "10154511203247713");

        assertNotNull(testLikesStatus);
        assertTrue(testLikesStatus.getCount() > 0);
    }

    @Test
    public void shouldGetPageId() throws Exception {

        String pageId = testedObject.getPageId("Arsenal");

        assertEquals("20669912712", pageId);
    }

    @Test
    public void shouldFetchLikesForPage() throws Exception {
        // given
        PostStats postStats = new PostStats("https://www.facebook.com/Arsenal/videos/10154511203247713/",
                "20669912712", "10154511203247713");
        List<PostStats> postStatsList = Collections.singletonList(postStats);
        when(statsRepository.getPostStatsToProcess()).thenReturn(postStatsList);

        // when
        testedObject.fetchingJob();

        // then
        verify(statsRepository, times(1)).save(any(PostStats.class));
        ArgumentCaptor<PostStats> argument = ArgumentCaptor.forClass(PostStats.class);
        verify(statsRepository).save(argument.capture());
        assertEquals(argument.getValue().getLikesStatuses().size(), 1);
    }
}
