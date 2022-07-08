package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the Molecule class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMMolecule
{
	molecule_label("Molecule Label", "The abbreviated name of the molecule."),
	molecule_mass("Molecule Mass", "The mass of the molecule."),
	molecule_name("Molecule Name", "The full name of the molecule.");
	
    private String sLabel;
    private String sDescription;

    private DMMolecule( String sLabel, String sDescription ) {
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
    
    public static DMMolecule lookUp( String _sKey ) {
    	if ( molecule_label.name().equals(_sKey ) )
    		return molecule_label;
    	else if ( molecule_mass.name().equals(_sKey) ) 
    		return molecule_mass;
    	else if ( molecule_name.name().equals(_sKey) ) 
    		return molecule_name;
    	
    	return null;
    } 
    
}
