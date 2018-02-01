/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionhtml;

import Model.CveObject;
import Model.DependencyObject;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String OWN_URL_PATH = "C:\\Users\\Eugene Tan\\Desktop\\Dependency Scanner Result\\free-python-games-master\\free-python-games-master\\dependency-check-report.html";

    /**
     * Project name variable
     */
    private static String projectName;
    
    /**
     * ArrayList to store multiple cveObject
     */
    private static ArrayList<CveObject> cveObject;
    
    /**
     * ArrayList to store multiple dependencyObject
     */
    private static ArrayList<DependencyObject> dependencyObject;
    
    /**
     * Constant variable for project name extraction
     */
    private static final String PROJECTHEADER = "Project";
    
    /**
     * Use regex patterns when extracting cve number.
     */
    private static final Pattern CVENUMBER = Pattern.compile("(CVE-\\d{4}-\\d{4}) "); 
    
    /**
     * Use regex patterns when extracting cvss score number.
     */
    private static final Pattern SCORE = Pattern.compile(" (\\d.\\d) "); 
    
    /**
     * Use regex patterns when extracting severity level number.
     */
    private static final Pattern SEVERITY = Pattern.compile(" (Low|Medium|High) ");
    
    /**
     * Use regex patterns when extracting file path
     */
    private static final Pattern FILE_PATH = Pattern.compile("([a-zA-Z]:(\\\\([a-zA-Z0-9_-]|\\s)+)+([a-zA-Z.]+))");
    
    /**
     * Use regex patterns when extract MD5 string
     */
    private static final Pattern MD5 = Pattern.compile("(MD5:\\s[a-z0-9]+)");
    
    /**
     * Use regex patterns when extract SHA1 string
     */
    private static final Pattern SHA1 = Pattern.compile("(SHA1:\\s[a-z0-9]+)");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Document d = DOM(OWN_URL_PATH);
        projectName = projectName(d, PROJECTHEADER);
        ArrayList<String> s = scanInformation(d);
        cveObject = cveDetails(d);
        dependencyObject = dependencyDetail(d);
        
        System.out.println("Project: " + projectName);
        System.out.println("Scan Information: " + s);
        System.out.println("CveNum: " + cveObject.get(0).getCveNumber());
        System.out.println("CveSeverity: " + cveObject.get(0).getCveSeverityLevel());
        System.out.println("CveScore: " + cveObject.get(0).getCvssScore());
        System.out.println("CveDesc: " + cveObject.get(0).getCveDescription());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getDependencyName());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getCpe());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getCoordinates());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getSeverityLevel());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getCveCount());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getCpeConfidence());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getEvidenceCount());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getDescription());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getFilePath());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getMd5());
        System.out.println("dependencyObject: " + dependencyObject.get(0).getSha1());
