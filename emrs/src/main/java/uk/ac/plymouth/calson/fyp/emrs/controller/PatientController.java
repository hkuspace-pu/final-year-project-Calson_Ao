package uk.ac.plymouth.calson.fyp.emrs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uk.ac.plymouth.calson.fyp.emrs.model.BlockchainSummary;
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
		List<BlockchainSummary> patientList = patientBlockChainService.findAllPatient();
		model.addAttribute("patientList", patientList);
		return "patient/list";
	}
	
	@PostMapping("/patient/create")
	public String createPatient(Model model, PatientBlock patientBlock)
	{
		logger.info("Create patient: {}", patientBlock.getPatientInfo());
		patientBlockChainService.createPatientBlock(patientBlock);
		logger.info("Total number of patients: {}", patientBlockChainService.countPatients());
		
		List<BlockchainSummary> patientList = patientBlockChainService.findAllPatient();
		model.addAttribute("patientList", patientList);
		return "patient/list";
	}
	
	@GetMapping("/patient/{hkid}/detail")
	public String viewPatientDetail(@PathVariable String hkid, Model model)
	{
		logger.info("Edit patient {}!", hkid);
		PatientBlock patientBlock = patientBlockChainService.getPatientLatestBlock(hkid);
		model.addAttribute("patientBlock", patientBlock);
		return "patient/edit";
	}
	
	@PostMapping("/patient/{hkid}/update")
	public String updatePatient(Model model, PatientBlock patientBlock)
	{
		String hkid = patientBlock.getHkid();
		logger.info("Update patient[hkid={}], data=[{}]", hkid, patientBlock.toString());
		patientBlockChainService.updatePatientBlock(patientBlock);
		
		// go back to edit page
		patientBlock = patientBlockChainService.getPatientLatestBlock(hkid);
		model.addAttribute("patientBlock", patientBlock);
		return "patient/edit";
	}
	
	@GetMapping("/patientchain/{hkid}")
	public String viewPatientBlockChain(@PathVariable String hkid, Model model)
	{
		logger.info("View full blockchain of patient {}!", hkid);
		List<PatientBlock> fullChain = patientBlockChainService.getBlockChain(hkid);
		model.addAttribute("patientHKID", hkid);
		model.addAttribute("fullChain", fullChain);
		return "patient/blockchain";
	}
	
	@GetMapping("/patientblock/{hkid}/{blockIndex}")
	public String viewPatientBlock(@PathVariable String hkid, @PathVariable Integer blockIndex, Model model)
	{
		logger.info("View block {} of patient {}!", blockIndex, hkid);
		List<PatientBlock> fullChain = patientBlockChainService.getBlockChain(hkid);
		PatientBlock targetBlock = fullChain.get(blockIndex);
		model.addAttribute("patientHKID", hkid);
		model.addAttribute("blockIndex", blockIndex);
		model.addAttribute("targetBlock", targetBlock);
		return "patient/targetBlock";
	}
	
	@PostMapping("/hackPatient/{hkid}/{blockIndex}")
	public String hackPatient(@PathVariable String hkid, @PathVariable Integer blockIndex, Model model, PatientBlock hackerBlock)
	{
		logger.info("Hack patient[hkid={}] - block {}", hkid, blockIndex);
		List<PatientBlock> fullChain = patientBlockChainService.getBlockChain(hkid);
		PatientBlock targetBlock = fullChain.get(blockIndex);
		targetBlock.setHkid(hackerBlock.getHkid());
		targetBlock.setName(hackerBlock.getName());
		targetBlock.setSex(hackerBlock.getSex());
		targetBlock.setConsultationData(hackerBlock.getConsultationData());
		model.addAttribute("patientHKID", hkid);
		model.addAttribute("fullChain", fullChain);
		return "patient/blockchain";
	}
}
