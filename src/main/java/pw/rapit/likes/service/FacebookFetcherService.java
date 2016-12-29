package pw.rapit.likes.service;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pw.rapit.likes.domain.LikesStatus;
import pw.rapit.likes.domain.PostStats;
import pw.rapit.likes.domain.PostStatsRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.substring;

@Service
class FacebookFetcherService {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookFetcherService.class);

    private static final Pattern FB_URL_PATTERN =
            Pattern.compile("(?:(?:http|https):\\/\\/)?(?:www.)?(mbasic.facebook|m\\.facebook|facebook|fb)\\.(com|me)\\/");

    private static final String FORWARD_SLASH = "/";

    private FacebookClient fbClient;

    private PostStatsRepository postStatsRepository;

    @Autowired
    FacebookFetcherService(FacebookClient fbClient, PostStatsRepository postStatsRepository) {
        this.fbClient = fbClient;
        this.postStatsRepository = postStatsRepository;
    }

    String getPageId(String pageName) {
        Page page = fbClient.fetchObject(pageName, Page.class, Parameter.with("fields", "id"));
        return page.getId();
    }

    LikesStatus getLikesStatus(String pageId, String postId) {
        String queryPostId = pageId + "_" + postId + "/likes";

        Post.Likes likes = fbClient.fetchObject(queryPostId, Post.Likes.class,
                Parameter.with("summary", 1), Parameter.with("limit", 0));

        return new LikesStatus(likes.getTotalCount());
    }

    @Scheduled(fixedRate = 30000)
    public void runFetchingJob() {
        LOG.info("Fetching job started");
        List<PostStats> postStatsList = postStatsRepository.findAll();

        for (PostStats postStats : postStatsList) {
            String postUrl = postStats.getPostUrl();
            LOG.debug("Fetching likes for {}", postUrl);

            String pageId = getPageId(extractPageName(postUrl));
            String postId = extractPostId(postUrl);

            LikesStatus likesStatus = getLikesStatus(pageId, postId);

            postStats.getLikesStatuses().add(likesStatus);

            LOG.debug("Current likes status is {} [{}]", likesStatus.getCount(), postUrl);
            postStatsRepository.save(postStats);
        }

        LOG.info("Fetching job finished. Processed {} posts", postStatsList.size());
    }

    static String extractPostId(final String url) {
        return extractSectionFromFacebookUrl(url, 2);
    }

    static String extractPageName(final String url) {
        return extractSectionFromFacebookUrl(url, 0);
    }

    private static String extractSectionFromFacebookUrl(final String url, int sectionNumber) {
        Matcher matcher = FB_URL_PATTERN.matcher(url);
        if (matcher.find()){
            String substringWithPageName = substring(url, matcher.end());
            return split(substringWithPageName, FORWARD_SLASH, 4)[sectionNumber];
        }
        return url;
    }
}

