package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the PeptideFeature class in the GRITS object model. Note that PeptideFeature extends Feature.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMPeptideFeature // extends _Feature
{
	peptide_feature_id("Peptide Feature Id", "The auto-assigned ID of the annotation object assigned to a scan."),
	peptide_feature_type("Peptide Feature Type", "The system-assigned type of feature assigned."),
	peptide_feature_sequence("Peptide Sequence", "The sequence of the annotation object associated with this feature."),
	peptide_feature_mz("Peptide m/z", "The theoretical m/z of the feature."),
	peptide_feature_deviation("Peptide Mass Error", "The delta between the observed and feature m/zs in parts-per-million (ppm)."),
	peptide_feature_charge("Peptide Charge", "The observed charge of the feature (could differ from scan if annotation considers other charge states)."),
	peptide_feature_numberMissCleavage("Peptide # Missed Cleavages", "The specified enzyme for peptide annotation an MS experiment.");
	
    private String sLabel;
    private String sDescription;

    private DMPeptideFeature( String sLabel, String sDescription ) {
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
    
    public static DMPeptideFeature lookUp( String _sKey ) {
    	if ( peptide_feature_id.name().equals(_sKey ) )
    		return peptide_feature_id;
    	else if ( peptide_feature_type.name().equals(_sKey) ) 
    		return peptide_feature_type;
    	else if ( peptide_feature_sequence.name().equals(_sKey) ) 
    		return peptide_feature_sequence;
    	else if ( peptide_feature_mz.name().equals(_sKey) ) 
    		return peptide_feature_mz;
    	else if ( peptide_feature_deviation.name().equals(_sKey) ) 
    		return peptide_feature_deviation;
    	else if ( peptide_feature_charge.name().equals(_sKey) ) 
    		return peptide_feature_charge;
    	else if ( peptide_feature_numberMissCleavage.name().equals(_sKey) ) 
    		return peptide_feature_numberMissCleavage;
    	
    	return null;
    } 
    
}
