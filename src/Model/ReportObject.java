/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Eugene Tan
 */
public class ReportObject {
    
    /**
     * A variable to store project name
     */
    private String projectName;
    
    /**
     * A variable to store scanned information
     */
    private ArrayList<String> scannedInformation;
    
    /**
     * A variable to store dependency object
     */
    private DependencyObject dependencyObject;
    
    /**
     * A variable to store cve object
     */
    private CveObject cveObject;
    
    /**
     * Constructor used for GSON
     */
    public ReportObject(String projectName, ArrayList<String> scannedInformation, DependencyObject dependencyObject, CveObject cveObject){
        this.projectName = projectName;
        this.scannedInformation = scannedInformation;
        this.dependencyObject = dependencyObject;
        this.cveObject = cveObject;
    }
}
