package com.bezkoder.spring.files.csv.model;

import java.util.Objects;

/*import javax.persistence.Column;
import javax.persistence.Id;*/


public class MastDetails {

  //@Id
	//@Column(name = "id")
  private long id;

  //@Column(name = "title")
  private String line;

  //@Column(name = "description")
  private String section;

  //@Column(name = "published")
  private String station;
  
  private String loopLine;

  private String elementarySection;
  
  private String chgKM;
  
  private String chgMeter;
  
  private String location;
  
  private String typeOfLocation;
  
  private String mastPortalType;
  
  private String typeOfMastPortal;
  
  private String longitude;
  
  private String latitude;
  
  private String altitude;
  
  private String implantation;
  
  private String fdnType;
  
  private String stepDistance;
  
  private String isAnchoredLocation;
  
  private String anchorType;
  
  private String isSacrificalMast;
  
  private String trackCenter;
  
  private String mastProjection;
  
  private String cautionBoard;
  
  private String isSuperMast;
  
  private String superMastDetails;
  
  private String superMastType;
  
  private String dateOfCommissioning;
  
  private String isStationOrSection;
  
  private String oheStation;
  
  private String oheLoopLine;
  
  private String noOfDropArms;

  private String boomLength;

  private String sedFileName;
  
  private String otherSideLocation;
  
  private String remarks;
  
  private String lineType;
  
  private String datype;
  
  private String daid;
  
  private String selectDALinevalues;
  
  private String daline;
  
  private String pelesection;
  
