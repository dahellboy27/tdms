package com.bezkoder.spring.files.csv.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.csv.service.CSVService;
import com.bezkoder.spring.files.csv.service.RestService;
import com.bezkoder.spring.files.csv.helper.CSVHelper;
import com.bezkoder.spring.files.csv.message.ResponseMessage;
import com.bezkoder.spring.files.csv.model.MastDetails;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

  @Autowired
  CSVService fileService;
  
  @Autowired
  RestService restService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    String message = "";
    System.out.println(System.getProperty("user.dir"));
    if (CSVHelper.hasCSVFormat(file)) {
      try {
    	  restService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
    	  throw e;
			/*
			 * message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			 * return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
			 * ResponseMessage(message));
			 */
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }
  
	/*
	 * @GetMapping("/testUpload") public ResponseEntity<String> testUploadFile()
	 * throws Exception {
	 * 
	 * ResponseEntity<String> response = restService.uploadData();
	 * System.out.println(response.getBody());
	 * System.out.println(response.getStatusCode()); return response; }
	 */

  @GetMapping("/tutorials")
  public ResponseEntity<List<MastDetails>> getAllTutorials() {
    try {
      List<MastDetails> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> getFile() {
    String filename = "tutorials.csv";
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
  
  @GetMapping("/login")
  public ResponseEntity<String> tdmsLogin() throws URISyntaxException, IOException {
    
	ResponseEntity<String> response = restService.tdmsLogin2();
	System.out.println(response.getBody());
    return response;
  }
  
  @GetMapping("/testLogin")
  public ResponseEntity<String> testLogin() throws URISyntaxException {
    
	ResponseEntity<String> response = restService.getData();
	System.out.println(response.getBody());
    return response;
  }
  

}
