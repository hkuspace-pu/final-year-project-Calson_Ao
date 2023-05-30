package uk.ac.plymouth.calson.fyp.emrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.plymouth.calson.fyp.emrs.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>
{
	List<Patient> findAll();
	List<Patient> findByName(String name);
	List<Patient> findBySex(String sex);
	List<Patient> findByHkid(String hkid);
}