  private String pelesection1;
  
public MastDetails(long id, String line, String section, String station, String loopLine, String elementarySection,
		String chgKM, String chgMeter, String location, String typeOfLocation, String mastPortalType,
		String typeOfMastPortal, String longitude, String latitude, String altitude, String implantation,
		String fdnType, String stepDistance, String isAnchoredLocation, String anchorType, String isSacrificalMast,
		String trackCenter, String mastProjection, String cautionBoard, String isSuperMast, String superMastDetails,
		String superMastType, String dateOfCommissioning, String isStationOrSection, String oheStation,
		String oheLoopLine, String noOfDropArms, String boomLength, String sedFileName) {
	super();
	this.id = id;
	this.line = line;
	this.section = section;
	this.station = station;
	this.loopLine = loopLine;
	this.elementarySection = elementarySection;
	this.chgKM = chgKM;
	this.chgMeter = chgMeter;
	this.location = location;
	this.typeOfLocation = typeOfLocation;
	this.mastPortalType = mastPortalType;
	this.typeOfMastPortal = typeOfMastPortal;
	this.longitude = longitude;
	this.latitude = latitude;
	this.altitude = altitude;
	this.implantation = implantation;
	this.fdnType = fdnType;
	this.stepDistance = stepDistance;
	this.isAnchoredLocation = isAnchoredLocation;
	this.anchorType = anchorType;
	this.isSacrificalMast = isSacrificalMast;
	this.trackCenter = trackCenter;
	this.mastProjection = mastProjection;
	this.cautionBoard = cautionBoard;
	this.isSuperMast = isSuperMast;
	this.superMastDetails = superMastDetails;
	this.superMastType = superMastType;
	this.dateOfCommissioning = dateOfCommissioning;
	this.isStationOrSection = isStationOrSection;
	this.oheStation = oheStation;
	this.oheLoopLine = oheLoopLine;
	this.noOfDropArms = noOfDropArms;
	this.boomLength = boomLength;
	this.sedFileName = sedFileName;
}

public MastDetails() {
	// TODO Auto-generated constructor stub
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getLine() {
	return line;
}

public void setLine(String line) {
	this.line = line;
}

public String getSection() {
	return section;
}

public void setSection(String section) {
	this.section = section;
}

public String getStation() {
	return station;
}

public void setStation(String station) {
	this.station = station;
}

public String getLoopLine() {
	return loopLine;
}

public void setLoopLine(String loopLine) {
	this.loopLine = loopLine;
}

public String getElementarySection() {
	return elementarySection;
}

public void setElementarySection(String elementarySection) {
	this.elementarySection = elementarySection;
}

public String getChgKM() {
	return chgKM;
}

public void setChgKM(String chgKM) {
	this.chgKM = chgKM;
}

public String getChgMeter() {
	return chgMeter;
}

public void setChgMeter(String chgMeter) {
	this.chgMeter = chgMeter;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getTypeOfLocation() {
	return typeOfLocation;
}

public void setTypeOfLocation(String typeOfLocation) {
	this.typeOfLocation = typeOfLocation;
}

public String getMastPortalType() {
	return mastPortalType;
}

public void setMastPortalType(String mastPortalType) {
	this.mastPortalType = mastPortalType;
}

public String getTypeOfMastPortal() {
	return typeOfMastPortal;
}

public void setTypeOfMastPortal(String typeOfMastPortal) {
	this.typeOfMastPortal = typeOfMastPortal;
}

public String getLongitude() {
	return longitude;
}

public void setLongitude(String longitude) {
	this.longitude = longitude;
}

public String getLatitude() {
	return latitude;
}

public void setLatitude(String latitude) {
	this.latitude = latitude;
}

public String getAltitude() {
	return altitude;
}

public void setAltitude(String altitude) {
	this.altitude = altitude;
}

public String getImplantation() {
	return implantation;
}

public void setImplantation(String implantation) {
	this.implantation = implantation;
}

public String getFdnType() {
	return fdnType;
}

public void setFdnType(String fdnType) {
	this.fdnType = fdnType;
}

public String getStepDistance() {
	return stepDistance;
}

public void setStepDistance(String stepDistance) {
	this.stepDistance = stepDistance;
}

public String getIsAnchoredLocation() {
	return isAnchoredLocation;
}

public void setIsAnchoredLocation(String isAnchoredLocation) {
	this.isAnchoredLocation = isAnchoredLocation;
}

public String getAnchorType() {
	return anchorType;
}

public void setAnchorType(String anchorType) {
	this.anchorType = anchorType;
}

public String getIsSacrificalMast() {
	return isSacrificalMast;
}

public void setIsSacrificalMast(String isSacrificalMast) {
	this.isSacrificalMast = isSacrificalMast;
}

public String getTrackCenter() {
	return trackCenter;
}

public void setTrackCenter(String trackCenter) {
	this.trackCenter = trackCenter;
}

public String getMastProjection() {
	return mastProjection;
}

public void setMastProjection(String mastProjection) {
	this.mastProjection = mastProjection;
}

public String getCautionBoard() {
	return cautionBoard;
}

public void setCautionBoard(String cautionBoard) {
	this.cautionBoard = cautionBoard;
}

public String getIsSuperMast() {
	return isSuperMast;
}

public void setIsSuperMast(String isSuperMast) {
	this.isSuperMast = isSuperMast;
}

public String getSuperMastDetails() {
	return superMastDetails;
}

public void setSuperMastDetails(String superMastDetails) {
	this.superMastDetails = superMastDetails;
}

public String getSuperMastType() {
	return superMastType;
}

public void setSuperMastType(String superMastType) {
	this.superMastType = superMastType;
}

public String getDateOfCommissioning() {
	return dateOfCommissioning;
}

public void setDateOfCommissioning(String dateOfCommissioning) {
	this.dateOfCommissioning = dateOfCommissioning;
}

public String getIsStationOrSection() {
	return isStationOrSection;
}

public void setIsStationOrSection(String isStationOrSection) {
	this.isStationOrSection = isStationOrSection;
}



public String getOheStation() {
	return oheStation;
}

public void setOheStation(String oheStation) {
	this.oheStation = oheStation;
}

public String getOheLoopLine() {
	return oheLoopLine;
}

public void setOheLoopLine(String oheLoopLine) {
	this.oheLoopLine = oheLoopLine;
}

/**
 * @return the noOfDropArms
 */
public String getNoOfDropArms() {
	return noOfDropArms;
}

/**
 * @param noOfDropArms the noOfDropArms to set
 */
public void setNoOfDropArms(String noOfDropArms) {
	this.noOfDropArms = noOfDropArms;
}

/**
 * @return the boomLength
 */
public String getBoomLength() {
	return boomLength;
}

/**
 * @param boomLength the boomLength to set
 */
public void setBoomLength(String boomLength) {
	this.boomLength = boomLength;
}

/**
 * @return the sedFileName
 */
public String getSedFileName() {
	return sedFileName;
}

/**
 * @param sedFileName the sedFileName to set
 */
public void setSedFileName(String sedFileName) {
	this.sedFileName = sedFileName;
}

/**
 * @return the otherSideLocation
 */
public String getOtherSideLocation() {
	return otherSideLocation;
}



/**
 * @param otherSideLocation the otherSideLocation to set
 */
public void setOtherSideLocation(String otherSideLocation) {
	this.otherSideLocation = otherSideLocation;
}

/**
 * @return the remarks
 */
public String getRemarks() {
	return remarks;
}

/**
 * @param remarks the remarks to set
 */
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

/**
 * @return the lineType
 */
public String getLineType() {
	return lineType;
}

/**
 * @param lineType the lineType to set
 */
public void setLineType(String lineType) {
	this.lineType = lineType;
}

/**
 * @return the datype
 */
public String getDatype() {
	return datype;
}

/**
 * @param datype the datype to set
 */
public void setDatype(String datype) {
	this.datype = datype;
}

/**
 * @return the daid
 */
public String getDaid() {
	return daid;
}

/**
 * @param daid the daid to set
 */
public void setDaid(String daid) {
	this.daid = daid;
}

/**
 * @return the selectDALinevalues
 */
public String getSelectDALinevalues() {
	return selectDALinevalues;
}

/**
 * @param selectDALinevalues the selectDALinevalues to set
 */
public void setSelectDALinevalues(String selectDALinevalues) {
	this.selectDALinevalues = selectDALinevalues;
}

/**
 * @return the daline
 */
public String getDaline() {
	return daline;
}

/**
 * @param daline the daline to set
 */
public void setDaline(String daline) {
	this.daline = daline;
}

/**
 * @return the pelesection
 */
public String getPelesection() {
	return pelesection;
}

/**
 * @param pelesection the pelesection to set
 */
public void setPelesection(String pelesection) {
	this.pelesection = pelesection;
}

/**
 * @return the pelesection1
 */
public String getPelesection1() {
	return pelesection1;
}

/**
 * @param pelesection1 the pelesection1 to set
 */
public void setPelesection1(String pelesection1) {
	this.pelesection1 = pelesection1;
}

@Override
public int hashCode() {
	return Objects.hash(altitude, anchorType, boomLength, cautionBoard, chgKM, chgMeter, dateOfCommissioning,
			elementarySection, fdnType, id, implantation, isAnchoredLocation, isSacrificalMast, isStationOrSection,
			isSuperMast, latitude, line, location, longitude, loopLine, mastPortalType, mastProjection, noOfDropArms,
			oheLoopLine, oheStation, section, sedFileName, station, stepDistance, superMastDetails, superMastType,
			trackCenter, typeOfLocation, typeOfMastPortal);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MastDetails other = (MastDetails) obj;
	return Objects.equals(altitude, other.altitude) && Objects.equals(anchorType, other.anchorType)
			&& Objects.equals(boomLength, other.boomLength) && Objects.equals(cautionBoard, other.cautionBoard)
			&& Objects.equals(chgKM, other.chgKM) && Objects.equals(chgMeter, other.chgMeter)
			&& Objects.equals(dateOfCommissioning, other.dateOfCommissioning)
			&& Objects.equals(elementarySection, other.elementarySection) && Objects.equals(fdnType, other.fdnType)
			&& id == other.id && Objects.equals(implantation, other.implantation)
			&& Objects.equals(isAnchoredLocation, other.isAnchoredLocation)
			&& Objects.equals(isSacrificalMast, other.isSacrificalMast)
			&& Objects.equals(isStationOrSection, other.isStationOrSection)
			&& Objects.equals(isSuperMast, other.isSuperMast) && Objects.equals(latitude, other.latitude)
			&& Objects.equals(line, other.line) && Objects.equals(location, other.location)
			&& Objects.equals(longitude, other.longitude) && Objects.equals(loopLine, other.loopLine)
			&& Objects.equals(mastPortalType, other.mastPortalType)
			&& Objects.equals(mastProjection, other.mastProjection) && Objects.equals(noOfDropArms, other.noOfDropArms)
			&& Objects.equals(oheLoopLine, other.oheLoopLine) && Objects.equals(oheStation, other.oheStation)
			&& Objects.equals(section, other.section) && Objects.equals(sedFileName, other.sedFileName)
			&& Objects.equals(station, other.station) && Objects.equals(stepDistance, other.stepDistance)
			&& Objects.equals(superMastDetails, other.superMastDetails)
			&& Objects.equals(superMastType, other.superMastType) && Objects.equals(trackCenter, other.trackCenter)
			&& Objects.equals(typeOfLocation, other.typeOfLocation)
			&& Objects.equals(typeOfMastPortal, other.typeOfMastPortal);
}

@Override
public String toString() {
	return "MastDetails [id=" + id + ", line=" + line + ", section=" + section + ", station=" + station + ", loopLine="
			+ loopLine + ", elementarySection=" + elementarySection + ", chgKM=" + chgKM + ", chgMeter=" + chgMeter
			+ ", location=" + location + ", typeOfLocation=" + typeOfLocation + ", mastPortalType=" + mastPortalType
			+ ", typeOfMastPortal=" + typeOfMastPortal + ", longitude=" + longitude + ", latitude=" + latitude
			+ ", altitude=" + altitude + ", implantation=" + implantation + ", fdnType=" + fdnType + ", stepDistance="
			+ stepDistance + ", isAnchoredLocation=" + isAnchoredLocation + ", anchorType=" + anchorType
			+ ", isSacrificalMast=" + isSacrificalMast + ", trackCenter=" + trackCenter + ", mastProjection="
			+ mastProjection + ", cautionBoard=" + cautionBoard + ", isSuperMast=" + isSuperMast + ", superMastDetails="
			+ superMastDetails + ", superMastType=" + superMastType + ", dateOfCommissioning=" + dateOfCommissioning
			+ ", isStationOrSection=" + isStationOrSection + ", oheStation=" + oheStation + ", oheLoopLine="
			+ oheLoopLine + ", noOfDropArms=" + noOfDropArms + ", boomLength=" + boomLength + ", sedFileName="
			+ sedFileName + "]";
}

 	

}
