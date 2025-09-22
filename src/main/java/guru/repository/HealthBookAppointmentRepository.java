package guru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.model.HealthBookAppointment;
@Repository
public interface HealthBookAppointmentRepository extends JpaRepository<HealthBookAppointment, Long>{
	
}
