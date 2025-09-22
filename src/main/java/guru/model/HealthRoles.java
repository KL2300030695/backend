package guru.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="healthroles")
public class HealthRoles {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	@Column(name="role")
	int role;
	@ManyToOne
	@JoinColumn(name="mid")
	HealthMenus menus;
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public int getRole() 
	{
		return role;
	}
	public void setRole(int role) 
	{
		this.role = role;
	}
	public HealthMenus getMenus() 
	{
		return menus;
	}
	public void setMenus(HealthMenus menus) 
	{
		this.menus = menus;
	}
	

}
