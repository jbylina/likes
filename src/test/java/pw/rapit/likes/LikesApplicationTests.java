package pw.rapit.likes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pw.rapit.likes.domain.PostStatsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikesApplicationTests {

    @Autowired
    PostStatsRepository repository;



    @Test
    public void contextLoads() {

    }

}
