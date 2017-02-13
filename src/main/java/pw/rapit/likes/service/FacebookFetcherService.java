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

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class FacebookFetcherService {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookFetcherService.class);

    private static final Pattern FB_URL_PATTERN =
            Pattern.compile("(?:(?:http|https)://)?(?:www.)?(mbasic.facebook|m\\.facebook|facebook|fb)\\.(com|me)/");

    private static final String FORWARD_SLASH = "/";

    private FacebookClient fbClient;

    private PostStatsRepository postStatsRepository;

    @Autowired
    FacebookFetcherService(FacebookClient fbClient, PostStatsRepository postStatsRepository) {
        this.fbClient = fbClient;
        this.postStatsRepository = postStatsRepository;
    }

    public PostStats addPost(String postUrl) {
        if (!FB_URL_PATTERN.matcher(postUrl).find()) {
            throw new IllegalArgumentException("Bad url format");
        }

        String pageName = extractPageName(postUrl);
        String postId = extractPostId(postUrl);

        if (isEmpty(pageName)) {
            throw new IllegalArgumentException("Empty page Id");
        } else if (isEmpty(postId)) {
            throw new IllegalArgumentException("Empty post Id");
        }

        String pageId = getPageId(pageName);

        try {
            getLikesStatus(pageId, postId);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return postStatsRepository.save(new PostStats(postUrl, pageId, postId));
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

    @Scheduled(fixedRate = 60000)
    void fetchingJob() {
        LOG.info("Fetching job started");

        List<PostStats> postStatsList = postStatsRepository.getPostStatsToProcess();

        for (PostStats postStats : postStatsList) {
            LOG.debug("Fetching likes for {}", postStats.getPostUrl());

            LikesStatus likesStatus = getLikesStatus(postStats.getPageId(), postStats.getPostId());
            postStats.addLikeStatus(likesStatus);

            LOG.debug("Current likes status is {} [{}]", likesStatus.getCount(), postStats.getPostUrl());
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

            String[] split = split(substringWithPageName, FORWARD_SLASH, 4);

            if (sectionNumber < split.length) {
                return split[sectionNumber];
            } else {
                return null;
            }
        }
        return url;
    }
}

