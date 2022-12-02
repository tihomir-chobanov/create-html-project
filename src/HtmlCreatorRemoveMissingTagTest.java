import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlCreatorRemoveMissingTagTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<a pos=\"0\" descr=\"visit W3Schools\" href=\"https://www.w3schools.com\">visit W3Schools</a><br>");
        HtmlCreator.tagList.add("<img pos=\"2\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
        String[] input = "remove java video".split(" ");
        HtmlCreator.remove(input);
    }

    @Test
    public void removeJavaVideo() {
        assertEquals(3, HtmlCreator.tagList.size());
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}