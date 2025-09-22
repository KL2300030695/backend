package guru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.model.AppointmentManager;
import guru.model.Doctor;
import guru.model.HealthBookAppointment;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins="*")
public class HealthAppointmentController {
	@Autowired
    AppointmentManager appointmentManager;

	@PostMapping("/create")
	public ResponseEntity<String> createAppointment(@RequestBody HealthBookAppointment appointment) {
        String result = appointmentManager.createAppointment(appointment);
        return ResponseEntity.ok(result);
    }
	
	@GetMapping("/list")
	public List<HealthBookAppointment> getAppointmentList() {
	    return appointmentManager.getAllAppointments();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteappointment(@PathVariable("id") Long id) {
		return appointmentManager.deleteAppointmentdetails(id);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAppointment(
	        @PathVariable Long id,
	        @RequestBody HealthBookAppointment updatedAppointment) {

	    appointmentManager.updateAppointment(id, updatedAppointment);
	    return ResponseEntity.ok("{\"status\":\"success\"}");
	}

	

}
