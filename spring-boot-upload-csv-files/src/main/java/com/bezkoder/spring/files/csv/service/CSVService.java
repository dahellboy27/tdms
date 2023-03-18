package com.bezkoder.spring.files.csv.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.csv.helper.CSVHelper;
import com.bezkoder.spring.files.csv.model.MastDetails;
//import com.bezkoder.spring.files.csv.repository.TutorialRepository;

@Service
public class CSVService {
	/* @Autowired */
  //TutorialRepository repository;


	
	  public ByteArrayInputStream load() { 
		  //List<Tutorial> tutorials =
	  //repository.findAll();
	  
	  ByteArrayInputStream in = new ByteArrayInputStream(null); 
			  //CSVHelper.tutorialsToCSV(tutorials); 
	  return in; }
	 

  public List<MastDetails> getAllTutorials() {
    return null;
  }
}
