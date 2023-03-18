package com.bezkoder.spring.files.csv.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.csv.helper.AssetMappings;
import com.bezkoder.spring.files.csv.helper.AssetType;
import com.bezkoder.spring.files.csv.helper.CSVHelper;
import com.bezkoder.spring.files.csv.helper.CurlCommands;
import com.bezkoder.spring.files.csv.helper.MastPortalType;
import com.bezkoder.spring.files.csv.model.Login;
import com.bezkoder.spring.files.csv.model.MastDetails;

@Service
public class RestService {

	
	//Solve issue for Mast at single line station like Raha

	String headers = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n"
			+ "Accept-Encoding: gzip, deflate, br\r\n"
			+ "Accept-Language: en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7\r\n"
			+ "Cache-Control: max-age=0\r\n"
			+ "Connection: keep-alive\r\n"
			+ "Cookie: Cookie_TDMS=AK7LCygiTQqlTvBmA14UBA$$; JSESSIONID=\"VavEt8DejVHezIjzXJXmIFn9uVSMBC_WQItoXg-B.master:server-two\"; TS013cc5b6=01ea7166bc9ff5215df6b9db38f95d2975e6656de831b1827ef1458de98949f4d5e5440aa49909b4ee1a2247c9abf170992bfae20b45b7695ce43bd33ed7ff356eb28cda31427e319e956808cba10515ef1d1bcc4b4348cd7b6393d0d9c82f7841a92f72a2; _ga=GA1.3.597427796.1675432819; _gid=GA1.3.1887741203.1675432819; railsaver=405294346.20480.0000; TS0166d8b7=01ea7166bc4cf7ab3fded68ac9d1fa5a86a895ee3d31b1827ef1458de98949f4d5e5440aa49909b4ee1a2247c9abf170992bfae20bce0106ab86bf8ae6d7ab9b87c144f286; _gat_gtag_UA_180576841_1=1\r\n"
			+ "Host: tdms.railsaver.gov.in\r\n"
			+ "Referer: https://tdms.railsaver.gov.in/?\r\n"
			+ "Sec-Fetch-Dest: document\r\n"
			+ "Sec-Fetch-Mode: navigate\r\n"
			+ "Sec-Fetch-Site: same-origin\r\n"
			+ "Sec-Fetch-User: ?1\r\n"
			+ "Upgrade-Insecure-Requests: 1\r\n"
			+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36\r\n"
			+ "sec-ch-ua: \"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"\r\n"
			+ "sec-ch-ua-mobile: ?0\r\n"
			+ "sec-ch-ua-platform: \"Windows\"";
	
	HttpHeaders httpHeaders = null;
	RestTemplate restTemplate = new RestTemplate();
	String baseUrl = "https://tdms.railsaver.gov.in/TDMS";
	String cookie = "Cookie_TDMS=AMP1JigiTQpzx6Nc9VezHw$$; JSESSIONID=\"igF9yfHWrMPLLyjeni-NjmRQbz5rzWPdOdRNhddk.master:server-one\"; TS013cc5b6=01ea7166bc4f900910a4c06e71cd626730933ebabf0b039c0017e49b5e8dee06a41eb2f3ec843fe47b4850439aee19dc945cdf10bc0ccc6e855a1fde97c0e8443b971c822b9e81405977c580397e423613e8aa00b3; _ga=GA1.3.597427796.1675432819; _gid=GA1.3.1887741203.1675432819; railsaver=405294346.20480.0000; TS0166d8b7=01ea7166bc40459349ef75b01467ed1908d6107e00d770ad799aa8d0ca08ef9de4ec6d7d6233c323109e663ddca56054a70d7c366fbde1c14cb5cf313006874d23105c96d4";
	AssetMappings assetMappings = null;
	int i =0;
	List<String> assetIds = null;
	public RestService() throws IOException {
		super();
		this.httpHeaders = this.stringToHttpHeaders(this.headers);
		this.assetMappings = new AssetMappings();
		this.assetIds = this.assetMappings.getSCEAssetID();
	}

