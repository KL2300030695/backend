package guru.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.repository.HealthBookAppointmentRepository;
import guru.repository.HealthDoctorRepository;
import jakarta.transaction.Transactional;


@Service
public class DoctorManager {
	@Autowired
	HealthDoctorRepository dr;
	@Autowired
	HealthBookAppointmentRepository br;
	public String addDoctor(Doctor doctor) {
        try {
            dr.save(doctor);  // Save the doctor to the database
            return "200 :: Doctor added successfully";
        } catch (Exception e) {
            return "401 :: Error adding doctor: " + e.getMessage();
        }
    }
	
	public List<Doctor> getAllDoctors() {
	    return dr.findAll();
	}
	
	public Doctor getDoctorById(Long id) {
	    return dr.findById(id).orElse(null);
	}

	
	public String deletedoctordetails(Long id) {
		try 
		{
	        dr.deleteById(id);
	        return "200::Job deleted successfully";
	    } catch (Exception e) {
	        return "401::" + e.getMessage();
	    }
	}


}
