package com.bezkoder.spring.files.csv.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.csv.model.MastDetails;

public class CSVHelper {
	public static String TYPE = "text/csv";
	
	//Last Max. Size = 37
	static String[] HEADERs = { "SN", "Main Section", "Line", "Section", "Station", "Loop Line", "Elementary section",
			"Km", "Chg.", "LOC", "Type of Location", "Mast Portal Type", "TYPE OF MAST/PORTAL as per TDMS",
			"TYPE OF MAST/PORTAL", "Longitude", "Latitute", "Altitute", "IMP (M)", "Foundation Type", "SD",
			"Anchored Location", "Anchor Type", "Boom Length", "Sacrificial Mast",
			"Track Centers as per SED (in meters)", "Projection of mast (in cm)", "Caution Board", "Super Mast",
			"Super Mast Details", "Super Mast Type", "Date of commissioning/Erection", "SED Diagram Filename", "REMARKS", "Other Side Loc.",
			"Type of Line", "Type of DA/ Upright", "Line of DA/ Upright"};

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static Map<String, List<MastDetails>> csvToMastDetails(InputStream is) {

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			//List<MastDetails> listOfMastDetails = new ArrayList<MastDetails>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			Map<String, List<MastDetails>> mastDetailsMap = new LinkedHashMap<String, List<MastDetails>>();
			for (CSVRecord csvRecord : csvRecords) {
				
				MastDetails mastDetails = new MastDetails();
				mastDetails.setAltitude(csvRecord.get(HEADERs[16]));
				mastDetails.setAnchorType(csvRecord.get(HEADERs[21]));
				mastDetails.setBoomLength(csvRecord.get(HEADERs[22]));
				mastDetails.setCautionBoard(csvRecord.get(HEADERs[26]));
				mastDetails.setChgKM(csvRecord.get(HEADERs[7]));
				mastDetails.setChgMeter(csvRecord.get(HEADERs[8]));

				// MM/YYYY
				mastDetails.setDateOfCommissioning(csvRecord.get(HEADERs[30]));
				mastDetails.setElementarySection(csvRecord.get(HEADERs[6]));
				mastDetails.setFdnType(csvRecord.get(HEADERs[18]));
				// mastDetails.setId(long);
				mastDetails.setImplantation(csvRecord.get(HEADERs[17]));

				mastDetails.setIsAnchoredLocation(csvRecord.get(HEADERs[20]));
				mastDetails.setAnchorType(csvRecord.get(HEADERs[21]));
				mastDetails.setIsSacrificalMast(csvRecord.get(HEADERs[23]));

				if (StringUtils.isEmpty(csvRecord.get(HEADERs[4]))) {
					mastDetails.setIsStationOrSection("sec");
					mastDetails.setSection(csvRecord.get(HEADERs[1]));
					mastDetails.setLine(csvRecord.get(HEADERs[2]));
				} else {
					mastDetails.setIsStationOrSection("stn");
					mastDetails.setStation(csvRecord.get(HEADERs[4]));
					mastDetails.setLoopLine(csvRecord.get(HEADERs[5]));
					mastDetails.setOheLoopLine(csvRecord.get(HEADERs[5]));
					mastDetails.setOheStation(csvRecord.get(HEADERs[4]));
				}
				
				// Update in googlesheet
				mastDetails.setIsSuperMast(csvRecord.get(HEADERs[27]));
				mastDetails.setLatitude(csvRecord.get(HEADERs[15]));

				mastDetails.setLocation(csvRecord.get(HEADERs[9]));
				mastDetails.setLongitude(csvRecord.get(HEADERs[14]));
				// mastDetails.setLoopLine(csvRecord.get(HEADERs[5]));
				mastDetails.setMastPortalType(csvRecord.get(HEADERs[11]));
				mastDetails.setMastProjection(csvRecord.get(HEADERs[25]));

				// logic to be written for portal
				// mastDetails.setNoOfDropArms(csvRecord.get(HEADERs[]));

				mastDetails.setIsSacrificalMast(csvRecord.get(HEADERs[23]));
				mastDetails.setStepDistance(csvRecord.get(HEADERs[19]));
				mastDetails.setSuperMastDetails(csvRecord.get(HEADERs[28]));
				mastDetails.setSuperMastType(csvRecord.get(HEADERs[29]));
				mastDetails.setTrackCenter(csvRecord.get(HEADERs[24]));
				mastDetails.setTypeOfLocation(csvRecord.get(HEADERs[10]));

				// Update in google sheet
				mastDetails.setTypeOfMastPortal(csvRecord.get(HEADERs[12]));
				mastDetails.setSedFileName(csvRecord.get(HEADERs[31]));
				mastDetails.setOtherSideLocation(csvRecord.get(HEADERs[33]));
				mastDetails.setRemarks(csvRecord.get(HEADERs[32]));
				
				mastDetails.setLineType(csvRecord.get(HEADERs[34]));
				mastDetails.setDatype(csvRecord.get(HEADERs[35]));
				mastDetails.setDaid("");
				//mastDetails.setSelectDALinevalues(csvRecord.get(HEADERs[36]));
				//mastDetails.setDaline(csvRecord.get(HEADERs[36]));
				System.out.println("____________________________________________");
				System.out.println("Adding row to list : " + mastDetails.toString());
					List<MastDetails> mastPortalDetails = null;
					//For portal mutiple entries for single location.
					if (mastDetailsMap.containsKey(mastDetails.getLocation())) {
						if(isValidPortalDetails(mastDetails)) {
						mastPortalDetails = mastDetailsMap.get(mastDetails.getLocation());
						mastPortalDetails.add(mastDetails);
						mastDetailsMap.put(mastDetails.getLocation(), mastPortalDetails);
						System.out.println("Added mast/portal with loc. " + mastDetails.getLocation());
						}
					} else {
						if (isValidMastDetails(mastDetails)) {
							mastPortalDetails = new ArrayList<>();
							mastPortalDetails.add(mastDetails);
							mastDetailsMap.put(mastDetails.getLocation(), mastPortalDetails);
							System.out.println("Added mast/portal with loc. " + mastDetails.getLocation());
						} else
							throw new Exception("Invalid mast Details : " + mastDetails.toString());
						//listOfMastDetails.add(mastDetails);
					}
					
			}

			return mastDetailsMap;
		} catch (Exception e) {
			throw new RuntimeException("fail to parse CSV file : ", e);
		}
	}

	private static boolean isValidPortalDetails(MastDetails mastDetails) throws Exception {
		
		String message = "";
		if(StringUtils.isBlank(mastDetails.getLineType())) {
			message = message + "\n" + "Location is portal but DA/Upright Line type is Blank";
		}
		
		if(StringUtils.isBlank(mastDetails.getDatype())) {
			message = message + "\n" + "Location is portal but DA/Upright type is Blank";
		}
		
		if(StringUtils.isBlank(mastDetails.getOheLoopLine())) {
			message = message + "\n" + "DA Line Value is Blank";
		}
		
		/*
		 * if(StringUtils.isBlank(mastDetails.getDaline())) { message = message + "\n" +
		 * "DA Line Value is Blank"; }
		 */
		
		if(StringUtils.isBlank(mastDetails.getElementarySection())) {
			message = message + "\n" + "DA Elementary section value is Blank";
		}
		
		if(StringUtils.isNotBlank(message)) {
			throw new Exception(message);
		}
		
		return true;
		
	}

	private static boolean isValidMastDetails(MastDetails mastDetails) throws Exception {
		
		String message = "";
		if(StringUtils.isBlank(mastDetails.getAltitude())) {
			//message = message + "\n" + "Location altitude is blank : ";
		}
		else if(!NumberUtils.isParsable(mastDetails.getAltitude()))
			message = message + "\n" + "Location altitude not numeric : " + mastDetails.getAltitude();
		
		mastDetails.setIsAnchoredLocation(mastDetails.getIsAnchoredLocation().toUpperCase());
		if(StringUtils.isBlank(mastDetails.getIsAnchoredLocation())){
			mastDetails.setIsAnchoredLocation("N");
		}
		
		if(!StringUtils.equals(mastDetails.getIsAnchoredLocation(), "Y") && StringUtils.isNotBlank(mastDetails.getAnchorType()))
			message = message + "\n" + "Location is not a anchor location but anchor type is entered : " + mastDetails.getAnchorType();
		else if(StringUtils.equals(mastDetails.getIsAnchoredLocation(), "Y") && StringUtils.isBlank(mastDetails.getAnchorType()))
			message = message + "\n" + "Location is a anchor location but anchor type blank : " + mastDetails.getAnchorType();
		
		if(StringUtils.isNotBlank(mastDetails.getBoomLength()) && !NumberUtils.isParsable(mastDetails.getBoomLength()))
			message = message + "\n" + "Boom length entered is not numeric : " + mastDetails.getBoomLength();
		
		if(!NumberUtils.isParsable(mastDetails.getChgKM()))
			message = message + "\n" + "Chainage KM is not numeric : " + mastDetails.getChgKM();
		
		if(!NumberUtils.isParsable(mastDetails.getChgMeter()))
			message = message + "\n" + "Chainage meter is not numeric : " + mastDetails.getChgMeter();
		
	
		
		//mm/yyyy format
		 String regex = "^(1[0-2]|0[1-9])/[0-9]{4}$";
		 Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher((CharSequence) mastDetails.getDateOfCommissioning());
		
	     if(!matcher.matches())
			message = message + "\n" + "Wrong date format : " + mastDetails.getDateOfCommissioning();
		
		if(StringUtils.isBlank(mastDetails.getElementarySection()))
			message = message + "\n" + "Elementary section is blank";
		
		if(!NumberUtils.isParsable(mastDetails.getImplantation()))
			message = message + "\n" + "Implantation is not numeric : " + mastDetails.getImplantation();
		
		if(StringUtils.isBlank(mastDetails.getIsSacrificalMast()))
			mastDetails.setIsSacrificalMast("N");
		
		mastDetails.setIsSacrificalMast(mastDetails.getIsSacrificalMast().toUpperCase());
		
		if (!StringUtils.equals(mastDetails.getIsSacrificalMast(), "Y") && !StringUtils.equals(mastDetails.getIsSacrificalMast(), "N"))
			message = message + "\n" + "Wrong sacrifical mast counter : " + mastDetails.getIsSacrificalMast();
		
		if(StringUtils.isBlank(mastDetails.getIsStationOrSection()))
			message = message + "\n" + "Blank station sec flag : ";
		else if(!StringUtils.equals(mastDetails.getIsStationOrSection(), "stn") && !StringUtils.equals(mastDetails.getIsStationOrSection(), "sec"))
			message = message + "\n" + "Wrong stn sec flag : " + mastDetails.getIsStationOrSection();
		else if(StringUtils.equals(mastDetails.getIsStationOrSection(), "stn")) {
			if(StringUtils.isBlank(mastDetails.getStation()))
				message = message + "\n" + "Station flag set but station is null";
			
			if(StringUtils.isNotBlank(mastDetails.getStation()) && StringUtils.isBlank(mastDetails.getLoopLine()))
				message = message + "\n" + "Station flag set but loop line is null";
		}
		else if(StringUtils.equals(mastDetails.getIsStationOrSection(), "sec")) {
			if(StringUtils.isBlank(mastDetails.getSection()))
				message = message + "\n" + "Section flag set but section is null";
			
			if(StringUtils.isNotBlank(mastDetails.getSection()) && StringUtils.isBlank(mastDetails.getLine()))
				message = message + "\n" + "Section flag set but line is null";
		}
		
		if(StringUtils.isBlank(mastDetails.getIsSuperMast()))
			mastDetails.setIsSuperMast("N");
		
		mastDetails.setIsSuperMast(mastDetails.getIsSuperMast().toUpperCase());
		
		if (!StringUtils.equals(mastDetails.getIsSuperMast(), "Y") && !StringUtils.equals(mastDetails.getIsSuperMast(), "N"))
			message = message + "\n" + "Wrong super mast counter : " + mastDetails.getIsSuperMast();
		else if(StringUtils.equals(mastDetails.getIsSuperMast(), "Y") && StringUtils.isBlank(mastDetails.getSuperMastType()))
			message = message + "\n" + "Mast is supermast but no type specified";
		
		if(StringUtils.isBlank(mastDetails.getLatitude())) {
			//message = message + "\n" + "Latitude is blank";
		}
		else if(!NumberUtils.isParsable(mastDetails.getLatitude()))
			message = message + "\n" + "Latitude is not numeric : " + mastDetails.getLatitude();

		if(StringUtils.isBlank(mastDetails.getLocation())) {
			message = message + "\n" + "Location is blank";
		}
		
		if(StringUtils.isBlank(mastDetails.getLongitude())) {
			//message = message + "\n" + "Longitude is blank";
		}
		else if(!NumberUtils.isParsable(mastDetails.getLongitude()))
			message = message + "\n" + "Longitude is not numeric : " + mastDetails.getLongitude();
		
		MastPortalType.valueOf(mastDetails.getMastPortalType());
		
		if(StringUtils.isNotBlank(mastDetails.getMastProjection()) && !NumberUtils.isParsable(mastDetails.getMastProjection()))
			message = message + "\n" + "Projection is not numeric : " + mastDetails.getMastProjection();
		
		//Logic required for drop arms
		//mastDetails.getNoOfDropArms()
		
		//Redundant
		/*mastDetails.getOheLoopLine()
		mastDetails.getOheStation()*/
		/*
		 * if (StringUtils.isBlank(mastDetails.getStepDistance())) { message = message +
		 * "\n" + "Step Distance if Blank"; }
		 */
		if(StringUtils.isNotBlank(mastDetails.getStepDistance()) && !NumberUtils.isParsable(mastDetails.getStepDistance()))
			message = message + "\n" + "Step Distance is not numeric : " + mastDetails.getStepDistance();
		//else if(Integer.valueOf(mastDetails.getStepDistance()) > 80)
		//	message = message + "\n" + "Step Distance is more than 80 cm : " + mastDetails.getStepDistance();
		
		if(StringUtils.isNotBlank(mastDetails.getTrackCenter()) && !NumberUtils.isParsable(mastDetails.getTrackCenter())) {
			message = message + "\n" + "Track center is not numeric : " + mastDetails.getTrackCenter();
		}
		
		if(StringUtils.isBlank(mastDetails.getTypeOfLocation())) {
			message = message + "\n" + "Type of location is Blank";
		}
		
		if(StringUtils.isBlank(mastDetails.getTypeOfMastPortal())) {
			message = message + "\n" + "Type of Mast/Potal is Blank";
		}
		
		if(StringUtils.isNotBlank(message)) {
			throw new Exception(message);
		}
		
		return true;
	}

	public static ByteArrayInputStream tutorialsToCSV(List<MastDetails> tutorials) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (MastDetails tutorial : tutorials) {
				List<String> data = null;

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
