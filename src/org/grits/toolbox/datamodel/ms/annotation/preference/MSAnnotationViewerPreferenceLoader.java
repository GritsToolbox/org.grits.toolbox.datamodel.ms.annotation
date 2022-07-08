package org.grits.toolbox.datamodel.ms.annotation.preference;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;

import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.preference.TableViewerPreference;

public class MSAnnotationViewerPreferenceLoader {
	private static final Logger logger = Logger.getLogger(MSAnnotationViewerPreferenceLoader.class);

	public static MSAnnotationViewerPreference getTableViewerPreference(int _iMSLevel, FillTypes fillType )  {
		MSAnnotationViewerPreference preferences = null;
		try {
			PreferenceEntity preferenceEntity = MSAnnotationViewerPreference.getPreferenceEntity(_iMSLevel, fillType); 
			if( preferenceEntity == null ) { // previous version
				preferences = MSAnnotationViewerPreferencePreVersion.getTableViewerPreferencesPreVersioning(_iMSLevel, fillType);
				
				if( preferences != null ) {
					MSAnnotationViewerPreferencePreVersion.removeElements(_iMSLevel, fillType);
				}
				if( preferences.getColumnSettings() != null ) {				
					preferences.writePreference();
				}
			} else {
				preferences = (MSAnnotationViewerPreference) TableViewerPreference.getTableViewerPreference(preferenceEntity, MSAnnotationViewerPreference.class);
			}
		} catch (UnsupportedVersionException ex) {
			logger.error(ex.getMessage(), ex);
			
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
		if( preferences == null ) { // well, either no preferences yet or some error. initialize to defaults and return
			preferences = new MSAnnotationViewerPreference();
			preferences.setFillType(fillType);
			preferences.setMSLevel(_iMSLevel);
			preferences.setColumnSettings("");
		}
		return preferences;
	}
	
}
