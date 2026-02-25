package com.app.dto.department;

import java.util.ArrayList;
import java.util.List;

import com.app.dto.user.User;

import lombok.Data;

@Data
public class DepartmentTreeNode {
    // Department 기본정보
    int id;
    int deptno;
    String name;
    Integer parentDepartmentId;

    // 트리/직원
    List<DepartmentTreeNode> children = new ArrayList<>();
    List<User> users = new ArrayList<>();

    // 옵션: 빠른 표시용
    int userCount;
}