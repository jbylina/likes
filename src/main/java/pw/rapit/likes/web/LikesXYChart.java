package pw.rapit.likes.web;

import org.apache.commons.compress.utils.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pw.rapit.likes.domain.PostStats;
import pw.rapit.likes.domain.PostStatsRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class LikesXYChart {

    private PostStatsRepository postStatsRepository;

    @Autowired
    public LikesXYChart(PostStatsRepository postStatsRepository) {
        this.postStatsRepository = postStatsRepository;
    }

    private HashMap<Integer, Integer> getLikes() {
        HashMap<Integer, Integer> likesMap = new HashMap<>();
        likesMap.put(0,1);likesMap.put(1,2);likesMap.put(2,5);likesMap.put(3,15);
        likesMap.put(4,16);likesMap.put(5,20);likesMap.put(6,26);likesMap.put(7,30);
        likesMap.put(8,34);likesMap.put(9,38);likesMap.put(10,40);likesMap.put(11,45);
        likesMap.put(15,47);likesMap.put(20,50);likesMap.put(25,52);likesMap.put(30,55);
        likesMap.put(40,56);likesMap.put(50,56);likesMap.put(60,56);likesMap.put(70,57);
        return likesMap;
    }

    @RequestMapping("/getPost/{idPost}")
    public PostStats getPost(@PathVariable String idPost) {
        return postStatsRepository.findById(idPost);
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showImage() throws IOException{

        Map map = getLikes();
        XYSeries series = new XYSeries("Likes");
        Set set = map.keySet();
        for (Object x : set) {
            series.add((Integer)x, (Integer)map.get(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Likes",
                "Time",
                "Number of likes",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        String property = System.getProperty("user.dir");

        try {
            ChartUtilities.saveChartAsJPEG(new File(property + "/chart.jpg"), chart, 500, 300);
        } catch (Exception e) {
            System.out.println("Problem occurred creating chart.");
        }

        InputStream in = new BufferedInputStream(new FileInputStream(property + "/chart.jpg"));
        return IOUtils.toByteArray(in);
    }
}