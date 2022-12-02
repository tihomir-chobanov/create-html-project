import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class HtmlCreatorAddNormalTagTest {

    @BeforeClass
    public static void init() {
        String[] input = "add p id345 some text here".split(" ");
        HtmlCreator.add(input);
    }

    @Test
    public void addParagraph() {
        String expected = "<p pos=\"0\" descr=\"id345\">some text here</p><br>";
        assertEquals(expected, HtmlCreator.tagList.get(0));
    }
    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }
}
