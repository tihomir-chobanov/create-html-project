import org.jsoup.Jsoup;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

            /*
            input examples:
            load C:\\Users\\Tihomir\\Desktop\\index.html
            add p id345 some text here
            add h2 mainheading HTML PAGE CREATOR
            add div id123 <p> this is paragraph </p>
            add video video about java https://www.youtube.com/embed/RRubcjpTkks
            add link visit W3Schools https://www.w3schools.com
            add image this is image dracaena-cinnabari.jpg
             */

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String firstWordOfInput;
        String filePath = "";

        while (true) {
            String input = getUserInput(scanner);
            String[] inputSplitter = splitTheInput(input);
            firstWordOfInput = inputSplitter[0];
            switch (firstWordOfInput) {
                case "add":
                    HtmlCreator.add(inputSplitter);
                    HtmlCreator.generateListWithRightPosAndPrintTagList();
                    break;
                case "remove":
                    HtmlCreator.remove(inputSplitter);
                    break;
                case "print":
                    HtmlCreator.generateListWithRightPosAndPrintTagList();
                    HtmlCreator.print();
                    break;
                case "moveTo":
                    HtmlCreator.move(inputSplitter);
                    HtmlCreator.generateListWithRightPosAndPrintTagList();
                    break;
                case "load":
                    try {
                        filePath = inputSplitter[1];
                        String myHtmlFile = Files.readString(Path.of(filePath));
                        System.out.println("File loaded successfully.");
                        HtmlCreator.doc = Jsoup.parse(myHtmlFile);
                        System.out.println("HTML file is: \n" + HtmlCreator.doc);
                        String[] splittedBodyOfLoadedFile = String.valueOf(HtmlCreator.doc.body().children()).split("<br>");
                        HtmlCreator.tagList.addAll(Arrays.asList(splittedBodyOfLoadedFile));
                    } catch (IOException e) {
                        filePath = inputSplitter[1];
                        File myObj = new File(filePath);
                        myObj.createNewFile();
                        System.out.println("File created: " + myObj.getName());
                        HtmlCreator.doc = Jsoup.parse(myObj, "UTF-8", "");
                    }
                    break;
                case "save":
                    HtmlCreator.generateListWithRightPosAndPrintTagList();
                    HtmlCreator.save(filePath);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Please enter a valid option!");
            }
        }
    }

    private static String[] splitTheInput(String input) {
        return input.split(" ");
    }

    private static String getUserInput(Scanner scanner) {
        System.out.println("\nPlease add, remove, print, moveTo, load, save or exit: ");
        return scanner.nextLine();
    }













}
