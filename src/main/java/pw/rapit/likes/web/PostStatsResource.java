package pw.rapit.likes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pw.rapit.likes.domain.*;
import pw.rapit.likes.domain.repositories.JobStatsRepository;
import pw.rapit.likes.domain.repositories.PostStatsRepository;
import pw.rapit.likes.service.FacebookFetcherService;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
public class PostStatsResource {

    static final Logger LOG = LoggerFactory.getLogger(PostStatsResource.class);

    private final FacebookFetcherService facebookFetcherService;
    private final PostStatsRepository postStatsRepository;
    private final JobStatsRepository jobStatsRepository;


    @Autowired
    public PostStatsResource(FacebookFetcherService facebookFetcherService, PostStatsRepository postStatsRepository, JobStatsRepository jobStatsRepository) {
        this.facebookFetcherService = facebookFetcherService;
        this.postStatsRepository = postStatsRepository;
        this.jobStatsRepository = jobStatsRepository;
    }

    @RequestMapping(value = "/api/process-post", method = RequestMethod.POST)
    public ResponseEntity<PostStats> process(@RequestBody ProcessPostRequest processRequest) {
        return ResponseEntity.ok()
                .body(facebookFetcherService.addPost(processRequest.getPostUrl()));
    }

    @RequestMapping("/api/get-post-stats/{id}")
    public ResponseEntity<PostStats> getLikesStatuses(@PathVariable String id) {
        PostStats stats = postStatsRepository.findById(id);
        if (stats != null) {
            return ResponseEntity.ok().body(stats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/api/get-last-posts")
    public ResponseEntity<List<PostStats>> getLastPosts() {
        return ResponseEntity.ok()
                .body(postStatsRepository.findTop10ByOrderByCreateDateDesc());
    }

    @RequestMapping("/api/stats")
    public ResponseEntity<AggregatedJobStats> getStats() {
        return ResponseEntity.ok()
                .body(jobStatsRepository.overallJobStats());
    }

    @RequestMapping("/post.html")
    public String postPreviewPage(@RequestParam String id) {
        if(isNotEmpty(id) && postStatsRepository.findById(id) != null) {
            return "forward:/static/post.html";
        } else {
            return "redirect:/";
        }
    }
}
