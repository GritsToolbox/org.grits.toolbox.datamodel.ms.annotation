package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the IonAdduct class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMIonAdduct
{
	ion_adduct_count("Ion Adduct Count", "The count of a particular adduct in this feature.");

    private String sLabel;
    private String sDescription;

    private DMIonAdduct( String sLabel, String sDescription ) {
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
    public static DMIonAdduct lookUp( String _sKey ) {
    	if ( ion_adduct_count.name().equals(_sKey ) )
    		return ion_adduct_count;
    	
    	return null;
    } 

}
