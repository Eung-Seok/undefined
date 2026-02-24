package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.department.DepartmentTreeNode;
import com.app.dto.role.Role;
import com.app.dto.user.User;
import com.app.dto.userRole.UserRole;
import com.app.service.department.DepartmentService;
import com.app.service.role.RoleService;
import com.app.service.user.UserService;
import com.app.service.userRole.UserRoleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	RoleService roleService;
	@Autowired
	DepartmentService departmentService;
	
    @GetMapping("/users") public String users(Model model){
    	
    	List<User> userList = userService.findUserList();
    	List<UserRole> userRoleList = userRoleService.findUserRoleList();
    	Map<Integer, String> userMap = new HashMap<>();
    	
    	for(UserRole u: userRoleList) {
    		userMap.put(u.getUserId(), roleService.findRoleById(u.getRoleId()).getName());
    	}
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("userMap", userMap);
    	
    	
    	return "admin/users"; 
    }
    @GetMapping("/roles") public String roles(){ return "admin/roles"; }
    
    @ResponseBody
    @GetMapping("/departments/tree") public  List<DepartmentTreeNode> tree(
            @RequestParam(defaultValue = "true") boolean includeUsers
    ) {
        return departmentService.findDepartmentTree(includeUsers);
    }
    
    @GetMapping("/org")
    public String orgPage() {
    	return "admin/org";
    }
    @GetMapping("/system") public String system(){ return "admin/system"; }
}
