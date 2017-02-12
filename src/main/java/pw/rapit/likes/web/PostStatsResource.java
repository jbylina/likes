package pw.rapit.likes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pw.rapit.likes.domain.PostStatsRepository;
import pw.rapit.likes.service.FacebookFetcherService;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
public class PostStatsResource {

    static final Logger LOG = LoggerFactory.getLogger(PostStatsResource.class);

    private FacebookFetcherService facebookFetcherService;

    private PostStatsRepository postStatsRepository;


    @Autowired
    public PostStatsResource(FacebookFetcherService facebookFetcherService, PostStatsRepository postStatsRepository) {
        this.facebookFetcherService = facebookFetcherService;
        this.postStatsRepository = postStatsRepository;
    }

    @RequestMapping(value = "/api/process-post", method = RequestMethod.POST)
    public ResponseEntity process(@RequestBody ProcessPostRequest processRequest) {
        return ResponseEntity.ok()
                .body(facebookFetcherService.addPost(processRequest.getPostUrl()));
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