	public void save(MultipartFile file) throws Exception {

		Map<String, List<MastDetails>> listOfMastDetails = CSVHelper.csvToMastDetails(file.getInputStream());

		/*------------------------------------------
		for (MastDetails mastDetails : listOfMastDetails) {
			try {
				ResponseEntity<String> response = uploadData(mastDetails);
				System.out.println("\n" + "Entering mast details for : " + mastDetails.getLocation());
				System.out.println(response.getBody());
				System.out.println(response.getStatusCode());
				System.out.println("Mast details successfully entered for : " + mastDetails.getLocation() + "\n");
			} catch (Exception e) {
				throw new RuntimeException("fail to store csv data for loc. : " + mastDetails.getLocation() + "\n" + e.getMessage(), e);
			}
		}
		/*------------------------------------------*/
		
		/*------------------------------------------*/
		//Map<String, List<MastDetails>> mastDetailsMap = new HashMap<String, List<MastDetails>>();
		System.out.println("Entering Mast/Portal details for [" + listOfMastDetails.size() + "] records.");
		for (Map.Entry<String,List<MastDetails>> mastDetailsEntry : listOfMastDetails.entrySet()) {
			try {
				ResponseEntity<String> response = uploadDataNew(mastDetailsEntry);
				System.out.println("\n" + "Entering mast details for : " + mastDetailsEntry.getKey());
				System.out.println(response.getBody());
				System.out.println(response.getStatusCode());
				System.out.println("Mast details successfully entered for : " + mastDetailsEntry.getKey() + "\n");
			} catch (Exception e) {
				throw new RuntimeException("fail to store csv data for loc. : " + mastDetailsEntry.getKey() + "\n" + e.getMessage(), e);
			}
		
		}
		
		/*------------------------------------------*/

	}
	
	
	  private ResponseEntity<String> uploadDataNew(Entry<String, List<MastDetails>>
	  mastDetailsEntry) throws Exception {
	  
	  String command = new
	  String(generateSaveMastDetailsCommandNew(mastDetailsEntry));
	  
	  //Check for any unfinished command 
	  if(command.contains("${")) 
		  throw new Exception("Invalid command. Command not generated fully : " + command); 
	  else
	  { 
		  System.out.println(command); 
		  return executePost(command); 
		  //return new ResponseEntity<>(HttpStatus.OK); 
		  }
	  
	  }
	 

	
	  private String generateSaveMastDetailsCommandNew(Entry<String, List<MastDetails>> mastDetailsEntry) throws Exception {
			
		  	System.out.println("Generating Mast/Portal details command for location : " + mastDetailsEntry.getKey());
			String command;
			Map<String, Object> params = new HashMap<>();
			List<MastDetails> mastDetailsList = mastDetailsEntry.getValue();
			boolean isPortalOrTTCorHeadSpan = false;
			if(mastDetailsList.size() == 0) 
				throw new IllegalArgumentException("Empty mast details");
			else if(mastDetailsList.size() > 1)
				isPortalOrTTCorHeadSpan = true;
			
			//First mast details is taken as parent for the exhaustive portal data
			MastDetails mastDetails = mastDetailsList.get(0);
			//Task type S for save mast
			params.put("task", "S");
			params.put("assettype", AssetType.MAST.getAssetType());
			params.put("secstncode", "");
			params.put("linelooplinecode", "");
			params.put("secstnflag", "");
			params.put("minkm", "");
			params.put("maxkm", "");
			params.put("minmet", "");
			params.put("maxmet", "");
			
			params.put("mastportalname", mastDetails.getLocation());
			
			params.put("assetid", this.assetIds.get(i++));
			params.put("gissection", "" );
			params.put("gisassetname", "");
			params.put("mastportal", MastPortalType.valueOf(mastDetails.getMastPortalType()).getMastPortalTypeId());
			
			if(mastDetails.getMastPortalType().equals(MastPortalType.Mast.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.Umbrella_Mast.toString()))
					params.put("typeofmastportal", assetMappings.getMastCode(mastDetails.getTypeOfMastPortal()));
			else if(mastDetails.getMastPortalType().equals(MastPortalType.Head_Span.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.Portal.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.TTC.toString()))
				params.put("typeofmastportal", assetMappings.getPortalOrTTCOrHEadSpanCode(mastDetails.getTypeOfMastPortal()));
			else
				throw new Exception("Wrong mast portal type : " + mastDetails.getTypeOfMastPortal());
			
			params.put("assetname", mastDetails.getLocation());
			
			if(isPortalOrTTCorHeadSpan) {
				params.put("passetname", mastDetails.getOtherSideLocation());
				params.put("passetid", this.assetIds.get(i++));
			}
				
			params.put("gisassetname1", "");
			params.put("longitude", mastDetails.getLongitude());
			params.put("latitude", mastDetails.getLatitude());
			params.put("altitude", mastDetails.getAltitude());
			
			if(mastDetails.getIsStationOrSection().equals("sec"))
			{
				params.put("oheflag", "sec");
				params.put("ohesection", assetMappings.getSectionCode(mastDetails.getSection()));
				params.put("oheflag1", "");
				params.put("ohestation", "");
				params.put("oheloopline", "");
				if (isPortalOrTTCorHeadSpan) {
					params.put("portalData", generatePortalDACommand(mastDetailsList, null));
					params.put("oheline", getMultipleOheLineCommand(mastDetailsList));
				}
				else {
					params.put("oheline", assetMappings.getOheLineCode(mastDetails.getLine()));
					params.put("portalData", "");
				}
			}
			else
				{
				params.put("oheflag", "stn");
				params.put("ohestation", mastDetails.getStation());
				//params.put("oheloopline", assetMappings.getLineCodeFromLine(mastDetails.getLoopLine()));
				params.put("oheflag1", "");
				params.put("ohesection", "");
				params.put("oheline", "");
				if(isPortalOrTTCorHeadSpan) {
					params.put("portalData", generatePortalDACommand(mastDetailsList, mastDetails.getStation()));
					params.put("oheloopline", getOheLoopLineCommand(mastDetailsList, mastDetails.getStation()));
				}
				else {
					params.put("oheloopline", assetMappings.getOheLoopLineCode(mastDetails.getStation(), mastDetails.getOheLoopLine()));
					params.put("portalData", "");
				}
					
				}
				
			params.put("oheflag_hidden", "");
			
			if(!isPortalOrTTCorHeadSpan)
			{
			params.put("elesection", mastDetails.getElementarySection());
			params.put("elesection1", "");
			
			}
			else{
			params.put("elesection", "");
			params.put("elesection1", "");
			}
			params.put("pgissection", "");
			params.put("pgisassetname", "");
			params.put("passetname", mastDetails.getOtherSideLocation());
			params.put("pgisdetails","");
			params.put("tmsLocNo", "");
			params.put("supermast", mastDetails.getIsSuperMast());
			params.put("kmfrom", mastDetails.getChgKM());
			params.put("metfrom", mastDetails.getChgMeter());
			//Validation for date MM/YYYY
			params.put("layingdate", mastDetails.getDateOfCommissioning());
			params.put("sedstepdist", mastDetails.getStepDistance());
			
			if(!StringUtils.isEmpty(mastDetails.getAnchorType())) {
				params.put("anchorType", "--form \"anchortype=" + assetMappings.getAnchorTypeCode(mastDetails.getAnchorType()) + "\" ");
			}
			else {
				params.put("anchorType", "");
			}
			//params.put("mastportallocation", getMastPortAllocation(mastDetails.getTypeOfLocation()));
			params.put("mastportallocation", getRepeatingFormData("mastportallocation", mastDetails.getTypeOfLocation()));
			params.put("cautionBoard", getRepeatingFormData("cautionBoard", mastDetails.getCautionBoard()));
			params.put("sedtrackcenter", mastDetails.getTrackCenter());
			params.put("sedimplantation", mastDetails.getImplantation());
			params.put("foundationtype", mastDetails.getFdnType());
			params.put("manualfoundationtype", "");
			params.put("foundationdtl", "");
			
			//Portal data
			if(mastDetailsList.size() > 0)
				params.put("numofinsulator", mastDetailsList.size());
			else
				params.put("numofinsulator", "");
			
			params.put("boomlength", mastDetails.getBoomLength());
			params.put("mastprojection", mastDetails.getMastProjection());
			params.put("anchorlocation", mastDetails.getIsAnchoredLocation());
			params.put("sacrificialmast", mastDetails.getIsSacrificalMast());
			//Code of je(ic)/trd/scelmg
			params.put("sseincharge", "023011");
			params.put("costofaquis", "");
			params.put("remarks", mastDetails.getRemarks());
			
			//Portal datas
			params.put("lineType", "");
			params.put("datype", "");
			params.put("daid", "");
			params.put("selectDALinevalues", "");
			params.put("daline", "");
			params.put("pelesection", "");
			params.put("pelesection1", "");
			
			//Supermast details
			params.put("supermast1", "");
			params.put("superMastid", "");
			params.put("supermastdetails1", "");
			params.put("supermasttype1", "");
			
			String sedFileUploadCommand = "";
			if(StringUtils.isNotBlank(mastDetails.getSedFileName())) {
				sedFileUploadCommand = "--form \"uploadfile=@SED/CPK-SCE/${fileName}.jpg\"";
				Map<String, Object> fileNameParam = new HashMap<>();
				fileNameParam.put("fileName", mastDetails.getSedFileName());
				sedFileUploadCommand = StrSubstitutor.replace(sedFileUploadCommand, fileNameParam, "${", "}");
				params.put("uploadFileCommand", sedFileUploadCommand);
			}
			else
				params.put("uploadFileCommand", "");

			String dataCommand = new String(CurlCommands.UPLOAD_LOC_DATA);
			String header = new String(CurlCommands.UPLOAD_LOC_HEADER);
			String url = new String(CurlCommands.UPLOAD_LOC_URL);
			String curlFinalCommand = new String(CurlCommands.CURL_POST_COMMAND);

			String cookie = "TS013cc5b6=01ea7166bc67a12b6efe8e34e0116a699eb8e4201332cc3d8b62330a660497dcfb8c4f279add2452d5da11cd0ff2b82af31b04b46e; Cookie_TDMS=AN1NLSgiTQqH45ILAoOzdg$$; JSESSIONID=_bHCYwCr1bhsPiZxFbiA6HcTrE7CsYLwXbr-RDkr.pcpepapp022; _ga=GA1.3.597427796.1675432819; railsaver=405294346.20480.0000; _gid=GA1.3.1282526598.1679145575; TS0166d8b7=01ea7166bc44a7335312a22a05d1cc15819a0c5a938361441976609eef399f759b5834a378775a651e37ab63bcdc68b8a8a3823292efeeeca0850a622ac3900432d77b1b97";
			dataCommand = StrSubstitutor.replace(dataCommand, params, "${", "}");
			Map<String, Object> headerParams = new HashMap<>();
			headerParams.put	("Cookie", cookie);
			header = StrSubstitutor.replace(header, headerParams, "${", "}");
			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("header", header);
			commandParams.put("url", url);
			commandParams.put("data", dataCommand);
			command = StrSubstitutor.replace(curlFinalCommand, commandParams);
			
		return command;
	}
	 

