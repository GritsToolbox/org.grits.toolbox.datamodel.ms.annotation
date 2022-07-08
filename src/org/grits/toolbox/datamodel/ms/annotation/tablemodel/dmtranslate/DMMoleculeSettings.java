package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the MoleculeSettings class in the GRITS object model. Note that MoleculeSettings extends Molecule.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMMoleculeSettings // extends Molecule
{
	molecule_settings_label("Molecule Label", "The abbreviated name of the molecule."),
	molecule_settings_mass("Molecule Mass", "The mass of the molecule."),
	molecule_settings_name("Molecule Name", "The full name of the molecule."),
	molecule_settings_count("Molecule Count Setting", "");
	
    private String sLabel;
    private String sDescription;

    private DMMoleculeSettings( String sLabel, String sDescription ) {
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

    public static DMMoleculeSettings lookUp( String _sKey ) {
    	if ( molecule_settings_label.name().equals(_sKey ) )
    		return molecule_settings_label;
    	else if ( molecule_settings_mass.name().equals(_sKey) ) 
    		return molecule_settings_mass;
    	else if ( molecule_settings_name.name().equals(_sKey) ) 
    		return molecule_settings_name;
    	else if ( molecule_settings_count.name().equals(_sKey) ) 
    		return molecule_settings_count;
    	
    	return null;
    } 
    
}
