/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class CveObject {

    /**
     * store String of CVE number
     */
    private String cveNumber;
    
    /**
     * store String of CVE description
     */
    private String cveDescription;
    
    /**
     * store Integer of CVE severity level
     */
    private String cveSeverityLevel; 
    
    /**
     * store Double of CVSS score
     */
    private double cvssScore;
    
    /**
     * empty constructor
     */
    public CveObject(String cveNumber, double cvssScore, String cveSeverityLevel, String cveDescription){
        this.cveNumber = cveNumber;
        this.cveDescription = cveDescription;
        this.cveSeverityLevel = cveSeverityLevel;
        this.cvssScore = cvssScore;
    }

    /**
     * @return the cveNumber
     */
    public String getCveNumber() {
        return cveNumber;
    }

    /**
     * @return the cveDescription
     */
    public String getCveDescription() {
        return cveDescription;
    }

    /**
     * @return the cveSeverityLevel
     */
    public String getCveSeverityLevel() {
        return cveSeverityLevel;
    }

    /**
     * @return the cvssScore
     */
    public double getCvssScore() {
        return cvssScore;
    }
}
