package org.grits.toolbox.datamodel.ms.annotation.tablemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreferenceLoader;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.datamodel.ms.tablemodel.MassSpecTableDataObject;
import org.grits.toolbox.display.control.table.datamodel.GRITSColumnHeader;
import org.grits.toolbox.display.control.table.datamodel.GRITSListDataRow;
import org.grits.toolbox.display.control.table.process.TableDataProcessor;
import org.grits.toolbox.ms.om.data.Feature;

public class MSAnnotationTableDataObject extends MassSpecTableDataObject {    
	private static final Logger logger = Logger.getLogger(MSAnnotationTableDataObject.class);
	protected ArrayList<Integer> alFeatureChargeCols;
	protected ArrayList<Integer> alFeatureIdCols;
	protected ArrayList<Integer> alAnnotationIdCols;
	protected ArrayList<Integer> alAnnotationStringIdCols;
	protected ArrayList<Integer> alSequenceCols;
	protected ArrayList<Integer> alUnAnnotatedRows = null;
	protected ArrayList<Integer> filterCols = null;
	protected ArrayList<Integer> commentCols = null;
	protected ArrayList<Integer> ratioCols = null;
	protected boolean usesComplexRowId = false;
	
	public MSAnnotationTableDataObject( int _iMSLevel, FillTypes fillType ) {
		super(_iMSLevel, fillType);
		this.alAnnotationIdCols = new ArrayList<Integer>();  
		this.alFeatureIdCols = new ArrayList<Integer>();  
		this.alFeatureChargeCols = new ArrayList<Integer>();  
		this.alSequenceCols = new ArrayList<Integer>();  
		this.filterCols = new ArrayList<>();
		this.commentCols = new ArrayList<>();
		this.ratioCols = new ArrayList<>();
		this.alAnnotationStringIdCols = new ArrayList<>();
	}
	
	public void setUsesComplexRowId(boolean usesComplexRowId) {
		this.usesComplexRowId = usesComplexRowId;
	}
	
	public boolean getUsesComplexRowId() {
		return usesComplexRowId ;
	}
	
	@Override
	public void initializePreferences() {
    	setTablePreferences(MSAnnotationViewerPreferenceLoader.getTableViewerPreference(getMSLevel(), getFillType()));
	}

	public void setUnAnnotatedRows(ArrayList<Integer> alUnAnnotatedRows) {
		this.alUnAnnotatedRows =  alUnAnnotatedRows;
		Collections.sort(this.alUnAnnotatedRows);
	}

	public boolean isUnannotatedRow( int iRowNum ) {
		return this.alUnAnnotatedRows != null && this.alUnAnnotatedRows.contains(iRowNum);
	}

	public List<Integer> getUnAnnotatedRows() {
		return alUnAnnotatedRows;
	}

	public void addFeatureChargeCol(int iFeatureIdCol) {
		this.alFeatureChargeCols.add(iFeatureIdCol);
	}

	public ArrayList<Integer> getFeatureChargeCols() {
		return alFeatureChargeCols;
	}
	
	public void addFeatureIdCol(int iFeatureIdCol) {
		this.alFeatureIdCols.add(iFeatureIdCol);
	}

	public ArrayList<Integer> getFeatureIdCols() {
		return alFeatureIdCols;
	}

	public void addAnnotationIdCol(int iAnnotationId) {
		this.alAnnotationIdCols.add(iAnnotationId);
	}

	public ArrayList<Integer> getAnnotationIdCols() {
		return alAnnotationIdCols;
	}

	public ArrayList<Integer> getAnnotationStringIdCols() {
		return alAnnotationStringIdCols;
	}

	public void addAnnotationStringIdCol(int iAnnotationStringId) {
		this.alAnnotationStringIdCols.add(iAnnotationStringId);
	}
	
	public ArrayList<Integer> getSequenceCols() {
		return alSequenceCols;
	}

	public void addSequenceCol(int iSequenceCol) {
		this.alSequenceCols.add(iSequenceCol);
	}
	
