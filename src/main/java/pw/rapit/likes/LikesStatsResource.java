package pw.rapit.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class LikesStatsResource {

    @Autowired
    LikeStatsRepository statsRepository;

    @RequestMapping("/test_date")
    public List<LikeStats> index() {
        return statsRepository.findAll();
    }

    @RequestMapping("/test")
    public String test() {
        Like like = new Like(10L);
        LikeStats test = new LikeStats("test");
        test.getLikes().add(like);

        test = statsRepository.save(test);

        return test.getId();
    }

    @RequestMapping("/test_new/{id}")
    public LikeStats testNew(@PathVariable String id) {
        LikeStats likeStats = statsRepository.findById(id);

        Like like = new Like(new Random().nextLong());
        likeStats.getLikes().add(like);

        statsRepository.save(likeStats);
        return likeStats;
    }
}