//        testing();
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
     * @return specific string from a full string
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
     * Method to extract the project name
     * @param d the document
     * @param projectHeader the project header to be search in a full text
     * @return the project name
     */
    public static String projectName(Document d, String projectHeader){
        String projName = "";
        String extractedData;
        
        Elements elements = d.select("div.wrapper");
        for(Element element: elements){
            extractedData = element.select("h2").first().text();
            projName = Trimmer(extractedData, projectHeader, ";");
        }
        return projName;
    }
    
    /**
     * Method to extract the scan information
     * @param d the DOM document
     * @return Array of scan information
     */
    public static ArrayList<String> scanInformation(Document d){
        ArrayList<String> extractedArrayData = new ArrayList<>();
        String extractedData;
        Elements elements = d.select("div.wrapper");
        
        for(int i = 0; i<6; i++){
            extractedData = elements.select("div ul.indent li").get(i).text();
            extractedArrayData.add(extractedData);
//            extractedArrayData.add(Trimmer(extractedData, "", ";"));
        }
        return extractedArrayData;
    }
    
    /**
     * Method to extracting desired each cve details into an CveObject java class
     * @param d the DOM document
     * @return ArrayList of CveObject
     */
    public static ArrayList<CveObject> cveDetails(Document d) {
        ArrayList<CveObject> cve = new ArrayList<>();
        Matcher match;
        String cveNumber;
        String severity;
        String cvssScore;
        String description;
        String number ="";
        String sev = "";
        double score = 0;

        Elements elements = d.select("div#content5 p");

        for (int i = 1; i < elements.size(); i += 6) {
            cveNumber = elements.eq(i - 1).text();
            match = CVENUMBER.matcher(cveNumber);
            if (match.find()) {
                number = match.group(1);
            }

            severity = elements.eq(i).text();
            match = SEVERITY.matcher(severity);
            if (match.find()) {
                sev = match.group(1);
            }

            cvssScore = elements.eq(i).text();
            match = SCORE.matcher(cvssScore);
            if (match.find()) {
                score = Double.parseDouble(match.group(1));
            }
            
            description = elements.eq(i + 1).text();
            
            cve.add(new CveObject(number, score, sev, description));
        }
        return cve;
    }
    
    /**
     * Method to extracting of dependency object into DependencyObject java class
     * @param d the DOM document
     * @return the Dependency Object
     */
    public static ArrayList<DependencyObject> dependencyDetail(Document d){
        ArrayList<DependencyObject> dependency = new ArrayList<>();;
        String dependencyName;
        String cpe;
        String coordinate;
        String highestSeverity;
        String cpeConfidence;
        String pathName;
        String description;
        
        int cveCount;
        int evidenceCount;
        int counter = 0;
  
        Elements elements = d.select("table#summaryTable tbody tr.vulnerable td");
        
        for (int i = 0; i < elements.size(); i+=7) {
            dependencyName = elements.select("a").get(i).text();
            cpe = elements.select("a").get(i+1).text();
            coordinate = elements.get(i+2).text();
            highestSeverity = elements.get(i+3).text();
            cveCount = Integer.parseInt(elements.get(i+4).text());
            cpeConfidence = elements.get(i+5).text();
            evidenceCount = Integer.parseInt(elements.get(i+6).text());
            pathName = filePath(d);
            description = projectDescription(d);
      
            dependency.add(new DependencyObject(dependencyName, cpe, coordinate, highestSeverity, 
                    cveCount, cpeConfidence, evidenceCount, description, pathName));
            hashString(d, dependency.get(counter));
            counter++;
        }
        return dependency;
    }
    
    /**
     * Method to extract project description from OWASP report
     * @param d
     * @return the project description
     */
    public static String projectDescription(Document d){
        Elements elements = d.select("div.subsectioncontent p");
        return elements.get(1).text();
    } 
    
    /**
     * Method to extract project file path from OWASP report
     * @param d the DOM document
     * @return the project file path
     */
    public static String filePath(Document d){
        Elements elements = d.select("div.subsectioncontent");
        String path;
        String extractedData;
        
        extractedData = elements.get(0).text();
        Matcher match = FILE_PATH.matcher(extractedData);
        if (match.find()) {
            path = match.group(1);
        }
        else{
            path = "";
        }
        return path;
    } 

    /**
     * Extract from OWASP report and set md5 and sha1 string into class DependencyObject object
     * @param d the DOM document
     * @param object the specific DependencyObject object
     */
    public static void hashString(Document d, DependencyObject object){
        
        Elements elements = d.select("div.subsectioncontent");
        String md5;
        String sha1;
        String extractedData;
        Matcher match;
        
        extractedData = elements.get(0).text();
        match = MD5.matcher(extractedData);
        if (match.find()) {
            md5 = match.group(1);
            object.setMd5(md5);
        }
        match = SHA1.matcher(extractedData);
        if (match.find()){
            sha1 = match.group(1);
            object.setSha1(sha1);
        }
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
    
//    public static void testing(){
//        String s = "File Path: C:\\Users\\Eugene Tan\\Desktop\\Dependency Scanner Result\\free-python-games-master\\free-python-games-master\\free-python-games-master\\tests\\__init__.py MD5: d41d8cd98f00b204e9800998ecf8427e SHA1: da39a3ee5e6b4b0d3255bfef95601890afd80709 Evidence Type Source Name Value Confidence Product __init__.py PackageName tests Highest Identifiers None\n" +
//"ccc: d41d8cd98f00b204e9800998ecf8427e SHA1: da39a3ee5e6b4b0d3255bfef95601890afd80709 Evidence";
//        Pattern test = Pattern.compile("(MD5:\\s[a-z0-9]+)");
//        String x;
//
//        Matcher match = test.matcher(s);
//        if (match.find()) {
//            x = match.group(1);
//            System.out.println("The String is: " + x);
//        }
//        else{
//            System.out.println("None!");
//        }
//
//    }
    
}
