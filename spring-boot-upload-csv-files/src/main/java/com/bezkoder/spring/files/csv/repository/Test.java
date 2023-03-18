package com.bezkoder.spring.files.csv.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;

public class Test {

	public static void main(String[] args) {
		
		HttpHeaders headers = stringToHttpHeaders("Cookie_TDMS=ADROTCgiTQro6f86KbDReg$$; JSESSIONID=\"4JDtJ0oY88CfXMppRU4J7e7Le0oOnHtPw8DxQZLb.master:server-two\"; TS013cc5b6=01ea7166bc935e46fd86f51a93c4405801ff7f627078e2d288fef6c226044ccb4039aa88502b505e65a5cf1b7e81da70b46e2994cf3a4f8f88266d45c07e5d3c9c55c309e87cce858296c309eb98d5b21061d53c6c009bbd4013d69c892ceac8e7854a90bf; _ga=GA1.3.597427796.1675432819; _gid=GA1.3.1887741203.1675432819; railsaver=405294346.20480.0000; TS0166d8b7=01ea7166bca4d5f1109f1487bc13086392cef885735422865ca03ef13c1675a0906115a82ba386df0de1e441b7f7454e236c93a7205ad052a7ab603cb5eef5e2323585d8a1; _gat_gtag_UA_180576841_1=1");
		
		System.out.println(headers.toString());

	}
	
	private static HttpHeaders stringToHttpHeaders(final String headerContents) {
	    HttpHeaders httpHeaders = new HttpHeaders();
	    final String[] tempHeaderArray = headerContents.split(";");
	    int i = 0;
	    List<String> cookies = new ArrayList<String>();
	    while (i + 1 <= tempHeaderArray.length) {
	    	cookies.add(tempHeaderArray[i++]);
	    	
	    }
	    return httpHeaders;
	}

}
