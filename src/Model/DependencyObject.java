/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eugene Tan
 */
public class DependencyObject {
    
    /**
     * The name of the dependency
     */
    private String dependencyName;
    
    /**
     * The cpe type
     */
    private String cpe;
    
    /**
     * The coordinate
     */
    private String coordinates;
    
    /**
     * The highest severity level
     */
    private String severityLevel;
    
    /**
     * The CVE count
     */
    private int cveCount;
    
    /**
     * The CPE confidence level
     */
    private String cpeConfidence;
    
    /**
     * The evidence count
     */
    private int evidenceCount;
    
    /**
     * The description of the dependency project
     */
    private String description;
    
    /**
     * The file path of the dependency project
     */
    private String filePath;
    
    /**
     * The MD5 string of the dependency project
     */
    private String md5;
    
    /**
     * The SHA1 strong of the dependency project
     */
    private String sha1;
    
    /**
     * The dependency constructor
     */
    public DependencyObject (String dependencyName, String cpe, String coordinates, 
            String severityLevel, int cveCount, String cpeConfidence, int evidenceCount) {
        this.dependencyName = dependencyName;
        this.cpe = cpe;
        this.coordinates = coordinates;
        this.severityLevel = severityLevel;
        this.cveCount = cveCount;
        this.cpeConfidence = cpeConfidence;
        this.evidenceCount = evidenceCount;
    }

    /**
     * @return the dependencyName
     */
    public String getDependencyName() {
        return dependencyName;
    }

    /**
     * @return the cpe
     */
    public String getCpe() {
        return cpe;
    }

    /**
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @return the severityLevel
     */
    public String getSeverityLevel() {
        return severityLevel;
    }

    /**
     * @return the cveCount
     */
    public int getCveCount() {
        return cveCount;
    }

    /**
     * @return the cpeConfidence
     */
    public String getCpeConfidence() {
        return cpeConfidence;
    }

    /**
     * @return the evidenceCount
     */
    public int getEvidenceCount() {
        return evidenceCount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @return the sha1
     */
    public String getSha1() {
        return sha1;
    }
}
