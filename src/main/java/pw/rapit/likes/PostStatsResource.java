package pw.rapit.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class PostStatsResource {

    @Autowired
    PostStatsRepository postsRepository;

    @RequestMapping("/test_date")
    public List<PostStats> index() {
        return postsRepository.findAll();
    }

    @RequestMapping("/test")
    public String test() {
        Like like = new Like(10L);
        PostStats test = new PostStats("test");
        test.getLikes().add(like);

        test = postsRepository.save(test);

        return test.getId();
    }

    @RequestMapping("/test_new/{id}")
    public PostStats testNew(@PathVariable String id) {
        PostStats postStats = postsRepository.findById(id);

        Like like = new Like(new Random().nextLong());
        postStats.getLikes().add(like);

        postsRepository.save(postStats);
        return postStats;
    }
}
