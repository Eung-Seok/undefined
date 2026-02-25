package com.app.service.department.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.department.DepartmentDAO;
import com.app.dao.user.UserDAO;
import com.app.dto.department.Department;
import com.app.dto.department.DepartmentTreeNode;
import com.app.dto.user.User;
import com.app.service.department.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDAO departmentDao;
	
	@Autowired
	UserDAO userDao;

	@Override
	public List<Department> findDepartmentList() {
		List<Department> departmentList = departmentDao.findDepartmentList();
		return departmentList;
	}
	
	@Override
	public int saveDepartment(Department department) {
		int result = departmentDao.saveDepartment(department);
		return result;
	}

	@Override
	public Department findDepartmentById(int id) {
		Department department = departmentDao.findDepartmentById(id);
		return department;
	}

	@Override
	public int removeDepartment(int id) {
		int result = departmentDao.removeDepartment(id);
		return result;
	}

	@Override
	public int modifyDepartment(Department department) {
		int result = departmentDao.modifyDepartment(department);
		return result;
	}

	@Override
    public List<DepartmentTreeNode> findDepartmentTree(boolean includeUsers) {
        List<Department> depts = departmentDao.findDepartmentList();
        if (depts == null || depts.isEmpty()) return Collections.emptyList();

        // 1) Department -> Node 변환 + id 맵
        Map<Integer, DepartmentTreeNode> nodeById = new HashMap<>();
        for (Department d : depts) {
            DepartmentTreeNode n = new DepartmentTreeNode();
            n.setId(d.getId());
            n.setDeptno(d.getDeptno());
            n.setName(d.getName());
            n.setParentDepartmentId(d.getParentDepartmentId());
            nodeById.put(n.getId(), n);
        }

        // 2) 부모-자식 연결 + 루트 수집
        List<DepartmentTreeNode> roots = new ArrayList<>();
        for (DepartmentTreeNode n : nodeById.values()) {
            Integer pid = n.getParentDepartmentId();
            if (pid == null) {
                roots.add(n);
            } else {
                DepartmentTreeNode parent = nodeById.get(pid);
                if (parent == null) {
                    // 부모가 없는 데이터(깨진 참조)면 루트로 처리
                    roots.add(n);
                } else {
                    parent.getChildren().add(n);
                }
            }
        }

        // 3) 직원 포함 옵션
        if (includeUsers) {
            List<Integer> deptnoList = depts.stream()
                    .map(Department::getDeptno)
                    .distinct()
                    .collect(Collectors.toList());

            List<User> users = userDao.findUsersByDeptnoList(deptnoList);

            // deptno -> users
            Map<Integer, List<User>> usersByDeptno = new HashMap<>();
            for (User u : users) {
                usersByDeptno.computeIfAbsent(u.getDeptno(), k -> new ArrayList<>()).add(u);
            }

            // 각 노드에 붙이기 + userCount
            for (DepartmentTreeNode n : nodeById.values()) {
                List<User> list = usersByDeptno.getOrDefault(n.getDeptno(), Collections.emptyList());
                n.setUsers(list);
                n.setUserCount(list.size());
            }
        } else {
            for (DepartmentTreeNode n : nodeById.values()) {
                n.setUserCount(0);
            }
        }

        // 4) 보기 좋게 정렬(옵션): deptno 오름차순
        sortTree(roots);

        return roots;
    }

    private void sortTree(List<DepartmentTreeNode> nodes) {
        nodes.sort(Comparator.comparingInt(DepartmentTreeNode::getDeptno));
        for (DepartmentTreeNode n : nodes) {
            if (n.getChildren() != null && !n.getChildren().isEmpty()) {
                sortTree(n.getChildren());
            }
        }
    }
}
