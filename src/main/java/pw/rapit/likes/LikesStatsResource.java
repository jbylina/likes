package pw.rapit.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LikesStatsResource {

    @Autowired
    LikeStatsRepository statsRepository;

    @RequestMapping("/")
    public List<LikeStats> index() {
        return statsRepository.findAll();
    }

    @RequestMapping("/test")
    public String test() {
        LikeStats test = new LikeStats("test");
        test = statsRepository.save(test);

        return test.getId();
    }
}
