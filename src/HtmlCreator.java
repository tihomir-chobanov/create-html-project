
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class HtmlCreator {

    private String tagType;
    private String description;
    private String content;

    static ArrayList<String> tagList = new ArrayList<>();

    static Document doc;

    public String getTagType() {
        return tagType;
    }

    public String getDescription() {
        return description;
    }

    public HtmlCreator(String tagType, String description, String content) {
        this.tagType = tagType;
        this.description = description;
        this.content = content;
    }

    public HtmlCreator(String tagType, String description) {
        this.tagType = tagType;
        this.description = description;
    }

    public static void add(String[] inputSplitter) {
        switch (inputSplitter[1]) {
            case "video":
                createVideoTag(inputSplitter);
                break;
            case "link":
                createLinkTag(inputSplitter);
                break;
            case "image":
                createImageTag(inputSplitter);
                break;
            default:
                createNormalTag(inputSplitter);
                break;
        }
    }

    public static void remove(String[] inputSplitter) {
        String descrToRemove = inputSplitter[1];
        try {
            boolean found = false;
            for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
                if (HtmlCreator.tagList.get(i).contains(descrToRemove)) {
                    found = true;
                    System.out.println("You removed a tag with descr: " + HtmlCreator.tagList.get(i));
                    HtmlCreator.tagList.remove(HtmlCreator.tagList.get(i));
                    break;
                }
            }
            if (!found) System.out.println("No such element!");
        } catch (Exception e) {
            System.out.println("EROR!");
            System.out.println(e.getMessage());
        }
    }

    public static void save(String filePath) throws IOException {
        createBody();
        String savedDocToString = convertDocToStringAndPrint();
        writeHtmlToFile(filePath, savedDocToString);
    }

    public static void move(String[] inputSplitter) {
        int to = Integer.parseInt(inputSplitter[1]);
        int from = -1;
        String descrOfSearchedTag = inputSplitter[2];
        from = getIndexOfSearchedTag(from, descrOfSearchedTag);
        checkFromAndToAndChangePosition(to, from);
    }

    public static void print() {
        StringBuilder descr = new StringBuilder();
        for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
            String substring = HtmlCreator.tagList.get(i).substring(0, HtmlCreator.tagList.get(i).indexOf(">"));
            // substring is: <h2 pos="0" descr="mainheading"  OR <iframe pos="0" descr="video about java " src="https://www.youtube.com/embed/RRubcjpTkks"
            String[] tagListSplitterForPos = HtmlCreator.tagList.get(i).split(" ");
            String posTag = tagListSplitterForPos[1];
            String[] tagListSplitterForDescr = substring.split(" ");
            for (int j = 2; j < tagListSplitterForDescr.length; j++) {
                if (tagListSplitterForDescr[j].contains("href") || tagListSplitterForDescr[j].contains("src")) {
                    break;
                }
                descr.append(tagListSplitterForDescr[j]).append(" ");
            }
            System.out.println(posTag + " " + descr);
            descr = new StringBuilder("");
        }
    }

    public static void createNormalTag(String[] inputSplitter) {
        HtmlCreator tagInfo;
        String content = getContentOfTagWhenAddMethodIsCalled(inputSplitter);
        tagInfo = new HtmlCreator(inputSplitter[1], inputSplitter[2], content);
        Attributes atrs = new Attributes();
        atrs.put("pos", String.valueOf(0));
        atrs.put("descr", tagInfo.getDescription());
        Element el = new Element(Tag.valueOf(tagInfo.getTagType()), "", atrs);
        el.append(content);
        HtmlCreator.tagList.add(el + "<br>");
        System.out.println("You created a tag with these attributes: " + el);
    }

    private static void createImageTag(String[] inputSplitter) {
        HtmlCreator tagInfo;
        String imageDescr = getDescrOfTagWhenAddVideoLinkOrImageMethodIsCalled(inputSplitter);
        tagInfo = new HtmlCreator(inputSplitter[1], imageDescr);
        Attributes atrs = new Attributes();
        atrs.put("pos", String.valueOf(0));
        atrs.put("descr", tagInfo.getDescription());
        atrs.put("src", inputSplitter[inputSplitter.length - 1]);
        String imageFullTag = "<image " + "pos=\"" + 0 + "\" " + "descr=\"" + tagInfo.getDescription() + "\"" + " src=\"" + inputSplitter[inputSplitter.length - 1] + "\">";
        HtmlCreator.tagList.add(imageFullTag + "<br>");
        System.out.println("You created a tag with these attributes: " + imageFullTag);
    }

    private static void createLinkTag(String[] inputSplitter) {
        HtmlCreator tagInfo;
        String linkDescr = getDescrOfTagWhenAddVideoLinkOrImageMethodIsCalled(inputSplitter);
        tagInfo = new HtmlCreator(inputSplitter[1], linkDescr);
        Attributes atrs = new Attributes();
        atrs.put("pos", String.valueOf(0));
        atrs.put("descr", tagInfo.getDescription());
        atrs.put("href", inputSplitter[inputSplitter.length - 1]);
        Element el = new Element(Tag.valueOf("a"), "", atrs);
        el.append(linkDescr);
        HtmlCreator.tagList.add(el + "<br>");
        System.out.println("You created a tag with these attributes: " + el);
    }

    private static void createVideoTag(String[] inputSplitter) {
        HtmlCreator tagInfo;
        String videoDescr = getDescrOfTagWhenAddVideoLinkOrImageMethodIsCalled(inputSplitter);
        tagInfo = new HtmlCreator(inputSplitter[1], videoDescr);
        Attributes atrs = new Attributes();
        atrs.put("pos", String.valueOf(0));
        atrs.put("descr", tagInfo.getDescription());
        atrs.put("src", inputSplitter[inputSplitter.length - 1]);
        Element el = new Element(Tag.valueOf("iframe"), "", atrs);
        HtmlCreator.tagList.add(el + "<br>");
        System.out.println("You created a tag with these attributes: " + el);
        //    Main.createVideoTag("add", "video".....");
    }

    private static String getContentOfTagWhenAddMethodIsCalled(String[] inputSplitter) {
        StringBuilder content = new StringBuilder();
        for (int i = 3; i < inputSplitter.length; i++) {
            content.append(inputSplitter[i]).append(" ");
        }
        return content.toString();
    }

    private static String getDescrOfTagWhenAddVideoLinkOrImageMethodIsCalled(String[] inputSplitter) {
        StringBuilder content = new StringBuilder();
        for (int i = 2; i < inputSplitter.length - 1; i++) {
            if (i < inputSplitter.length - 2) {
                content.append(inputSplitter[i]).append(" ");
            } else {
                content.append(inputSplitter[i]);
            }
        }
        return content.toString();
    }

    static void generateListWithRightPosAndPrintTagList() {
        int posNumber = 0;
        if (HtmlCreator.tagList.size() == 0) {
            System.out.println("Html is empty");
        } else {
            for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
                if (HtmlCreator.tagList.get(i).contains("pos")) {
                    String[] arr = HtmlCreator.tagList.get(i).split("\"");
                    String tagContentInfo = HtmlCreator.tagList.get(i).replace("pos=\"" + arr[1] + "\"", "pos=\"" + posNumber++ + "\"");
                    HtmlCreator.tagList.set(i, tagContentInfo);
                }
            }
        }
    }

    static int getIndexOfSearchedTag(int from, String fromDescr) {
        for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
            if (HtmlCreator.tagList.get(i).contains(fromDescr)) {
                from = i;
                break;
            }
        }
        return from;
    }

    private static void checkFromAndToAndChangePosition(int to, int from) {
        if (from == -1) {
            System.out.println("No such element in collection.");
        } else if (to > HtmlCreator.tagList.size() - 1 || to < 0) {
            System.out.println("Index: " + to + " doesn't exist in this collection.");
        } else {
            System.out.println("The position of tag is changed.");
            changeThePositionOfTags(to, from);
        }
    }

    private static void changeThePositionOfTags(int to, int from) {
        String temp = HtmlCreator.tagList.get(from);
        if (to < from) {
            for (int i = from; i > to; i--) {
                HtmlCreator.tagList.set(i, HtmlCreator.tagList.get(i - 1));
            }
            HtmlCreator.tagList.set(to, temp);
        } else if (from < to) {
            for (int i = from; i < to; i++) {
                HtmlCreator.tagList.set(i, HtmlCreator.tagList.get(i + 1));
            }
            HtmlCreator.tagList.set(to, temp);
        } else {
            System.out.println("The position is the same!");
        }
    }

    private static void createBody() {
        HtmlCreator.doc.body().empty();
        for (int i = 0; i < HtmlCreator.tagList.size(); i++) {
            HtmlCreator.doc.body().append(HtmlCreator.tagList.get(i));
        }
    }

    private static String convertDocToStringAndPrint() {
        System.out.println("Your html file is: ");
        String savedDocToString = String.valueOf(HtmlCreator.doc);
        System.out.println(savedDocToString);
        return savedDocToString;
    }

    private static void writeHtmlToFile(String filePath, String savedDocToString) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(savedDocToString);
        fw.close();
    }


}

