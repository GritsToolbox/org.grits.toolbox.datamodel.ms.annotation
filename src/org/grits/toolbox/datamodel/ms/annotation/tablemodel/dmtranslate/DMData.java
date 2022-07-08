package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements in the Data class of the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMData
{
	data_version("Version", "Version of the software tool perform annotation assignment."),
	data_spectrumType("Spectrum Type", ""); 
	
    private String sLabel;
    
    private String sDescription;

    public String getDescription() {
 		return sDescription;
 	}

    public String getLabel() 
    {  
        return this.sLabel;  
    }		
        
    private DMData( String sLabel, String sDescription ) {
        this.sLabel = sLabel;
        this.sDescription = sDescription;
    }
    
    public static DMData lookUp( String _sKey ) {
    	if ( data_version.name().equals(_sKey ) )
    		return data_version;
    	else if ( data_spectrumType.name().equals(_sKey) ) 
    		return data_spectrumType;
    	
    	return null;
    } 
    
}
