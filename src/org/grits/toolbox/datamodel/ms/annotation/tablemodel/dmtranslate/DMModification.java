package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the Modification class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMModification
{
	modification_modificationType("Modification Type", "The type of post-translational modification."),
	modification_modificationPosition("Modification Position", "The position of the post-translational modification.");
	
    private String sLabel;
    private String sDescription;

    private DMModification( String sLabel, String sDescription ) {
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
	
    public static DMModification lookUp( String _sKey ) {
    	if ( modification_modificationType.name().equals(_sKey ) )
    		return modification_modificationType;
    	else if ( modification_modificationPosition.name().equals(_sKey) ) 
    		return modification_modificationPosition;
    	
    	return null;
    } 
    
}
