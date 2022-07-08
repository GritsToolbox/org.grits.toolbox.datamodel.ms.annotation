package org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate;

/**
 * Abstraction for the Annotation class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMAnnotation
{
	annotation_id("Annotation Id", "The auto-assigned ID of the object in the annotation database."),
	annotation_string_id("Annotation Name", "The user-friendly name of the object in the annotation database."),
	annotation_type("Annotation Type", "The system-assigned type of annotation used."),
	annotation_sequence("Sequence", "If applicable, the sequence representation of the object in the database."),
	annotation_num_candidates("# Candidate Annotations", "The number of candidates in the database that are assigned to the scan using the search parameters."),
	annotation_ratio("Ratio", "Ratio of mixtures, if known");

    private String sLabel;
    private String sDescription;

    private DMAnnotation( String sLabel, String sDescription ) {
        this.sLabel = sLabel;
        this.sDescription = sDescription;
    }

    public String getLabel() 
    {  
        return this.sLabel;  
    }	
 
    public String getDescription() {
  		return sDescription;
  	}
         
    public static DMAnnotation lookUp( String _sKey ) {
    	if ( annotation_id.name().equals(_sKey ) )
    		return annotation_id;
    	else if ( annotation_id.name().equals(_sKey) ) 
    		return annotation_id;
    	else if ( annotation_type.name().equals(_sKey) ) 
    		return annotation_type;
    	else if ( annotation_sequence.name().equals(_sKey) )
    		return annotation_sequence;
    	else if ( annotation_num_candidates.name().equals(_sKey) )
    		return annotation_num_candidates;
    	
    	return null;
    } 
 }
