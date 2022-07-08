package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the ModificationSettings class in the GRITS object model. Note that ModificationSettings extends Molecule.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMModificationSettings // extends Molecule
{
	modification_settings_label("Molecule Label", "The abbreviated name of the molecule."),
	modification_settings_mass("Molecule Mass", "The mass of the molecule."),
	modification_settings_name("Molecule Name", "The full name of the molecule."),
	modification_settings_modificationType("Modification Type", "The type of post-translational modification.");

	private String sLabel;
    private String sDescription;

    private DMModificationSettings( String sLabel, String sDescription ) {
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
    
    public static DMModificationSettings lookUp( String _sKey ) {
    	if ( modification_settings_modificationType.name().equals(_sKey) ) 
    		return modification_settings_modificationType;
     	
    	return null;
    } 
    
}
