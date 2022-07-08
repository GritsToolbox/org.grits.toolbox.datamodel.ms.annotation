package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements of the Method class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMMethod
{
	method_annotationType("Annotation Type", "The type of annotation for the MS Experiment."),
	method_monoisotopic("Use Monoisotopic Mass", "Tells whether to use monoisotopic mass (if true) or average (if false)."),
	method_accuracy("Mass Accuracy", "The specified mass accuracy for assigning annotation objects to MS data."),
	method_accuracyPpm("Use PPM Accuracy", "Tells whether to use parts-per-million (ppm) mass accuracy (if true) or mass delta (in daltons)."),
	method_shift("Mass Shift", "Global shift to add to observed precursor mass in order to account for MS instrument calibration errors."),
	method_maxIonCount("Max Ion Count", "The maximum number of total adducts for a feature."),
	method_maxIonExchangeCount("Max Ion Exchange Count", "The maximum number of total neutral exchanges for a feature.");

    private String sLabel;
    private String sDescription;

    private DMMethod( String sLabel, String sDescription ) {
        this.sLabel = sLabel;
        this.sDescription = sDescription;
    }
    
    public String getDescription() {
 		return sDescription;
 	}

    public String getLabel() 
    {  
        return this.sLabel;  
    }		
    
    public static DMMethod lookUp( String _sKey ) {
     	if ( method_annotationType.name().equals(_sKey ) )
    		return method_annotationType;
    	else if ( method_monoisotopic.name().equals(_sKey) ) 
    		return method_monoisotopic;
    	else if ( method_accuracy.name().equals(_sKey) ) 
    		return method_accuracy;
    	else if ( method_accuracyPpm.name().equals(_sKey) ) 
    		return method_accuracyPpm;
    	else if ( method_shift.name().equals(_sKey) ) 
    		return method_shift;
    	else if ( method_maxIonCount.name().equals(_sKey) ) 
    		return method_maxIonCount;
    	else if ( method_maxIonExchangeCount.name().equals(_sKey) ) 
    		return method_maxIonExchangeCount;
    	
    	return null;
    } 

}
