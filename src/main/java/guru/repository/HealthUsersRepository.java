package guru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import guru.model.HealthUser;

public interface HealthUsersRepository extends JpaRepository<HealthUser,String> {
	@Query("select count(U) from HealthUser U where U.email=:email") 
	 public int validateEmail(@Param("email") String email);
	@Query("select count(U) from HealthUser U where U.email=:email AND U.password=:password")
	 public int CheckCredentials(@Param("email") String email,@Param("password") String password);

}
