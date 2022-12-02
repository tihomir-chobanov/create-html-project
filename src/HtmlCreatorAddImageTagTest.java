import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HtmlCreatorAddImageTagTest {

    @BeforeClass
    public static void init() {
        String[] input = "add image this is image dracaena-cinnabari.jpg".split(" ");
        HtmlCreator.add(input);
        System.out.println("Size of tagList is: " + HtmlCreator.tagList.size());
    }

    @Test
    public void addImage() {
        String expected = "<image pos=\"0\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>";
        assertEquals(expected, HtmlCreator.tagList.get(0));
        assertTrue(HtmlCreator.tagList.get(0).contains(expected));
        assertEqualsFromExpectedString(expected); // here we have test implementation

    }
   private void assertEqualsFromExpectedString(String template) {
        assertEquals(template, HtmlCreator.tagList.get(0));
    }

    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }

}