	public ArrayList<Integer> getFilterCols() {
		return filterCols;
	}
	
	public void addFilterCol (int filterCol) {
		this.filterCols.add(filterCol);
	}
	
	public ArrayList<Integer> getCommentCols() {
		return commentCols;
	}
	
	public void addCommentCol (int commentCol) {
		this.commentCols.add(commentCol);
	}
	
	public ArrayList<Integer> getRatioCols() {
		return ratioCols;
	}
	
	public void addRatioCol (int index) {
		this.ratioCols.add(index);
	}

	protected void cloneHeaderLineForSubsetTable( MSAnnotationTableDataObject subsetSimianTableData ) {
		int iTableSize = getTableHeader().size();
		ArrayList<GRITSColumnHeader> alHeader = (ArrayList<GRITSColumnHeader>) getTableHeader().get(iTableSize - 1).clone();		
		alHeader.add(0, TableDataProcessor.selColHeader);
		subsetSimianTableData.getTableHeader().add(alHeader);
	}

	protected void cloneDataForSubsetTable(MSAnnotationTableDataObject subsetSimianTableData, Integer _iParentScanNum, String _sParentRowId, boolean _bCheckParentScan) {
		ArrayList<String> alShown = new ArrayList<>();
		try {
			// note this is only applicable for non-merge reports (single scan rows), thus using get(0) for appropriate data types
			for(int i = 0; i < getTableData().size(); i++ ) {
				GRITSListDataRow alData = getTableData().get(i);
				if ( alData.getDataRow().get( getPeakIdCols().get(0) ) == null ) {
					GRITSListDataRow alNewData = (GRITSListDataRow) alData.clone();
					alNewData.getDataRow().add(0, null);
					subsetSimianTableData.getTableData().add(alNewData);
					continue;
				}
				if ( getPeakIdCols().isEmpty() || getFeatureIdCols().isEmpty() )
					continue;

				if( alData.getDataRow().get(getPeakIdCols().get(0)) == null || 
						alData.getDataRow().get(getFeatureIdCols().get(0)) == null ) {
					continue;
				}
				int iParentScanNum = -1;
				if( getParentNoCol() != null && ! getParentNoCol().isEmpty() ) {
					if( alData.getDataRow().get(getParentNoCol().get(0)) != null ) {
						iParentScanNum = (Integer) alData.getDataRow().get(getParentNoCol().get(0));
					}
				}
				if ( _bCheckParentScan && _iParentScanNum != -1 && _iParentScanNum != iParentScanNum ) 
					continue;
				Integer iPeakId = (Integer) alData.getDataRow().get(getPeakIdCols().get(0));
				Integer iScanNum = null;
				if( alData.getDataRow().get(getScanNoCols().get(0)) != null ) {
					iScanNum = (Integer) alData.getDataRow().get(getScanNoCols().get(0));
				}
				String sRowId = Feature.getRowId(iPeakId, iScanNum, getUsesComplexRowId());
				if( ! _sParentRowId.equals(sRowId) ) {
					continue;
				}
				
				String sFeatureId = (String) alData.getDataRow().get(getFeatureIdCols().get(0)).toString();
				if( alShown.contains(sFeatureId) )
					continue;
				GRITSListDataRow alNewData = (GRITSListDataRow) alData.clone();
				boolean bHidden = isHiddenRow(_iParentScanNum, sRowId, sFeatureId) || isInvisibleRow(_iParentScanNum, sRowId);
				if ( sFeatureId == null ) {
					alNewData.getDataRow().add(0, null);
				} else if ( bHidden || sFeatureId == null) {        	
					alNewData.getDataRow().add(0, Boolean.FALSE);
				} else {
					alNewData.getDataRow().add(0, Boolean.TRUE);
				}			
				subsetSimianTableData.getTableData().add(alNewData);
				alShown.add(sFeatureId);
			}

		} catch(Exception e) {
			logger.log(Level.ERROR, "Exception in cloneDataForSubsetTable", e);
			e.printStackTrace();
		}
		// adding 2 blank rows to the subset table		
		subsetSimianTableData.getTableData().add( TableDataProcessor.getNewRow( getTableData().get(0).getDataRow().size() + 1, getTableData().size()) );
		subsetSimianTableData.getTableData().add( TableDataProcessor.getNewRow( getTableData().get(0).getDataRow().size() + 1, getTableData().size()) );
	}

