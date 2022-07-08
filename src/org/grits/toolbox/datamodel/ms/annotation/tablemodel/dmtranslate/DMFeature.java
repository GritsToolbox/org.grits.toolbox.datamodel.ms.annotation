package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements of the Feature class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMFeature
{
	feature_id("Feature Id", "The auto-assigned ID of the annotation object assigned to a scan."),
	feature_type("Feature Type", "The system-assigned type of feature assigned."),
	feature_sequence("Feature Sequence", "The sequence of the annotation object associated with this feature."),
	feature_mz("Feature m/z", "The theoretical m/z of the feature."),
	feature_deviation("Feature Mass Error", "The delta between the feature and observed m/zs in parts-per-million (ppm)"),
	feature_charge("Feature Charge", "The observed charge of the feature (could differ from scan if annotation considers other charge states)."),
	feature_annotation_id("Annotation Id", "The ID of the annotation object associated with this features."),
	feature_precursor_id("Precursor Id", "The ID of the peak assigned with this feature."),
	feature_fragmentType("Fragmentation Type", "Name of the fragmentation type (ions) that produced this feature, if applicable."), 
	feature_ions("Adducts", "Text representation of the adducts (with quantity) that produced this feature."),
	feature_neutralLosses("Neutral Losses", "Text representation of the neutral losses (with quantity) that produced this feature, if any."),
	feature_exchanges("Ion Exchanges", "Text representation of the ion exchanges (with quantity) that produced this feature, if any.");
	
    private String sLabel;
    private String sDescription;

    private DMFeature( String sLabel, String sDescription ) {
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
    
    public static DMFeature lookUp( String _sKey ) {
    	if ( feature_id.name().equals(_sKey ) )
    		return feature_id;
    	else if ( feature_type.name().equals(_sKey) ) 
    		return feature_type;
    	else if ( feature_sequence.name().equals(_sKey) ) 
    		return feature_sequence;
    	else if ( feature_mz.name().equals(_sKey) ) 
    		return feature_mz;
    	else if ( feature_deviation.name().equals(_sKey) ) 
    		return feature_deviation;
    	else if ( feature_charge.name().equals(_sKey) ) 
    		return feature_charge;
    	else if ( feature_annotation_id.name().equals(_sKey) ) 
    		return feature_annotation_id;
    	else if ( feature_precursor_id.name().equals(_sKey) ) 
    		return feature_precursor_id;
    	else if ( feature_ions.name().equals(_sKey) ) 
    		return feature_ions;
    	else if ( feature_fragmentType.name().equals(_sKey) ) 
    		return feature_fragmentType;
//    	else if ( feature_adduct.name().equals(_sKey) ) 
//    		return feature_adduct;
    	else if ( feature_neutralLosses.name().equals(_sKey) ) 
    		return feature_neutralLosses;
    	else if ( feature_exchanges.name().equals(_sKey) ) 
    		return feature_exchanges;
    	
    	return null;
    } 
    
}
