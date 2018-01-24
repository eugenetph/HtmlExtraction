/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionhtml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Eugene
 */
public class ExtractionHtml {

    /**
     * Constant path for html file
     */
    private static final String OWN_URL_PATH = "C:\\Users\\Eugene\\Desktop\\Dependency Scanner Result\\free-python-games-master\\free-python-games-master\\dependency-check-report.html";

    /**
     * Project name variable
     */
    private static String projectName;
    
    /**
     * Used when compiling file scanning regex patterns.
     */
    
    private static final String PROJECTHEADER = "Project";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Document d = DOM(OWN_URL_PATH);
        projectName = projectName(d, PROJECTHEADER);
        ArrayList<String> s = scanInformation(d);
        
        System.out.println("Project: " + projectName);
        System.out.println("Scan Information: " + s);
    }
    
    /**
     * A method for input html file path
     * @param path the file path
     * @return
     * @throws IOException 
     */
    public static Document DOM(String path) throws IOException{
        File input = new File(path);
        Document d = Jsoup.parse(input, "UTF-8");
        return d;
    }
    /**
     * Method to trimmer your desire sentence/text from a full string
     * @param fullPart the full string
     * @param projectHeader the project header to be search in a full text
     * @param separator the separator in the full string
     * @return 
     */
    public static String Trimmer(String fullPart, String projectHeader, String separator) {
        String partHeader = fullPart;
        for (String content : partHeader.split(separator)) {
            if (content.trim().startsWith(projectHeader)) {
                return content.substring(
                        content.indexOf(":") + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    /**
     * Extract the project name
     * @param d the document
     * @param projectHeader the project header to be search in a full text
     * @return 
     */
    public static String projectName(Document d, String projectHeader){
        String projName = "";
        String extractedData = "";
        
        Elements elements = d.select("div.wrapper");
        for(Element element: elements){
            extractedData = element.select("h2").first().text();
            projName = Trimmer(extractedData, projectHeader, ";");
        }
        return projName;
    }
    
    /**
     * Extract the scan information
     * @param d the DOM document
     * @return 
     */
    public static ArrayList<String> scanInformation(Document d){
        ArrayList<String> extractedArrayData = new ArrayList<String>();
        String extractedData = "";
        Elements elements = d.select("div.wrapper");
        
        for(int i = 0; i<6; i++){
            extractedData = elements.select("div ul.indent li").get(i).text();
            extractedArrayData.add(extractedData);
//            extractedArrayData.add(Trimmer(extractedData, "", ";"));
        }
        return extractedArrayData;
    }
    
    /**
     * For online url
     * @throws IOException 
     */
    public static void example1() throws IOException {
        Document d = Jsoup.connect("https://www.wikihow.com/wikiHowTo?search=build+a+desktop").timeout(6000).get();         //The url for extracting the DOM you want
        Elements element = d.select("div#searchresults_list");                                                              //Specifying the div container id(#) or class(.)
        for(Element ele: element.select("div.result")){                                                                     //Get all DOM with a specific target of a class name(You can get the substring of a class or id name)
            String img_url = ele.select("div.result_thumb img").attr("src");                                                //Get the url with a specific target of a class name with attr(use only if it is not class or id)
            System.out.println(img_url);                                                                                    //Normal print line
            
            String title = ele.select("div.result_data a").text();                                                          //Get the text of div.result_data a
            System.out.println(title);                                                                                      //Normal print line
        }
        System.out.println("\n\n\n");
        
        Document d2 = Jsoup.connect("https://www.wikihow.com/Build-a-Personal-Desktop-Computer").timeout(6000).get();
        Elements body = d2.select("div#bodycontents");
        for(Element step: body){
            String method = step.select("div.steps h2 span#Steps").text();
            System.out.println(method);
        }
    }
    
    /**
     * For own url file in directory
     * @param path the file path
     * @throws IOException 
     */
    public static void example2(String path) throws IOException {
        File input = new File(path);
        Document d = Jsoup.parse(input, "UTF-8");
        Elements element1 = d.select("div.wrapper");
        for(Element ele: element1){
            String x = ele.select("p.disclaimer").text();
            System.out.println(x);
        }
        System.out.println("\n\n\n");
        
        Elements element2 = d.select("div.subsectioncontent");
        String desc = element2.select("p").text();
        System.out.println(desc);
    }
    
}
