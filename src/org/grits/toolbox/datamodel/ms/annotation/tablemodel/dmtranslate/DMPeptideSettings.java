package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements of the PeptideFeature class in the GRITS object model. 
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMPeptideSettings
{
	peptide_settings_enzyme("Peptide Enzyme Name", "The specified enzyme for peptide annotation an MS experiment."),
	peptide_settings_numberMissCleavages("Peptide # Missed Cleavages", "The maximum number of missed cleavages for peptide annotation of an MS experiment.");

    private String sLabel;
    private String sDescription;

    private DMPeptideSettings( String sLabel, String sDescription ) {
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
    
    public static DMPeptideSettings lookUp( String _sKey ) {
    	if ( peptide_settings_enzyme.name().equals(_sKey ) )
    		return peptide_settings_enzyme;
    	else if ( peptide_settings_numberMissCleavages.name().equals(_sKey) ) 
    		return peptide_settings_numberMissCleavages;
    	
    	return null;
    } 
    
}
