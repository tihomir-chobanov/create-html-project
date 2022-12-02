import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HtmlCreatorRemoveTagTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<a pos=\"0\" descr=\"visit W3Schools\" href=\"https://www.w3schools.com\">visit W3Schools</a><br>");
        HtmlCreator.tagList.add("<img pos=\"0\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
        String[] input = "remove visit W3Schools".split(" ");
        HtmlCreator.remove(input);
    }

    @Test
    public void removeVisitW3SchoolsHtmlCreatorRemoveTest() {
        assertFalse(HtmlCreator.tagList.contains("visit W3Schools"));
    }

    @Test
    public void changeSizeOfTagListAfterRemoval() {
        assertEquals(2, HtmlCreator.tagList.size());
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}