package guru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.model.Doctor;
import guru.model.DoctorManager;


@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {
	@Autowired
	DoctorManager dm;
	@PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor) {
        return dm.addDoctor(doctor);
    }
	
	@GetMapping("/list")
	public List<Doctor> getDoctorList() {
	    return dm.getAllDoctors();
	}
	
	@GetMapping("/update/{id}")
	public Doctor getDoctorById(@PathVariable("id") Long id) {
	    return dm.getDoctorById(id);
	}
	@DeleteMapping("/delete/{id}")
	public String deletedoctor(@PathVariable("id") Long id) {
		return dm.deletedoctordetails(id);
	}


}
