package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the Fragment class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMFragment
{
	fragment_type("Fragment Type", "Fragmentation type of a particular ion."),
	fragment_number("Fragment #", "Fragmentation number of a particular ion.");
	
    private String sLabel;
    private String sDescription;

    private DMFragment( String sLabel, String sDescription ) {
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
        
    public static DMFragment lookUp( String _sKey ) {
    	if ( fragment_type.name().equals(_sKey ) )
    		return fragment_type;
    	else if ( fragment_number.name().equals(_sKey) ) 
    		return fragment_number;
    	
    	return null;
    } 
    
}
