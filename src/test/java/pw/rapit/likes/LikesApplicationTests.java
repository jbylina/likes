package pw.rapit.likes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikesApplicationTests {

    @Autowired
    PostStatsRepository repository;




    @Test
    public void contextLoads() {
        String postId = "21898300328_10155368824645329";
        Like sourceLike = new Like(10L);
        PostStats test = new PostStats("test", postId);
        test.getLikes().add(sourceLike);
        repository.save(test);


        PostStats postStats = repository.findById(postId).getLikes();
        FacebookFetcher fetcher = new FacebookFetcher("1774967242758495", "df4be9b7a5bb62ab33c632fcf87d565f");
        Like updatingLike = fetcher.getLikesById(postId);
        postStats.getLikes().add(updatingLike);
        repository.delete(postStats);
        repository.save(postStats);


    }

}
