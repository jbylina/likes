package pw.rapit.likes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.rapit.likes.domain.LikesStatus;
import pw.rapit.likes.domain.PostStats;
import pw.rapit.likes.domain.PostStatsRepository;

import java.util.List;
import java.util.Random;

@RestController
public class PostStatsResource {

    static final Logger LOG = LoggerFactory.getLogger(PostStatsResource.class);

    private PostStatsRepository postsRepository;

    @Autowired
    public PostStatsResource(PostStatsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @RequestMapping("/test_date")
    public List<PostStats> index() {
        return postsRepository.findAll();
    }

    @RequestMapping("/test")
    public String test() {
        PostStats test = new PostStats("https://www.facebook.com/Arsenal/videos/10154511203247713/");

        test = postsRepository.save(test);

        return test.getId();
    }

    @RequestMapping("/test_new/{id}")
    public PostStats testNew(@PathVariable String id) {
        return postsRepository.findById(id);
    }
}
