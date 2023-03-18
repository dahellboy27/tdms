package com.bezkoder.spring.files.csv.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class AssetMappings {

	public String getAnchorTypeCode(String anchorType) throws Exception {

		switch (anchorType) {
			case "Self Anchored":
				return "01";

			case "Drawf Mast":
				return "02";

			case "Anchor Block":
				return "03";
			default :
				throw new Exception("Invalid anchor type : " + anchorType);
		}
	
	}
	
	public String getDaLineTypeCode(String daLineType) throws Exception {

		switch (daLineType) {
			case "Single Line":
				return "01";

			case "Umbrella Line":
				return "02";

			default :
				throw new Exception("Invalid DA Line type : " + daLineType);
		}
	
	}
	
	/*
	public String getLineCodeFromLine(String line) throws Exception {

		switch (line) {
		case "AGTL-SBRMSL":
			return "0:1487-003";

		case "ARCL-JRBMSL":
			return "0:1064-003";

		case "ARCL-SCLSL":
			return "0:1062-003";

		case "BPB-KXJSL":
			return "0:1058-003";

		case "BPB-KTXSL":
			return "0:1060-003";

		case "BRGM-AGTLSL":
			return "0:1067-003";

		case "BRGM-DLCRSL":
			return "0:1066-003";

		case "CPK-LMGDN":
			return "0:1032-002";

		case "CPK-LMGSL":
			return "0:1032-003";

		case "CPK-LMGUP":
			return "0:1032-001";

		case "CPK-SCESL":
			return "0:1054-003";

		case "JRBM- VNGPSL":
			return "0:1488-003";

		case "KXJ-MSSNSL":
			return "0:1059-003";

		case "KXJ-BRGMSL":
			return "0:1065-003";

		case "KTX-ARCLSL":
			return "0:1061-003";

		case "KTX-BHRBSL":
			return "0:1063-003";

		case "LMG-BPBSL":
			return "0:1057-003";

		case "LMG-FKGSL":
			return "0:1033-003";

		case "NBQ-KYQSL":
			return "0:1026-003";

		case "NKMG- BY PASS CABINSL":
			return "0:1507-003";

		case "SMUN - SBJNSL":
			return "0:1531-003";

		case "PKB - MYD BypassSL":
			return "0:1505-003";

		case "RNY-SGT BRSL":
			return "0:1006-003";

		case "SGT BR-CPKDN":
			return "0:1031-002";

		case "SGT BR-CPKN":
			return "0:1031-005";

		case "SGT BR-CPKS":
			return "0:1031-006";

		case "SGT BR-CPKUP":
			return "0:1031-001";

		case "SCE-MBOSL":
			return "0:1055-003";

		case "SCE-SHTTS":
			return "0:1056-006";

		case "TTLA- KMLJSL":
			return "0:1506-003";

		default:
			throw new Exception("Invalid line code : " + line);
		}
	}
	*/
	
	public String getSectionCode(String section) throws Exception {
		
		switch (section) {
		case "Agartala - Sabroom (AGTL-SBRM) (0 - 114)":
			return "1487";
		case "Arunachal Jn-Jiribam (ARCL-JRBM) (0 - 49)":
			return "1064";
		case "Arunachal Jn-Silchar (ARCL-SCL) (23 - 30)":
			return "1062";
		case "Badarpur Jn-Karimganj Jn (BPB-KXJ) (169 - 189)":
			return "1058";
		case "Badarpur Jn-Katakhal Jn (BPB-KTX) (0 - 9)":
			return "1060";
		case "Baraigram Jn-Agartala (BRGM-AGTL) (0 - 181)":
			return "1067";
		case "Baraigram Jn-Dullabcherra (BRGM-DLCR) (21 - 51)":
			return "1066";
		case "Chaparmukh Jn-Lumding Jn (CPK-LMG) (101 - 191)":
			return "1032";
		case "Chaparmukh Jn-Senchoa Jn (CPK-SCE) (0 - 20)":
			return "1054";
		case "Dhansiri - Shokhuvi (DSR - SHKV) (0 - 16)":
			return "1576";
		case "Jiribam - Vangaichungpao (JRBM- VNGP) (0 - 11)":
			return "1488";
		case "Karimganj Jn-Maishashan (KXJ-MSSN) (189 - 201)":
			return "1059";
		case "Karimnagar Jn-Baraigram Jn (KXJ-BRGM) (0 - 21)":
			return "1065";
		case "Katakhal Jn-Arunachal Jn (KTX-ARCL) (9 - 23)":
			return "1061";
		case "Katakhal Jn-Bhairabi (KTX-BHRB) (0 - 85)":
			return "1063";
		case "Lumding Jn-Badarpur Jn (LMG-BPB) (0 - 169)":
			return "1057";
		case "Lumding Jn-Furkating Jn (LMG-FKG) (191 - 329)":
			return "1033";
		case "New Bongaigaon-Kamakhya (NBQ-KYQ) (0 - 175)":
			return "1026";
		case "New Karimganj  - By Pass Cabin (NKMG- BY PASS CABIN) (0 - 1)":
			return "1507";
		case "New Sambhu - New Sarai Banjara (SMUN - SBJN) (320 - 338)":
			return "1531";
		case "Pkb - Myd Bypass (PKB - MYD Bypass) (0 - 8)":
			return "1505";
		case "Rangiya-Sariaghat Bridge (RNY-SGT BR) (360 - 397)":
			return "1006";
		case "Saraighat Bridge-Chaparmukh Jn (SGT BR-CPK) (0 - 101)":
			return "1031";
		case "Senchoa Jn-Mairabari (SCE-MBO) (0 - 49)":
			return "1055";
		case "Senchoa Jn-Silghat Town (SCE-SHTT) (0 - 61)":
			return "1056";
		case "Tetelia  -  Kamalajari (TTLA- KMLJ) (0 - 10)":
			return "1506";
		default:
			throw new Exception("Invalid section : " + section);
		}
	}
	
	public String getDATypeCode(String daType) throws Exception {

		switch (daType) {
		case "A":
			return "01";
		case "B":
			return "02";
		case "C":
			return "03";
		case "BFB":
			return "04";
		case "RSJ":
			return "05";
		case "Short":
			return "06";
		case "Upright":
			return "07";
		default:
			throw new Exception("Invalid DA Type : " + daType);
		}
	}
	
	public String getTypeOfLocationCode(String typeOfLocation) throws Exception {
		
		switch (typeOfLocation) {
		case "Curvature":
			return "01";
		case "Obligatory":
			return "03";
		case "Tunnels":
			return "04";
		case "Girder Bridge":
			return "05";
		case "Platform":
			return "08";
		case "IOL":
			return "09";
		case "UIOL":
			return "10";
		case "ACC":
			return "11";
		case "ACA":
			return "12";
		case "FTA":
			return "14";
		case "OTHERS":
			return "16";
		case "Polluted":
			return "17";
		case "Vandal prone":
			return "18";
		case "Theft prone":
			return "19";
		case "Kite flying zone":
			return "20";
		case "Bird Nesting":
			return "21";
		case "Anchored Feeder":
			return "22";
		case "Out of Plumb":
			return "23";
		case "Vulnerable Foundation":
			return "24";
		default:
			throw new Exception("Invalid type of location : " + typeOfLocation);
		}
	}
	
public String getFoundationDetail(String fdnType) throws Exception {
		
		switch (fdnType) {
		case "ANBC-4":
			return "Dinemsion in Meters -A1:1.5 A2:1 B1:1.5 B2:1   H: H2:0.5 H3:2.5 Volume in CU.M. -3.353 Volume in CU.M. with BWA-3.353";
		case "ANBC-5":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "ANBC-6":
			return "Dinemsion in Meters -A1:1.4 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.613 Volume in CU.M. with BWA-3.613";
		case "ANBC-7":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:2.2 B2:1   H: H2:0.6 H3:2.15 Volume in CU.M. -3.719 Volume in CU.M. with BWA-3.719";
		case "ANBC-8":
			return "Dinemsion in Meters -A1:1.3 A2:1.2 B1:2.2 B2:1   H: H2:0.6 H3:2.5 Volume in CU.M. -4.332 Volume in CU.M. with BWA-4.332";
		case "ANBC-9":
			return "Dinemsion in Meters -A1:1.5 A2:1.4 B1:2.2 B2:1   H: H2:0.6 H3:2.15 Volume in CU.M. -5.035 Volume in CU.M. with BWA-5.035";
		case "B SPL-1":
			return "Dinemsion in Meters -A1:0.8  B1:2.9  C:0.6  H1:2.1   Volume in CU.M. -4.785 Volume in CU.M. with BWA-4.785";
		case "B SPL-2":
			return "Dinemsion in Meters -A1:0.8  B1:2.9  C:0.6  H1:2.3   Volume in CU.M. -5.249 Volume in CU.M. with BWA-5.249";
		case "B SPL-3":
			return "Dinemsion in Meters -A1:0.8  B1:3.1  C:0.6  H1:2.3   Volume in CU.M. -5.611 Volume in CU.M. with BWA-5.611";
		case "B-0":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-01":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-1":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-10":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-11":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-12":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-13":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-2":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-3":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-4":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-5":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-6":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-7":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-8":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "B-9":
			return "Dinemsion in Meters -A1:1.2 A2:1 B1:1.9 B2:1   H: H2:0.45 H3:2.3 Volume in CU.M. -3.48 Volume in CU.M. with BWA-3.48";
		case "BC-0":
			return "Dinemsion in Meters -A1:1.2 A2:1.4 B1:0.8 B2:1   H1:2.5 H2:0.3  Volume in CU.M. -2.82 Volume in CU.M. with BWA-2.82";
		default:
			throw new Exception("Invalid fdn type. : " + fdnType);
		}
		}
		
		public String getMastCode(String typeOfMast) throws Exception {
			
			switch (typeOfMast) {
			case "BFB 6X6":
				return "01";
			case "BFB 8X8":
				return "02";
			case "RSJ 8X6":
				return "03";
			case "K 100":
				return "04";
			case "K 125":
				return "05";
			case "K 150":
				return "06";
			case "K 175":
				return "07";
			case "K 200":
				return "08";
			case "K 225":
				return "09";
			case "K 250":
				return "10";
			case "M 4":
				return "11";
			case "M 5":
				return "12";
			case "M 6":
				return "13";
			case "M 7":
				return "14";
			case "M 8":
				return "15";
			case "M 9":
				return "16";
			case "B 150":
				return "17";
			case "B 175":
				return "18";
			case "B 200":
				return "19";
			case "TTC":
				return "20";
			case "OTHERS":
				return "21";
			case "Anchored Mast":
				return "22";
			case "Bridge Mast":
				return "23";
			case "S-1":
				return "24";
			case "S-6":
				return "25";
			case "T-150":
				return "26";
			case "B-225":
				return "27";
			case "B-250":
				return "28";
			default :
				throw new Exception("Wrong mast : " + typeOfMast);
		}
			
		
		}
		
		public String getPortalOrTTCOrHEadSpanCode(String type) throws Exception {
			
			switch (type) {
			case "N TYPE":
				return "01";
			case "O TYPE":
				return "02";
			case "G TYPE":
				return "03";
			case "P TYPE":
				return "04";
			case "R TYPE":
				return "05";
			case "BFB TYPE":
				return "06";
			case "SPL TYPE":
				return "07";
			case "FABRICATED TYPE":
				return "08";
			default :
				throw new Exception("Wrong portal or TTC or Headspan type : " + type);
			}
		}
		
		public String getCautionBoardCode(String cautionBoard) throws Exception {
			
			switch (cautionBoard) {
			case " Distance - 500 m":
				return "01";
			case " Distance - 250 m":
				return "02";
			case " Switch ON Power":
				return "03";
			case " Switch OFF Power":
				return "04";
			case " Lower Pantograph":
				return "05";
			case " Raise Pantograph":
				return "06";
			case " Unwired Turnouts":
				return "07";
			case " Electric Engine Stops":
				return "08";
			case " Power Block Works Limit":
				return "09";
			case " Restricted Clearance":
				return "10";
			case " Caution 25000":
				return "11";
			case " Σ Board":
				return "12";
			default :
				throw new Exception("Wrong caution board type : " + cautionBoard);
			}
		}
		
		public String getAllOHELoopLineCodeForStation(String station) throws IOException {
			String path = System.getProperty("user.dir");
			Document doc = Jsoup.parse(new File(path + "\\src\\main\\java\\com\\bezkoder\\spring\\files\\csv\\helper\\" + "stationLoopLineCode.html"), "UTF-8");
			
			Elements lists = doc.select("[id=" + station + "]").select("option");
			List<String> loopLineCodes = new ArrayList<String>();
		    
			if( !lists.isEmpty() )
			{
				for (Element e : lists) {
					loopLineCodes.add(e.select("option").attr("value"));
				}
			}
		    return String.join(";", loopLineCodes);
		}
		
		public String getOheLineCode(String line) throws IOException {
			String path = System.getProperty("user.dir");
			Document doc = Jsoup.parse(new File(path + "\\src\\main\\java\\com\\bezkoder\\spring\\files\\csv\\helper\\" + "stationLoopLineCode.html"), "UTF-8");
			String result = searchAttribute(doc, "oheline", line);
			return result;// result = 1
		}
		
		public String getOheLoopLineCode(String station, String loopLine) throws IOException {
			String path = System.getProperty("user.dir");
			Document doc = Jsoup.parse(new File(path + "\\src\\main\\java\\com\\bezkoder\\spring\\files\\csv\\helper\\" + "stationLoopLineCode.html"), "UTF-8");
			String result = searchAttribute(doc, station, loopLine);
			return result;// result = 1
		}
		
		private static String searchAttribute(Element element, String station, String loopLine)
		{
		    Elements lists = element.select("[id=" + station + "]");

		    for( Element e : lists )
		    {
		        Elements result = e.select("option:contains(" + loopLine + ")");

		        if( !result.isEmpty() )
		        {
		            return result.first().attr("value");
		        }
		    }

		    return null;
		}
		
		public List<String> getSCEAssetID() throws IOException {
			String path = System.getProperty("user.dir");
			Document doc = Jsoup.parse(new File(path + "\\src\\main\\java\\com\\bezkoder\\spring\\files\\csv\\helper\\" + "assetID.html"), "UTF-8");
			
			LinkedList<Integer> unsortedAssetIdList = new LinkedList<>();
			List<String> sortedAssetIdList = new LinkedList<>();
			Element table = doc.select("table").get(0); //select the first table.
			Elements rows = table.select("tbody").select("tr");

			for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
			    Element row = rows.get(i);
			    Elements cols = row.select("td");

			    for(Node node : cols.get(1).childNodes()) {
			    	if(node instanceof Element) {
			    		String onclick = node.attr("onclick");
			    		System.out.println(onclick);
			    		onclick = StringUtils.substringAfter(onclick, "popUpMastDetail('");
			    		onclick = StringUtils.substringBefore(onclick, "',3)");
			    		unsortedAssetIdList.add(Integer.parseInt(onclick));
			    	}
			    }
			}
			Comparator<Integer> order = Integer::compare;
			unsortedAssetIdList.sort(order);
			sortedAssetIdList = unsortedAssetIdList.stream().map(Object::toString).collect(Collectors.toList());
            System.out.println(sortedAssetIdList);
			return sortedAssetIdList;
			
		}
		
}
