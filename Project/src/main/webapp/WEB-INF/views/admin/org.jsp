<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if (session.getAttribute("loginUser") == null) {
	java.util.Map<String, Object> u = new java.util.HashMap<>();
	u.put("name", "홍길동");
	u.put("position", "사원");
	u.put("role", "MEMBER");
	session.setAttribute("loginUser", u);
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>조직도 관리</title>
  <style>
    /* ====== Admin Org Tree (Minimal) ====== */
    .admin-org-wrap { max-width: 980px; margin: 24px auto; padding: 0 16px; }
    .admin-org-header { display: flex; align-items: center; justify-content: space-between; gap: 12px; margin-bottom: 16px; }
    .admin-org-title { font-size: 20px; font-weight: 700; }
    .admin-org-actions { display: flex; gap: 8px; align-items: center; }

    .admin-org-input {
      width: 260px;
      padding: 10px 12px;
      border: 1px solid #ddd;
      border-radius: 10px;
      outline: none;
    }
    .admin-org-btn {
      padding: 10px 12px;
      border: 1px solid #ddd;
      background: #fff;
      border-radius: 10px;
      cursor: pointer;
    }
    .admin-org-btn:hover { background: #f7f7f7; }

    .admin-org-card {
      border: 1px solid #e6e6e6;
      border-radius: 14px;
      overflow: hidden;
      background: #fff;
    }

    .admin-org-toolbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 12px 14px;
      border-bottom: 1px solid #eee;
      background: #fafafa;
      font-size: 13px;
      color: #444;
    }

    .admin-org-tree { padding: 14px; }

    /* Tree */
    .admin-org-ul { list-style: none; margin: 0; padding-left: 18px; }
    .admin-org-li { margin: 6px 0; }

    .admin-org-node {
      display: flex;
      align-items: center;
      gap: 8px;
      padding: 8px 10px;
      border: 1px solid #eee;
      border-radius: 12px;
      background: #fff;
    }

    .admin-org-toggle {
      width: 28px; height: 28px;
      border-radius: 8px;
      border: 1px solid #e5e5e5;
      background: #fff;
      cursor: pointer;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      font-size: 14px;
      user-select: none;
    }
    .admin-org-toggle[disabled] { opacity: .4; cursor: default; }

    .admin-org-dept {
      display: flex; flex-direction: column;
      gap: 2px;
      flex: 1;
      min-width: 0;
    }

    .admin-org-dept-name {
      font-weight: 700;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .admin-org-dept-meta { font-size: 12px; color: #666; }
    .admin-org-pill {
      font-size: 12px;
      padding: 4px 8px;
      border-radius: 999px;
      border: 1px solid #e8e8e8;
      background: #fafafa;
      color: #333;
      white-space: nowrap;
    }

    /* Employees list under department */
    .admin-org-emp {
      margin-top: 8px;
      margin-left: 36px;
      border-left: 2px solid #f0f0f0;
      padding-left: 12px;
    }
    .admin-org-emp-item {
      display: flex;
      justify-content: space-between;
      gap: 10px;
      padding: 6px 8px;
      border-radius: 10px;
    }
    .admin-org-emp-item:hover { background: #fafafa; }

    .admin-org-emp-left { display: flex; gap: 8px; min-width: 0; }
    .admin-org-emp-name { font-weight: 600; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
    .admin-org-emp-pos { color: #666; font-size: 12px; white-space: nowrap; }

    .admin-org-emp-right { color: #888; font-size: 12px; white-space: nowrap; }

    .admin-org-muted { color: #888; font-size: 13px; padding: 16px; }
    .admin-org-error { color: #b00020; font-size: 13px; padding: 16px; }
    .admin-org-hidden { display: none !important; }
  </style>
</head>

<body>
<div class="admin-org-wrap">
  <div class="admin-org-header">
    <div class="admin-org-title">조직도(부서/직원)</div>
    <div class="admin-org-actions">
      <input id="adminOrgSearch" class="admin-org-input" placeholder="부서/직원 검색 (예: 개발부, 김부장)" />
      <button id="adminOrgReload" class="admin-org-btn" type="button">새로고침</button>
      <button id="adminOrgCollapseAll" class="admin-org-btn" type="button">전체 접기</button>
      <button id="adminOrgExpandAll" class="admin-org-btn" type="button">전체 펼치기</button>
    </div>
  </div>

  <div class="admin-org-card">
    <div class="admin-org-toolbar">
      <div>데이터: <span id="adminOrgStatus">로딩중…</span></div>
      <div>API: <code>/api/departments/tree?includeUsers=true</code></div>
    </div>
    <div id="adminOrgRoot" class="admin-org-tree">
      <!-- Rendered Here -->
    </div>
  </div>
</div>

<script>
  const adminOrg = {
    data: [],
    expanded: new Set(), // dept id expanded
  };

  const $root = document.getElementById('adminOrgRoot');
  const $status = document.getElementById('adminOrgStatus');
  const $search = document.getElementById('adminOrgSearch');

  document.getElementById('adminOrgReload').addEventListener('click', () => loadOrg());
  document.getElementById('adminOrgCollapseAll').addEventListener('click', () => { adminOrg.expanded.clear(); render(); });
  document.getElementById('adminOrgExpandAll').addEventListener('click', () => expandAll());
  $search.addEventListener('input', () => render());

  async function loadOrg() {
    $status.textContent = '로딩중…';
    $root.innerHTML = '<div class="admin-org-muted">로딩중…</div>';

    try {
      const res = await fetch('<%=request.getContextPath()%>/api/departments/tree?includeUsers=true', {
        headers: { 'Accept': 'application/json' }
      });
      if (!res.ok) throw new Error('HTTP ' + res.status);

      const json = await res.json();
      adminOrg.data = Array.isArray(json) ? json : [];
      // 기본: 루트는 펼쳐두기
      adminOrg.expanded.clear();
      for (const n of adminOrg.data) adminOrg.expanded.add(n.id);

      $status.textContent = '정상';
      render();
    } catch (e) {
      $status.textContent = '오류';
      $root.innerHTML = '<div class="admin-org-error">조직도 로딩 실패: ' + escapeHtml(String(e)) + '</div>';
    }
  }

  function expandAll() {
    adminOrg.expanded.clear();
    walk(adminOrg.data, (n) => adminOrg.expanded.add(n.id));
    render();
  }

  function walk(nodes, fn) {
    for (const n of nodes || []) {
      fn(n);
      if (n.children && n.children.length) walk(n.children, fn);
    }
  }

  function render() {
    const q = ($search.value || '').trim().toLowerCase();
    if (!adminOrg.data.length) {
      $root.innerHTML = '<div class="admin-org-muted">부서 데이터가 없습니다.</div>';
      return;
    }

    const ul = document.createElement('ul');
    ul.className = 'admin-org-ul';

    for (const node of adminOrg.data) {
      const li = renderNode(node, q);
      if (li) ul.appendChild(li);
    }

    // 검색 결과가 하나도 없으면
    if (!ul.children.length) {
      $root.innerHTML = '<div class="admin-org-muted">검색 결과가 없습니다.</div>';
      return;
    }

    $root.innerHTML = '';
    $root.appendChild(ul);
  }

  function renderNode(node, q) {
    const hasChildren = node.children && node.children.length > 0;
    const hasUsers = node.users && node.users.length > 0;

    const selfMatch = matchDept(node, q) || matchUsers(node, q);

    // 자식 중 매치되는 게 있는지 확인(검색용)
    let childLis = [];
    let anyChildMatch = false;
    if (hasChildren) {
      for (const c of node.children) {
        const childLi = renderNode(c, q);
        if (childLi) {
          anyChildMatch = true;
          childLis.push(childLi);
        }
      }
    }

    // 검색어가 있을 때: 자신도/자식도 매치 없으면 숨김
    if (q && !selfMatch && !anyChildMatch) return null;

    const li = document.createElement('li');
    li.className = 'admin-org-li';

    // Node row
    const row = document.createElement('div');
    row.className = 'admin-org-node';

    const btn = document.createElement('button');
    btn.className = 'admin-org-toggle';
    btn.type = 'button';

    // 펼침 상태: 검색 중이면 매치된 경로는 자동으로 펼쳐서 보이게
    const isExpanded = q ? true : adminOrg.expanded.has(node.id);
    if (!hasChildren && !hasUsers) {
      btn.textContent = '·';
      btn.disabled = true;
    } else {
      btn.textContent = isExpanded ? '−' : '+';
      btn.addEventListener('click', () => {
        if (adminOrg.expanded.has(node.id)) adminOrg.expanded.delete(node.id);
        else adminOrg.expanded.add(node.id);
        render();
      });
    }

    const deptBox = document.createElement('div');
    deptBox.className = 'admin-org-dept';

    const deptName = document.createElement('div');
    deptName.className = 'admin-org-dept-name';
    deptName.textContent = node.name || '(이름없음)';

    const deptMeta = document.createElement('div');
    deptMeta.className = 'admin-org-dept-meta';
    deptMeta.textContent = `DEPTNO ${node.deptno} · ID ${node.id}`;

    deptBox.appendChild(deptName);
    deptBox.appendChild(deptMeta);

    const pill = document.createElement('div');
    pill.className = 'admin-org-pill';
    pill.textContent = `인원 ${node.userCount ?? (node.users ? node.users.length : 0)}명`;

    row.appendChild(btn);
    row.appendChild(deptBox);
    row.appendChild(pill);

    li.appendChild(row);

    // Expanded content
    if (isExpanded) {
      // users
      if (hasUsers) {
        const emp = document.createElement('div');
        emp.className = 'admin-org-emp';

        for (const u of node.users) {
          // 검색어 있을 때는 직원도 필터
          if (q && !matchUser(u, q) && !matchDept(node, q)) continue;

          const item = document.createElement('div');
          item.className = 'admin-org-emp-item';

          const left = document.createElement('div');
          left.className = 'admin-org-emp-left';

          const uname = document.createElement('div');
          uname.className = 'admin-org-emp-name';
          uname.textContent = u.name || '(이름없음)';

          const upos = document.createElement('div');
          upos.className = 'admin-org-emp-pos';
          upos.textContent = u.position ? `(${u.position})` : '';

          left.appendChild(uname);
          left.appendChild(upos);

          const right = document.createElement('div');
          right.className = 'admin-org-emp-right';
          right.textContent = u.empno ? `EMPNO ${u.empno}` : '';

          item.appendChild(left);
          item.appendChild(right);
          emp.appendChild(item);
        }

        li.appendChild(emp);
      }

      // children
      if (hasChildren) {
        const childUl = document.createElement('ul');
        childUl.className = 'admin-org-ul';
        for (const childLi of childLis) childUl.appendChild(childLi);
        li.appendChild(childUl);
      }
    }

    return li;
  }

  function matchDept(node, q) {
    if (!q) return true;
    const s = ((node.name || '') + ' ' + (node.deptno ?? '') + ' ' + (node.id ?? '')).toLowerCase();
    return s.includes(q);
  }

  function matchUsers(node, q) {
    if (!q) return true;
    for (const u of (node.users || [])) {
      if (matchUser(u, q)) return true;
    }
    return false;
  }

  function matchUser(u, q) {
    const s = ((u.name || '') + ' ' + (u.position || '') + ' ' + (u.email || '') + ' ' + (u.empno ?? '')).toLowerCase();
    return s.includes(q);
  }

  function escapeHtml(str) {
    return str.replace(/[&<>"']/g, (m) => ({
      '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    }[m]));
  }

  // initial load
  loadOrg();
</script>
</body>
</html>