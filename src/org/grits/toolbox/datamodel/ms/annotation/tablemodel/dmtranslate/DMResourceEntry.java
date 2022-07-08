package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the ResourceEntry (source of annotation information) class in the GRITS object model. 
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMResourceEntry
{
	resource_entry_databaseName("Database Resource Name", "The name of the database specified for the annotation of an MS experiment."),
	resource_entry_databaseId("Database Resource Id", "The ID of the database specified for the annotation of an MS experiment.");
	
    private String sLabel;
    private String sDescription;

    private DMResourceEntry( String sLabel, String sDescription ) {
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
    
    public static DMResourceEntry lookUp( String _sKey ) {
    	if ( resource_entry_databaseName.name().equals(_sKey ) )
    		return resource_entry_databaseName;
    	else if ( resource_entry_databaseId.name().equals(_sKey) ) 
    		return resource_entry_databaseId;
    	
    	return null;
    } 
    
}