	// this is needed because we added the "Selected" column and need to shift everything over 1
	protected void setColIdSettingsForSubsetObject( MSAnnotationTableDataObject subsetSimianTableData ) {
		// note this is only applicable for non-merge reports (single scan rows), thus using get(0) for appropriate data types
		if( getFeatureIdCols() != null && ! getFeatureIdCols().isEmpty() )
			subsetSimianTableData.addFeatureIdCol( getFeatureIdCols().get(0) + 1 );
		if( getFeatureChargeCols() != null && ! getFeatureChargeCols().isEmpty() )
			subsetSimianTableData.addFeatureChargeCol( getFeatureChargeCols().get(0) + 1 );
		if( getScanNoCols() != null && ! getScanNoCols().isEmpty() )
			subsetSimianTableData.addScanNoCol( getScanNoCols().get(0) + 1);
		if( getParentNoCol() != null && ! getParentNoCol().isEmpty() )
			subsetSimianTableData.addParentNoCol( getParentNoCol().get(0) + 1);
		if( getPeakIdCols() != null && ! getPeakIdCols().isEmpty() )
			subsetSimianTableData.addPeakIdCol( getPeakIdCols().get(0) + 1);
		if( getMzCols() != null && ! getMzCols().isEmpty() )
			subsetSimianTableData.addMzCol( getMzCols().get(0) + 1);
		if( getSequenceCols() != null && ! getSequenceCols().isEmpty() ) {
			for( int i = 0; i < getSequenceCols().size(); i++ ) {
				subsetSimianTableData.addSequenceCol( getSequenceCols().get(i) + 1);
			}
		}
		if( getAnnotationIdCols() != null && ! getAnnotationIdCols().isEmpty() ) {
			subsetSimianTableData.addAnnotationIdCol( getAnnotationIdCols().get(0) + 1);
		}
		if( getAnnotationStringIdCols() != null && ! getAnnotationStringIdCols().isEmpty() ) {
			subsetSimianTableData.addAnnotationStringIdCol( getAnnotationStringIdCols().get(0) + 1);
		}
		if (getFilterCols() != null && !getFilterCols().isEmpty()) {
			subsetSimianTableData.addFilterCol(getFilterCols().get(0) + 1);
		}
		if (getCommentCols() != null && !getCommentCols().isEmpty()) {
			subsetSimianTableData.addCommentCol(getCommentCols().get(0) + 1);
		}
		if (getRatioCols() != null && !getRatioCols().isEmpty()) {
			subsetSimianTableData.addRatioCol(getRatioCols().get(0) + 1);
		}
	}

	public MSAnnotationTableDataObject getSubsetSimianTableDataObject(int _iParentScanNum, String _sParentRowId, boolean _bCheckParentScan) {
		MSAnnotationTableDataObject subsetSimianTableData = new MSAnnotationTableDataObject(getMSLevel(), getFillType());
		return getSubsetSimianTableDataObject(_iParentScanNum, _sParentRowId, subsetSimianTableData, _bCheckParentScan);
	}

	protected MSAnnotationTableDataObject getSubsetSimianTableDataObject(int _iParentScanNum, String _sParentRowId, 
			MSAnnotationTableDataObject subsetSimianTableData, boolean _bCheckParentScan ) {
		subsetSimianTableData.setUsesComplexRowId(getUsesComplexRowId());
		setColIdSettingsForSubsetObject(subsetSimianTableData);
		cloneHeaderLineForSubsetTable(subsetSimianTableData);
		cloneDataForSubsetTable(subsetSimianTableData, _iParentScanNum, _sParentRowId, _bCheckParentScan);
		return subsetSimianTableData;
	}
}
