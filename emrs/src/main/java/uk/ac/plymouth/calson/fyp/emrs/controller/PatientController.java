package uk.ac.plymouth.calson.fyp.emrs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import uk.ac.plymouth.calson.fyp.emrs.model.Patient;
import uk.ac.plymouth.calson.fyp.emrs.repository.PatientRepository;

@Controller
public class PatientController
{
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class.getName());
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/patient/list")
	public String patientList(Model model)
	{
		logger.info("Go to patient list page!");
		List<Patient> patientList = patientRepository.findAll();
		model.addAttribute("patientList", patientList);
		return "patient/list";
	}
	
	@PostMapping("/patient/create")
	public String createPatient(Model model, Patient patient)
	{
		patient = patientRepository.save(patient);
		logger.info("Create patient=[name={}, sex={}, hkid={}], patient ID is {}", patient.getName(), patient.getSex(), patient.getHkid(), patient.getId());
		
		List<Patient> patientList = patientRepository.findAll();
		model.addAttribute("patientList", patientList);
		return "patient/list";
	}
}
