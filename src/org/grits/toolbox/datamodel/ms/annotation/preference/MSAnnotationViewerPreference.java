package org.grits.toolbox.datamodel.ms.annotation.preference;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;
import org.grits.toolbox.core.preference.share.PreferenceReader;
import org.grits.toolbox.core.preference.share.PreferenceWriter;

import org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate.DMAnnotation;
import org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate.DMFeature;
import org.grits.toolbox.datamodel.ms.preference.MassSpecViewerPreference;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.datamodel.GRITSColumnHeader;

/**
 * Extends the Mass Spec Viewer preferences to add elements that are relevant to 
 * MS Annotation of Mass Spec data.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
@XmlRootElement(name = "msAnnotationViewerPreference")
public class MSAnnotationViewerPreference extends MassSpecViewerPreference {
	private static final Logger logger = Logger.getLogger(MSAnnotationViewerPreference.class);
	private static final String PREFERENCE_NAME_ALL = "org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference";
	/*
	 * Version history:
	 * 1.0 - Original release w/ versioning
	 * 1.1 - Committed 03/08/16. Re-versioned because parent class "TableViewerPreference" was modified
	 */
	private static final String CURRENT_VERSION = "1.1";
	protected boolean bHideUnannotatedPeaks = false;
	protected boolean bShowUnannotated = false;
	protected boolean bShowUnannotatedLabels = false;

	public MSAnnotationViewerPreference() {
		this( 0, FillTypes.Scans);
	}
	
	public MSAnnotationViewerPreference(int _iMSLevel, FillTypes fillType) {
		super(_iMSLevel, fillType);
	}
	
	/**
	 * @return true if the user wants to hide un-annotated peak GRITS table, false otherwise
	 */
	public boolean isHideUnannotatedPeaks() {
		return this.bHideUnannotatedPeaks;
	}
	/**
	 * @param _bVal
	 * 		sets whether the user wants to hide un-annotated peak GRITS table
	 */
	@XmlAttribute(name="hideUnannotatedPeaks")
	public void setHideUnannotatedPeaks( boolean _bVal ) {
		this.bHideUnannotatedPeaks = _bVal;
	}
	
	/**
	 * @return true if the user wants to see un-annotated peaks in the MS Spectra, false otherwise
	 */
	public boolean isShowUnannotated() {
		return bShowUnannotated;
	}
	/**
	 * @param bShowUnannotated
	 * 		whether the user wants to see un-annotated peaks in the MS Spectra
	 */
	@XmlAttribute(name="showUnannotatedPeaks")
	public void setShowUnannotated(boolean bShowUnannotated) {
		this.bShowUnannotated = bShowUnannotated;
	}
	
	/**
	 * @return true if the user wants to see un-annotated peak labels in the MS Spectra, false otherwise
	 */
	public boolean isShowUnannotatedLabels() {
		return bShowUnannotatedLabels;
	}
	/**
	 * @param bShowUnannotatedLabels
	 * 		whether the user wants to see un-annotated peak labels in the MS Spectra
	 */
	@XmlAttribute(name="showUnannotatedPeakLabels")
	public void setShowUnannotatedLabels(boolean bShowUnannotatedLabels) {
		this.bShowUnannotatedLabels = bShowUnannotatedLabels;
	}
	
	/**
	 * Called to create the String ID of this MS Annotation GRITS table preference entry.
	 * @param _iMSLevel
	 * 		the MS Level of the GRITS Table
	 * @param _fillType
	 * 		the fill type of the GRITS Table
	 * @return an ID for the preference file for this GRITS Table
	 */
	protected static String getPreferenceID( int _iMSLevel, FillTypes fillType ) {
		String sAdder = "";
		String sName = PREFERENCE_NAME_ALL;
		if ( fillType == FillTypes.Scans ) {
			sAdder = ".Scans";
		}
		else if ( fillType == FillTypes.PeakList ) {
			sAdder = ".Peaks";
		}
		else if ( fillType == FillTypes.PeaksWithFeatures ) {
			sAdder = ".PeaksWithFeatures";
		}
		else if ( fillType == FillTypes.Selection ) {
			sAdder = ".Selection";
		}
		sName += sAdder;
		sName += ".MSLevel" + (_iMSLevel - 1);
		return sName;
	}
	
	/**
	 * @param _iMSLevel
	 * 		the MS Level of the GRITS Table
	 * @param _fillType
	 * 		the fill type of the GRITS Table
	 * @return the MSAnnotation PreferenceEntity for the GRITS Table with the specified MS level and fill type
	 * @throws UnsupportedVersionException
	 */
	public static PreferenceEntity getPreferenceEntity( int _iMSLevel, FillTypes fillType  ) throws UnsupportedVersionException {
		PreferenceEntity preferenceEntity = PreferenceReader.getPreferenceByName(MSAnnotationViewerPreference.getPreferenceID(_iMSLevel, fillType));
		return preferenceEntity;
	}
	
	/* (non-Javadoc)
	 * @see org.grits.toolbox.datamodel.ms.preference.MassSpecViewerPreference#getCurrentVersion()
	 */
	@Override
	protected String getCurrentVersion() {
		return MSAnnotationViewerPreference.CURRENT_VERSION; 
	}

	/* (non-Javadoc)
	 * @see org.grits.toolbox.datamodel.ms.preference.MassSpecViewerPreference#writePreference()
	 */
	@Override
	public boolean writePreference() {
		PreferenceEntity preferenceEntity = new PreferenceEntity(MSAnnotationViewerPreference.getPreferenceID(getMSLevel(), getFillType()));
		preferenceEntity.setVersion(getCurrentVersion());
		preferenceEntity.setValue(marshalXML());
		return PreferenceWriter.savePreference(preferenceEntity);
	}
	
	/** 
	 * Creates MS Annotation column header objects (if the key is recognized). 
	 * If the key isn't recognized, call the super-class method to see if it is known there.
	 * @see org.grits.toolbox.datamodel.ms.preference.MassSpecViewerPreference#getColumnHeader(java.lang.String)
	 */
	@Override
	public GRITSColumnHeader getColumnHeader(String _sKey) {
		if ( _sKey.equals(DMAnnotation.annotation_type.name() ) ) {
			return new GRITSColumnHeader( DMAnnotation.annotation_type.getLabel(), DMAnnotation.annotation_type.name());
		}
		if ( _sKey.equals(DMAnnotation.annotation_id.name() ) ) {
			return new GRITSColumnHeader( DMAnnotation.annotation_id.getLabel(), DMAnnotation.annotation_id.name());
		}
		if ( _sKey.equals(DMAnnotation.annotation_sequence.name() ) ) {
			return new GRITSColumnHeader( DMAnnotation.annotation_sequence.getLabel(), DMAnnotation.annotation_sequence.name());
		}
		if ( _sKey.equals(DMAnnotation.annotation_num_candidates.name() ) ) {
			return new GRITSColumnHeader( DMAnnotation.annotation_num_candidates.getLabel(), DMAnnotation.annotation_num_candidates.name());
		}
		if ( _sKey.equals(DMFeature.feature_id.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_id.getLabel(), DMFeature.feature_id.name());
		}
		if ( _sKey.equals(DMFeature.feature_type.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_type.getLabel(), DMFeature.feature_type.name());
		}
		if ( _sKey.equals(DMFeature.feature_sequence.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_sequence.getLabel(), DMFeature.feature_sequence.name());
		}
		if ( _sKey.equals(DMFeature.feature_mz.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_mz.getLabel(), DMFeature.feature_mz.name());
		}
		if ( _sKey.equals(DMFeature.feature_deviation.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_deviation.getLabel(), DMFeature.feature_deviation.name());
		}
		if ( _sKey.equals(DMFeature.feature_charge.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_charge.getLabel(), DMFeature.feature_charge.name());
		}
		if ( _sKey.equals(DMFeature.feature_annotation_id.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_annotation_id.getLabel(), DMFeature.feature_annotation_id.name());
		}
		if ( _sKey.equals(DMFeature.feature_precursor_id.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_precursor_id.getLabel(), DMFeature.feature_precursor_id.name());
		}
//		if ( _sKey.equals(DMFeature.feature_adduct.name() ) ) {
//			return new SimianColumnHeader( DMFeature.feature_adduct.getLabel(), DMFeature.feature_adduct.name());
//		}
		if ( _sKey.equals(DMFeature.feature_ions.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_ions.getLabel(), DMFeature.feature_ions.name());
		}
		if ( _sKey.equals(DMFeature.feature_fragmentType.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_fragmentType.getLabel(), DMFeature.feature_fragmentType.name());
		}
		if ( _sKey.equals(DMFeature.feature_neutralLosses.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_neutralLosses.getLabel(), DMFeature.feature_neutralLosses.name());
		}
		if ( _sKey.equals(DMFeature.feature_exchanges.name() ) ) {
			return new GRITSColumnHeader( DMFeature.feature_exchanges.getLabel(), DMFeature.feature_exchanges.name());
		}
		return super.getColumnHeader(_sKey);
	}

	
}