	private String getMultipleOheLineCommand(List<MastDetails> mastDetailsList) throws Exception {
		List<String> lineCodes = new ArrayList<>();
		for(MastDetails mastDetails : mastDetailsList) {
			lineCodes.add(mastDetails.getLine());
		}
		return getRepeatingFormData("oheline", String.join(";", lineCodes));
	}

	private String getOheLoopLineCommand(List<MastDetails> mastDetailsList, String station) throws IOException, Exception {
		
		List<String> loopLineCodes = new ArrayList<>();
		for(MastDetails mastDetails : mastDetailsList) {
			loopLineCodes.add(mastDetails.getOheLoopLine());
		}
		return getRepeatingFormData("oheloopline", String.join(";", loopLineCodes), station);
	}

	private String generatePortalDACommand(List<MastDetails> mastDetailsList, String station) throws Exception {
		
		String portalData = "--form \"lineType=${lineType}\" --form \"datype=${datype}\" --form \"daid=${daid}\" --form \"selectDALinevalues=${selectDALinevalues}\" --form \"daline=${daline}\" --form \"pelesection=${pelesection}\" --form \"pelesection1=${pelesection1}\" ";
		String portalCommand = "";
		for(MastDetails mastDetails : mastDetailsList) {
			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("lineType", assetMappings.getDaLineTypeCode(mastDetails.getLineType()));
			commandParams.put("datype", assetMappings.getDATypeCode(mastDetails.getDatype()));
			commandParams.put("daid", mastDetails.getDaid());
			//Code required to set all looplines
			String selectDALineValue = null;
			String daLine = null;
			if(StringUtils.isNotBlank(station)) {
				selectDALineValue = assetMappings.getOheLoopLineCode(station, mastDetails.getOheLoopLine());
				daLine = assetMappings.getOheLoopLineCode(station, mastDetails.getOheLoopLine());
			}
			else {
				selectDALineValue = assetMappings.getOheLineCode(mastDetails.getOheLoopLine());
				daLine = assetMappings.getOheLineCode(mastDetails.getOheLoopLine());
			}
			commandParams.put("selectDALinevalues", selectDALineValue);
			commandParams.put("daline", daLine);
			String[] elementarySections = StringUtils.split( mastDetails.getElementarySection(), ";");
			commandParams.put("pelesection", elementarySections[0]);
			
			// A DA can have maximum of two separate elementary sections
			if(elementarySections.length == 2) {
				commandParams.put("pelesection1", elementarySections[1]);
			}
			
			portalCommand = portalCommand.concat(StrSubstitutor.replace(portalData, commandParams));
		}
		
		return portalCommand;
	}

