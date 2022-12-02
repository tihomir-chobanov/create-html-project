import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlCreatorPrintTagListEmptyTest {

    @BeforeClass
    public static void init() {
        System.out.println(HtmlCreator.tagList.size());
        HtmlCreator.print();
    }

    @Test
    public void printTagListEmptyTest() {
        assertEquals(0, HtmlCreator.tagList.size());
    }


    @AfterClass
    public static void cleanUp() {
        HtmlCreator.tagList = new ArrayList<>();
    }




    /*
    pos="0" descr="id345"
    pos="1" descr="visit W3Schools"
    pos="2" descr="this is image"
     */
}