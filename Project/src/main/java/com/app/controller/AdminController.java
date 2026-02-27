package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.department.Department;
import com.app.dto.department.DepartmentTreeNode;
import com.app.dto.role.Role;
import com.app.dto.user.AdminUserUpdate;
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
    	List<Department> departmentList = departmentService.findDepartmentList();
    	Map<Integer, String> userMap = new HashMap<Integer, String>();
    	Map<Integer, String> departmentMap = new HashMap<Integer, String>();
    	
    	for(UserRole u: userRoleList) {
    		userMap.put(u.getUserId(), roleService.findRoleById(u.getRoleId()).getName());
    	}
    	for(Department d: departmentList) {
    		departmentMap.put(d.getDeptno(), d.getName());
    	}
    	
    	model.addAttribute("departmentMap", departmentMap);
    	model.addAttribute("userList", userList);
    	model.addAttribute("userMap", userMap);
    	
    	
    	return "admin/users"; 
    }
    
    @GetMapping("/users/edit")
    public String edit(@RequestParam("empno") int empno, HttpServletRequest request) {
    	
    	 User user = userService.findUserByEmpno(empno);
         List<Department> deptList = departmentService.findDepartmentList(); // deptno, name
         List<Role> roleList = roleService.findRoleList();                  // id, name
         String userRole = roleService.findRoleById(userRoleService.findUserRoleByUserId(empno).getRoleId()).getName();  // ["ADMIN","MEMBER"] 같은 형태

         request.setAttribute("user", user);
         request.setAttribute("deptList", deptList);
         request.setAttribute("roleList", roleList);
         request.setAttribute("userRole", userRole);

         return "admin/user_edit"; // /WEB-INF/views/admin/user_edit.jsp
    }
    
    @PostMapping("/users/update")
    public String update(AdminUserUpdate adminUserUpdate) {
    	userService.updateUserAdmin(adminUserUpdate);
    	return "redirect:/admin/users";
    }
    
    @GetMapping("/users/create")
    public String create(Model model) {
    	 List<Department> deptList = departmentService.findDepartmentList(); // deptno, name
         List<Role> roleList = roleService.findRoleList();   
    	
         model.addAttribute("deptList", deptList);
         model.addAttribute("roleList", roleList);
    	return "admin/user_create";
    }
    
    @PostMapping("/users/create")
    public String createAction(AdminUserUpdate adminUserUpdate) {
    	userService.createUserAdmin(adminUserUpdate);
    	return "redirect:/admin/users";
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
