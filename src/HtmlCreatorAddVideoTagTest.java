import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class HtmlCreatorAddVideoTagTest {

    @BeforeClass
    public static void init() {
        String[] input = "add video video about java https://www.youtube.com/embed/RRubcjpTkks".split(" ");
        HtmlCreator.add(input);
    }

    @Test
    public void addVideo() {
        String expected = "<iframe pos=\"0\" descr=\"video about java\" src=\"https://www.youtube.com/embed/RRubcjpTkks\"></iframe><br>";
        assertEquals(expected, HtmlCreator.tagList.get(0));
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}