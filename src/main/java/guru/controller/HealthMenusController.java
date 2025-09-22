package guru.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.model.HealthMenusManager;


@RestController
@RequestMapping("/menus")
@CrossOrigin(origins="*")
public class HealthMenusController {
	@Autowired
	HealthMenusManager MM;
	@PostMapping("/getmenus")
	public String getMenuList() {
		return MM.getMenus();
	}
	@PostMapping("/getmenusbyrole")
	public String getMenusByRole(@RequestBody Map<String,String> data) 
	{
		return MM.getMenusByRole(data.get("crsid"));
		
	}

}
