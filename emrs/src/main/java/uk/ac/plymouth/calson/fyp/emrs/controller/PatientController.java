package uk.ac.plymouth.calson.fyp.emrs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import uk.ac.plymouth.calson.fyp.emrs.model.PatientBlock;
import uk.ac.plymouth.calson.fyp.emrs.service.PatientBlockChainService;

@Controller
public class PatientController
{
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class.getName());
	
	@Autowired
	private PatientBlockChainService patientBlockChainService;
	
	@GetMapping("/patient/list")
	public String patientList(Model model)
	{
		logger.info("Go to patient list page!");
		List<String> patientHkidList = patientBlockChainService.findAllPatient();
		model.addAttribute("patientHkidList", patientHkidList);
		return "patient/list";
	}
	
	@PostMapping("/patient/create")
	public String createPatient(Model model, PatientBlock patientBlock)
	{
		logger.info("Create patient: {}", patientBlock.getPatientInfo());
		
		List<String> patientHkidList = patientBlockChainService.findAllPatient();
		model.addAttribute("patientHkidList", patientHkidList);
		return "patient/list";
	}
}
