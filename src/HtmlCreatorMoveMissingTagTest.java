import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class HtmlCreatorMoveMissingTagTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<a pos=\"0\" descr=\"visit W3Schools\" href=\"https://www.w3schools.com\">visit W3Schools</a><br>");
        HtmlCreator.tagList.add("<img pos=\"2\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
        String[] input = "moveTo 2 java".split(" ");
        HtmlCreator.move(input);
    }

    @Test
    public void moveMissingTagTest() {
        assertFalse(HtmlCreator.tagList.get(2).contains("java"));
    }

    @After
    public void finish() {
        System.out.println("No changes in tagList: ");
        for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
            System.out.println(HtmlCreator.tagList.get(i));
        }
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }

}