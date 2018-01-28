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
     * store String array of CVE number
     */
    private ArrayList<String> cveNumber = new ArrayList<>();
    
    /**
     * store String array of CVE description
     */
    private ArrayList<String> cveDescription = new ArrayList<>();;
    
    /**
     * store Integer array of CVE severity level
     */
    private ArrayList<String> cveSeverityLevel = new ArrayList<>();; 
    
    /**
     * store Double array of CVSS score
     */
    private ArrayList<Double> cvssScore = new ArrayList<>();;
    
    /**
     * empty constructor
     */
    public CveObject(){}

    /**
     * @return the cveNumber
     */
    public ArrayList<String> getCveNumber() {
        return cveNumber;
    }

    /**
     * @param cveNumber the cveNumber to set
     */
    public void setCveNumber(String cveNumber) {
        this.cveNumber.add(cveNumber);
    }

    /**
     * @return the cveDescription
     */
    public ArrayList<String> getCveDescription() {
        return cveDescription;
    }

    /**
     * @param cveDescription the cveDescription to set
     */
    public void setCveDescription(String cveDescription) {
        this.cveDescription.add(cveDescription);
    }

    /**
     * @return the cveSeverityLevel
     */
    public ArrayList<String> getCveSeverityLevel() {
        return cveSeverityLevel;
    }

    /**
     * @param cveSeverityLevel the cveSeverityLevel to set
     */
    public void setCveSeverityLevel(String cveSeverityLevel) {
        this.cveSeverityLevel.add(cveSeverityLevel);
    }

    /**
     * @return the cvssScore
     */
    public ArrayList<Double> getCvssScore() {
        return cvssScore;
    }

    /**
     * @param cvssScore the cvssScore to set
     */
    public void setCvssScore(Double cvssScore) {
        this.cvssScore.add(cvssScore);
    }
    
}