	public ResponseEntity<String> tdmsLogin2() throws IOException {
		
		String command = new String ("curl -H \"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\" -H \"Accept-Language: en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7\" -H \"Cache-Control: max-age=0\" -H \"Connection: keep-alive\" -H \"Content-Type: application/x-www-form-urlencoded\" -H \"Cookie: Cookie_TDMS=AI87LigiTQri3rNqBeMzDQ$$; JSESSIONID=\"jq2s-XAD3NLw0sJsdMeo1TbcxJ69_SGWP1jyhLRE.master:server-one\"; TS013cc5b6=01ea7166bc539ca81b465c9cbd0a86718e1da7684e5b7860c713048860fd161cdccbcdfed565002e611199482a7f4ed91767939214fd67661178a0a64f7845dd1decad844a105755763f105d8ad5e6d5143908b2ff; _ga=GA1.3.597427796.1675432819; _gid=GA1.3.1887741203.1675432819; railsaver=405294346.20480.0000; TS0166d8b7=01ea7166bc9af0776b91284e111660376766cd4f855b7860c713048860fd161cdccbcdfed5a433151fd8d8649651caed8b8e220fe04f0b2b8516d0594a8d6323ae380f6cee; _gat_gtag_UA_180576841_1=1\" -H \"Origin: https://tdms.railsaver.gov.in\" -H \"Referer: https://tdms.railsaver.gov.in/TDMS\" -H \"Sec-Fetch-Dest: document\" -H \"Sec-Fetch-Mode: navigate\" -H \"Sec-Fetch-Site: same-origin\" -H \"Sec-Fetch-User: ?1\" -H \"Upgrade-Insecure-Requests: 1\" -H \"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36\" -H \"sec-ch-ua-mobile: ?0\" -H \"sec-ch-ua-platform: Windows\" -X POST https://tdms.railsaver.gov.in/TDMS/LoginController --data-raw \"loginid=jeictrdcpk&password=roy%40123&answer=nw56h\"");
		return executePost(command);
	}
	
