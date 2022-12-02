
import org.junit.*;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class getIndexOfSeachedTagTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("add video video about java https://www.youtube.com/embed/RRubcjpTkks");
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<img pos=\"0\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
    }

    @Test
    public void indexOfId345Is1() {
         assertEquals(1, HtmlCreator.getIndexOfSearchedTag(-1, "id345"));
    }

    @Test
    public void indexOfthisisimageIs2() {
        assertEquals(2, HtmlCreator.getIndexOfSearchedTag(-1, "this is image"));
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}