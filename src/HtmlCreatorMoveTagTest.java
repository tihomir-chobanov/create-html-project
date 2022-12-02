import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlCreatorMoveTagTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<a pos=\"1\" descr=\"visit W3Schools\" href=\"https://www.w3schools.com\">visit W3Schools</a><br>");
        HtmlCreator.tagList.add("<img pos=\"2\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
        String[] input = "moveTo 2 id345".split(" ");
        HtmlCreator.move(input);
    }

    @Test
    public void moveId345Test() {
        assertTrue(HtmlCreator.tagList.get(2).contains("id345"));
    }

    @After
    public void finish() {
        System.out.println("After change: ");
        for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
            System.out.println(HtmlCreator.tagList.get(i));
        }
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}