	//Command to execute command
	private ResponseEntity<String> executePost(String command) throws IOException {

		Process process = Runtime.getRuntime().exec(command);
		BufferedReader reader = null;
		
		while(process.isAlive()) {
			
		}
		System.out.println("Command executed");
		if(process.exitValue() == 0)
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		else
			reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		String line;
		StringBuffer response = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			response.append(line);
		}

		if(process.exitValue() == 0)
			return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
		else
			throw new IOException("Command didn't execute due to : \n" + response.toString());
	}
	
	public ResponseEntity<String> uploadData(MastDetails mastDetails) throws Exception {
		
		String command = new String(generateSaveMastDetailsCommand(mastDetails));
		
		//Check for any unfinished command
		if(command.contains("${"))
			throw new Exception("Invalid command. Command not generated fully : " + command);
		else {
			System.out.println(command);
			return executePost(command);
		}
	}
	
	private String generateSaveMastDetailsCommand (MastDetails mastDetails) throws Exception {
		
			String command;
			Map<String, Object> params = new HashMap<>();
			
			//Task type S for save mast
			params.put("task", "S");
			params.put("assettype", AssetType.MAST.getAssetType());
			params.put("secstncode", "");
			params.put("linelooplinecode", "");
			params.put("secstnflag", "");
			params.put("minkm", "");
			params.put("maxkm", "");
			params.put("minmet", "");
			params.put("maxmet", "");
			params.put("mastportalname", mastDetails.getLocation());
			params.put("assetid", "");
			params.put("gissection", "" );
			params.put("gisassetname", "");
			params.put("mastportal", MastPortalType.valueOf(mastDetails.getMastPortalType()).getMastPortalTypeId());
			
			if(mastDetails.getMastPortalType().equals(MastPortalType.Mast.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.Umbrella_Mast.toString()))
					params.put("typeofmastportal", assetMappings.getMastCode(mastDetails.getTypeOfMastPortal()));
			else if(mastDetails.getMastPortalType().equals(MastPortalType.Head_Span.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.Portal.toString()) || mastDetails.getMastPortalType().equals(MastPortalType.TTC.toString()))
				params.put("typeofmastportal", assetMappings.getPortalOrTTCOrHEadSpanCode(mastDetails.getTypeOfMastPortal()));
			else
				throw new Exception("Wrong mast portal type : " + mastDetails.getTypeOfMastPortal());
			
			params.put("assetname", mastDetails.getLocation());
			params.put("gisassetname1", "");
			params.put("longitude", mastDetails.getLongitude());
			params.put("latitude", mastDetails.getLatitude());
			params.put("altitude", mastDetails.getAltitude());
			
			if(mastDetails.getIsStationOrSection().equals("sec"))
				{
				params.put("oheflag", "sec");
				params.put("ohesection", assetMappings.getSectionCode(mastDetails.getSection()));
				params.put("oheline", assetMappings.getOheLineCode(mastDetails.getLine()));
				params.put("oheflag1", "");
				params.put("ohestation", "");
				params.put("oheloopline", "");
				}
			else
				{
				params.put("oheflag", "stn");
				params.put("ohestation", mastDetails.getStation());
				params.put("oheloopline", assetMappings.getOheLineCode(mastDetails.getLoopLine()));
				params.put("oheflag1", "");
				params.put("ohesection", "");
				params.put("oheline", "");
				}
			params.put("oheflag_hidden", "");
			
			params.put("elesection", mastDetails.getElementarySection());
			params.put("elesection1", "");
			params.put("pgissection", "");
			params.put("pgisassetname", "");
			params.put("passetname", "");
			params.put("passetid", "");
			params.put("pgisdetails","");
			params.put("tmsLocNo", "");
			params.put("supermast", mastDetails.getIsSuperMast());
			params.put("kmfrom", mastDetails.getChgKM());
			params.put("metfrom", mastDetails.getChgMeter());
			//Validation for date MM/YYYY
			params.put("layingdate", mastDetails.getDateOfCommissioning());
			params.put("sedstepdist", mastDetails.getStepDistance());
			
			if(!StringUtils.isEmpty(mastDetails.getAnchorType())) {
				params.put("anchorType", "--form \"anchortype=" + assetMappings.getAnchorTypeCode(mastDetails.getAnchorType()) + "\" ");
			}
			else {
				params.put("anchorType", "");
			}
			params.put("mastportallocation", getMastPortAllocation(mastDetails.getTypeOfLocation()));
			params.put("cautionBoard", getCautionBoard(mastDetails.getCautionBoard()));
			params.put("sedtrackcenter", mastDetails.getTrackCenter());
			params.put("sedimplantation", mastDetails.getImplantation());
			params.put("foundationtype", mastDetails.getFdnType());
			params.put("manualfoundationtype", "");
			params.put("foundationdtl", "");
			
			//Portal data
			if(!StringUtils.isEmpty(mastDetails.getNoOfDropArms()))
				params.put("numofinsulator", mastDetails.getNoOfDropArms());
			else
				params.put("numofinsulator", "");
			
			params.put("boomlength", mastDetails.getBoomLength());
			params.put("mastprojection", mastDetails.getMastProjection());
			params.put("anchorlocation", mastDetails.getIsAnchoredLocation());
			params.put("sacrificialmast", mastDetails.getIsSacrificalMast());
			//Code of je(ic)/trd/scelmg
			params.put("sseincharge", "023011");
			params.put("costofaquis", "");
			params.put("remarks", "");
			
			//Portal datas
			params.put("lineType", "");
			params.put("datype", "");
			params.put("daid", "");
			params.put("selectDALinevalues", "");
			params.put("daline", "");
			params.put("pelesection", "");
			params.put("pelesection1", "");
			
			//Supermast details
			params.put("supermast1", "");
			params.put("superMastid", "");
			params.put("supermastdetails1", "");
			params.put("supermasttype1", "");
			
			String sedFileUploadCommand = "";
			if(StringUtils.isNotBlank(mastDetails.getSedFileName())) {
				sedFileUploadCommand = "--form \"uploadfile=@SED/CPK-SCE/${fileName}.jpg\" ";
				Map<String, Object> fileNameParam = new HashMap<>();
				fileNameParam.put("fileName", mastDetails.getSedFileName());
				sedFileUploadCommand = StrSubstitutor.replace(sedFileUploadCommand, fileNameParam, "${", "}");
				params.put("uploadFileCommand", sedFileUploadCommand);
			}
			else
				params.put("uploadFileCommand", "");

			String dataCommand = new String(CurlCommands.UPLOAD_LOC_DATA);
			String header = new String(CurlCommands.UPLOAD_LOC_HEADER);
			String url = new String(CurlCommands.UPLOAD_LOC_URL);
			String curlFinalCommand = new String(CurlCommands.CURL_POST_COMMAND);

			String cookie = "Cookie_TDMS=AAcIDCgiTQrxaHxaFIcyKg$$; TS013cc5b6=01ea7166bc2e670fd0561bdd5f5ee2ccad01d314b12b1db0c6b6e3f2b7958d5abf9ebf7c9a819b7f864c5718c5861e0a63a771e297; JSESSIONID=\"ebK9U_LX_gltI6ZctqAMrBYIc_Zi8ANgxZ2VQKi6.master:server-one\"; _ga=GA1.3.597427796.1675432819; _gid=GA1.3.172256030.1677155575; railsaver=405294346.20480.0000; TS0166d8b7=01ea7166bcef939423213de63f5f429773614d6e5975f3bea1dfcf60a45f48ccb353851ddda4ab01ed457de84272fb8ac795ce096e1599cb935d7913e9d7f5beb5ebadbf8b";
			dataCommand = StrSubstitutor.replace(dataCommand, params, "${", "}");
			Map<String, Object> headerParams = new HashMap<>();
			headerParams.put	("Cookie", cookie);
			header = StrSubstitutor.replace(header, headerParams, "${", "}");
			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("header", header);
			commandParams.put("url", url);
			commandParams.put("data", dataCommand);
			command = StrSubstitutor.replace(curlFinalCommand, commandParams);
			
		return command;
	}
	 
	public String getRepeatingFormData(String formAttribute, String attributeValuesString, String... station) throws Exception {

		String[] attributeValues = StringUtils.split( attributeValuesString, ";");
		int i = 0;
		String formDataString = "";
		while (i < attributeValues.length) {
			try {
				if (i == 0) {

					if (formAttribute.equals("mastportallocation"))
						formDataString = new String(assetMappings.getTypeOfLocationCode(attributeValues[i++]));
					else if (formAttribute.equals("cautionBoard"))
						formDataString = new String(assetMappings.getCautionBoardCode(attributeValues[i++]));
					else if (formAttribute.equals("oheloopline"))
						formDataString = new String(assetMappings.getOheLoopLineCode(station[0], attributeValues[i++])) ;
					else if(formAttribute.equals("oheline")) {
						formDataString = new String(assetMappings.getOheLineCode(attributeValues[i++])) ;
					}
				} else {
					String attributeValueCode = null;
					if (formAttribute.equals("mastportallocation"))
						attributeValueCode = assetMappings.getTypeOfLocationCode(attributeValues[i++]);
					else if (formAttribute.equals("cautionBoard"))
						attributeValueCode = assetMappings.getCautionBoardCode(attributeValues[i++]);
					else if (formAttribute.equals("oheloopline"))
						attributeValueCode = assetMappings.getOheLoopLineCode(station[0], attributeValues[i++]) ;
					else if(formAttribute.equals("oheline")) {
						attributeValueCode = assetMappings.getOheLineCode(attributeValues[i++]) ;
					}

					formDataString = formDataString.concat("\" --form \"" + formAttribute + "=" + attributeValueCode);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return formDataString;
	
	}
	
	public String getMastPortAllocation(String typeOfLocationsString) throws Exception {
		
		String[] typeOfLocations = StringUtils.split(typeOfLocationsString, ";");
		int i = 0;
		String mastPortAllocation = null;
		while(i < typeOfLocations.length) {
			try {
			if(i == 0) {
				
				mastPortAllocation = new String(assetMappings.getTypeOfLocationCode(typeOfLocations[i++]));
				
			}
			else {
				mastPortAllocation = mastPortAllocation.concat("\" --form \"mastportallocation=" + assetMappings.getTypeOfLocationCode(typeOfLocations[i++]));
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(mastPortAllocation == null) {
			throw new Exception("Wrong type of location string");
		}
		
		return mastPortAllocation;
	}
	
	public String getCautionBoard(String typeOfCautionBoard) throws Exception {
		
		if(StringUtils.isBlank(typeOfCautionBoard))
			return "";
		
		String[] typeOfCautionBoards = StringUtils.split(typeOfCautionBoard, ";");
		int i = 0;
		String cautionBoard = null;
		while(i < typeOfCautionBoards.length) {
			try {
			if(i == 0) {
				
				cautionBoard = new String(assetMappings.getCautionBoardCode((typeOfCautionBoards[i++])));
				
			}
			else {
				cautionBoard = cautionBoard.concat("\" --form \"cautionBoard=" + assetMappings.getCautionBoardCode(typeOfCautionBoards[i++]));
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(cautionBoard == null) {
			throw new Exception("Wrong type of caution board string");
		}
		
		return cautionBoard;
	}
	
	public ResponseEntity<String> tdmsLogin() throws URISyntaxException {
		
		HttpHeaders header = new HttpHeaders();
		ResponseEntity<String> hitResponse = restTemplate.getForEntity(baseUrl, String.class);
		
		/*
		 * List<String> cookies = hitResponse.getHeaders().get(HttpHeaders.SET_COOKIE);
		 * header.addAll(HttpHeaders.COOKIE, cookies);
		 */
		URI uri = new URI(baseUrl + "/LoginController");
		
		HttpHeaders hitHeader = hitResponse.getHeaders();
		
		
		final String[] tempHeaderArray = this.cookie.split(";");
	    int i = 0;
	    List<String> cookieList = new ArrayList<String>();
	    while (i + 1 <= tempHeaderArray.length) {
	    	cookieList.add(tempHeaderArray[i++]);
	    	
	    }
	    
	    header.addAll(HttpHeaders.COOKIE, cookieList);
	    header.addAll(HttpHeaders.SET_COOKIE, cookieList);
	    
		HttpEntity<Login> loginDetails = new HttpEntity<Login>(new Login("jeictrdcpk", "roy@123", "k42bc"), header);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, loginDetails, String.class);
				//postForEntity(uri, loginDetails, String.class);
		
		System.out.println("Cookie : " + response.getHeaders().get(HttpHeaders.COOKIE));
		System.out.println("Set-Cookie : " + response.getHeaders().get(HttpHeaders.SET_COOKIE));
				//restTemplate.getForEntity(uri, String.class);
				//
		return response;
	}
	
	public ResponseEntity<String> getData() throws URISyntaxException {
		
		URI uri = new URI(baseUrl + "/OHEMasterDiv2.jsp");
		HttpHeaders header = new HttpHeaders();
		final String[] tempHeaderArray = cookie.split(";");
	    int i = 0;
	    List<String> cookieList = new ArrayList<String>();
	    while (i + 1 <= tempHeaderArray.length) {
	    	cookieList.add(tempHeaderArray[i++]);
	    	
	    }
	    header.addAll(HttpHeaders.COOKIE, cookieList);
		return restTemplate.getForEntity(uri, null);
	}
	
	private HttpHeaders stringToHttpHeaders(final String headerContents) {
		HttpHeaders httpHeaders = new HttpHeaders();
	    final String[] tempHeaderArray = headerContents.split(System.getProperty("line.separator"));
	    int i = 0;
	    while (i + 1 <= tempHeaderArray.length) {
	    	String[] header = tempHeaderArray[i++].split(":");
	    	httpHeaders.add(header[0].trim(), header[1].trim());
	    }
	    return httpHeaders;
	}
	
}
