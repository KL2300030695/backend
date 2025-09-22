package guru.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import guru.repository.HealthBookAppointmentRepository;
import guru.repository.HealthDoctorRepository;


@Service
public class AppointmentManager {
    
    @Autowired
    private HealthBookAppointmentRepository healthBookAppointmentRepository;

    @Autowired
    private HealthDoctorRepository doctorRepository; // Added to fetch doctor by ID
    
    @Autowired
    private HealthEmailManager emailManager;

    // Method to create a HealthBookAppointment
    public String createAppointment(HealthBookAppointment healthBookAppointment) {
        try {
            // Check if doctor exists
            if (healthBookAppointment.getDoctor() != null) {
                Doctor doctor = doctorRepository.findById(healthBookAppointment.getDoctor().getId()).orElse(null);
                if (doctor == null) {
                    return "401::Doctor does not exist";
                }
                healthBookAppointment.setDoctor(doctor); // Assign existing doctor
            }

            // Save the appointment
            healthBookAppointmentRepository.save(healthBookAppointment);

            // Now send the email
            String toMail = healthBookAppointment.getEmail();
            String subject = "Appointment Confirmation";

            String message = "Dear " + healthBookAppointment.getFullName() + ",\n\n"
                    + "Your appointment is successfully booked on " + healthBookAppointment.getAppointmentDate()
                    + " at " + healthBookAppointment.getAppointmentTime() + ".\n\n"
                    + "Doctor: Dr. " + healthBookAppointment.getDoctor().getFullname() + "\n"
                    + "Contact: " + healthBookAppointment.getDoctor().getPhone() + "\n\n"
                    + "Regards,\n"
                    + "HealthCare Team";

            emailManager.tOMail(toMail, subject, message);

            return "200 :: HealthBookAppointment created successfully";
        } catch (Exception e) {
            return "401::" + e.getMessage();
        }
    }

    // Method to update a HealthBookAppointment
    public String updateAppointment(Long id, String newFullName, String newStatus, Long doctorId) {
        try {
            // Check if the appointment exists
            if (!healthBookAppointmentRepository.existsById(id)) {
                return "404:: Appointment not found";
            }

            // Fetch the existing appointment
            HealthBookAppointment appointment = healthBookAppointmentRepository.findById(id).orElse(null);

            if (appointment != null) {
                // Check if the doctor exists before setting
                if (doctorId != null) {
                    Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                    if (doctor == null) {
                        return "401::Doctor does not exist";
                    }
                    appointment.setDoctor(doctor); // Assign the doctor to the appointment
                }

                // Make changes to the fetched appointment
                appointment.setFullName(newFullName);
                appointment.setStatus(newStatus);

                // Save (update) the appointment
                healthBookAppointmentRepository.save(appointment);

                return "200 :: HealthBookAppointment updated successfully";
            } else {
                return "404:: Appointment not found";
            }
        } catch (Exception e) {
            return "401::" + e.getMessage();
        }
    }

    public List<HealthBookAppointment> getAllAppointments() {
        return healthBookAppointmentRepository.findAll();
    }
    
    public String deleteAppointmentdetails(Long id) {
		try 
		{
			healthBookAppointmentRepository.deleteById(id);
	        return "200::Job deleted successfully";
	    } catch (Exception e) {
	        return "401::" + e.getMessage();
	    }
	}
    
    public void updateAppointment(Long id, HealthBookAppointment updatedAppointment) {
        HealthBookAppointment existing = healthBookAppointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

        existing.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existing.setAppointmentTime(updatedAppointment.getAppointmentTime());
        existing.setDepartment(updatedAppointment.getDepartment());
        existing.setDiseases(updatedAppointment.getDiseases());
        existing.setStatus("Scheduled"); // Or keep existing.getStatus() if needed

        // Optional: Update doctor if doctorId is coming
        if (updatedAppointment.getDoctor() != null && updatedAppointment.getDoctor().getId() != null) {
            Doctor doctor = doctorRepository.findById(updatedAppointment.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
            existing.setDoctor(doctor);
        }

        healthBookAppointmentRepository.save(existing);
    }
    
    


}
