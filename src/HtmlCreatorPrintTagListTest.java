import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlCreatorPrintTagListTest {

    @BeforeClass
    public static void init() {
        HtmlCreator.tagList.add("<p pos=\"0\" descr=\"id345\">some text here</p><br>");
        HtmlCreator.tagList.add("<a pos=\"1\" descr=\"visit W3Schools\" href=\"https://www.w3schools.com\">visit W3Schools</a><br>");
        HtmlCreator.tagList.add("<img pos=\"2\" descr=\"this is image\" src=\"dracaena-cinnabari.jpg\"><br>");
        HtmlCreator.print();
    }

    @Test
    public void printTagListIndex0Test() {
        String expected1 = "pos=\"0\" descr=\"id345\" ";
        StringBuilder descr = new StringBuilder();
        String result = "";
        String substring = HtmlCreator.tagList.get(0).substring(0, HtmlCreator.tagList.get(0).indexOf(">"));
        // substring is: <h2 pos="0" descr="mainheading"  OR <iframe pos="0" descr="video about java " src="https://www.youtube.com/embed/RRubcjpTkks"
        String[] tagListSplitterForPos = HtmlCreator.tagList.get(0).split(" ");
        String posTag = tagListSplitterForPos[1];
        String[] tagListSplitterForDescr = substring.split(" ");
        for (int j = 2; j < tagListSplitterForDescr.length; j++) {
            if (tagListSplitterForDescr[j].contains("href") || tagListSplitterForDescr[j].contains("src")) {
                break;
            }
            descr.append(tagListSplitterForDescr[j]).append(" ");
        }
        result = posTag + " " + descr;
        assertEquals(expected1, result);
    }

    @Test
    public void printTagListIndex1Test() {
        String expected2 = "pos=\"1\" descr=\"visit W3Schools\" ";
        StringBuilder descr = new StringBuilder();
        String result = "";
        String substring = HtmlCreator.tagList.get(1).substring(0, HtmlCreator.tagList.get(1).indexOf(">"));
        // substring is: <h2 pos="0" descr="mainheading"  OR <iframe pos="0" descr="video about java " src="https://www.youtube.com/embed/RRubcjpTkks"
        String[] tagListSplitterForPos = HtmlCreator.tagList.get(1).split(" ");
        String posTag = tagListSplitterForPos[1];
        String[] tagListSplitterForDescr = substring.split(" ");
        for (int j = 2; j < tagListSplitterForDescr.length; j++) {
            if (tagListSplitterForDescr[j].contains("href") || tagListSplitterForDescr[j].contains("src")) {
                break;
            }
            descr.append(tagListSplitterForDescr[j]).append(" ");
        }
        result = posTag + " " + descr;
        assertEquals(expected2, result);
    }

    @Test
    public void printTagListIndex2Test() {
        String expected3 = "pos=\"2\" descr=\"this is image\" ";
        StringBuilder descr = new StringBuilder();
        String result = "";
        String substring = HtmlCreator.tagList.get(2).substring(0, HtmlCreator.tagList.get(2).indexOf(">"));
        // substring is: <h2 pos="0" descr="mainheading"  OR <iframe pos="0" descr="video about java " src="https://www.youtube.com/embed/RRubcjpTkks"
        String[] tagListSplitterForPos = HtmlCreator.tagList.get(2).split(" ");
        String posTag = tagListSplitterForPos[1];
        String[] tagListSplitterForDescr = substring.split(" ");
        for (int j = 2; j < tagListSplitterForDescr.length; j++) {
            if (tagListSplitterForDescr[j].contains("href") || tagListSplitterForDescr[j].contains("src")) {
                break;
            }
            descr.append(tagListSplitterForDescr[j]).append(" ");
        }
        result = posTag + " " + descr;
        assertEquals(expected3, result);
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