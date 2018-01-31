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
}
