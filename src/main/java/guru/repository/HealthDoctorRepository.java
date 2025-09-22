package guru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.model.Doctor;

@Repository
public interface HealthDoctorRepository extends JpaRepository<Doctor, Long>{

}
