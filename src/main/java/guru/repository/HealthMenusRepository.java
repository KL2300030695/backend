package guru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.model.HealthMenus;


@Repository
public interface HealthMenusRepository extends JpaRepository<HealthMenus, Long>{
	@Query("select M from HealthMenus M join HealthRoles R on M.mid=R.menus.mid where R.role=:role")
	public List<HealthMenus> findByrole(@Param("role") int role);

}
