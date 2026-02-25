document.addEventListener('DOMContentLoaded', () => {
  const API_URL = (window.APP_CTX || '') + 'employees/tree?includeUsers=true';

  const adminOrg = {
    data: [],
    expanded: new Set(),
  };

  const $root = document.getElementById('adminOrgRoot');
  const $status = document.getElementById('adminOrgStatus');
  const $search = document.getElementById('adminOrgSearch');

  const $apiLabel = document.getElementById('adminOrgApiLabel');
  const $reload = document.getElementById('adminOrgReload');
  const $collapseAll = document.getElementById('adminOrgCollapseAll');
  const $expandAll = document.getElementById('adminOrgExpandAll');

  // DOM 요소 없으면 조용히 중단(레이아웃/페이지 혼용 대비)
  if (!$root || !$status || !$search || !$reload || !$collapseAll || !$expandAll) {
    console.error('[ORG] required DOM not found');
    return;
  }

  if ($apiLabel) $apiLabel.textContent = API_URL;

  $reload.addEventListener('click', () => loadOrg());
  $collapseAll.addEventListener('click', () => { adminOrg.expanded.clear(); render(); });
  $expandAll.addEventListener('click', () => expandAll());
  $search.addEventListener('input', () => render());

  async function loadOrg() {
    $status.textContent = '로딩중…';
    $root.innerHTML = '<div class="admin-org-muted">로딩중…</div>';

    try {
      const res = await fetch(API_URL, { headers: { 'Accept': 'application/json' } });
      if (!res.ok) throw new Error('HTTP ' + res.status);

      const json = await res.json();
      adminOrg.data = Array.isArray(json) ? json : [];

      adminOrg.expanded.clear();
      for (const n of adminOrg.data) adminOrg.expanded.add(n.id);

      $status.textContent = '정상';
      render();
    } catch (e) {
      console.error('[ORG] loadOrg error', e);
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

    if (q && !selfMatch && !anyChildMatch) return null;

    const li = document.createElement('li');
    li.className = 'admin-org-li';

    const row = document.createElement('div');
    row.className = 'admin-org-node';

    const btn = document.createElement('button');
    btn.className = 'admin-org-toggle';
    btn.type = 'button';

    const nodeId = (node.id != null) ? node.id : node.ID;
    const isExpanded = q ? true : adminOrg.expanded.has(nodeId);

    if (!hasChildren && !hasUsers) {
      btn.textContent = '·';
      btn.disabled = true;
    } else {
      btn.textContent = isExpanded ? '−' : '+';
      btn.addEventListener('click', () => {
        if (adminOrg.expanded.has(nodeId)) adminOrg.expanded.delete(nodeId);
        else adminOrg.expanded.add(nodeId);
        render();
      });
    }

    const deptBox = document.createElement('div');
    deptBox.className = 'admin-org-dept';

    const deptName = document.createElement('div');
    deptName.className = 'admin-org-dept-name';
    deptName.textContent = node.name || '(이름없음)';

    const deptno = (node.deptno != null) ? node.deptno : node.DEPTNO;

    const deptMeta = document.createElement('div');
    deptMeta.className = 'admin-org-dept-meta';
    deptMeta.textContent = 'DEPTNO ' + deptno;

    deptBox.appendChild(deptName);
    deptBox.appendChild(deptMeta);

    const pill = document.createElement('div');
    pill.className = 'admin-org-pill';
    const cnt = (node.userCount != null) ? node.userCount : (node.users ? node.users.length : 0);
    pill.textContent = '인원 ' + cnt + '명';

    row.appendChild(btn);
    row.appendChild(deptBox);
    row.appendChild(pill);
    li.appendChild(row);

    if (isExpanded) {
      if (hasUsers) {
        const emp = document.createElement('div');
        emp.className = 'admin-org-emp';

        for (const u of node.users) {
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
          const pos = (u.position || '').trim();
          upos.textContent = pos ? ('(' + pos + ')') : '';

          left.appendChild(uname);
          left.appendChild(upos);

          const right = document.createElement('div');
          right.className = 'admin-org-emp-right';
          const email = (u.email != null) ? u.email : u.EMAIL;
          right.textContent = (email != null) ? String(email) : '';

          item.appendChild(left);
          item.appendChild(right);
          emp.appendChild(item);
        }

        li.appendChild(emp);
      }

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
    const deptno = (node.deptno != null) ? node.deptno : node.DEPTNO;
    const id = (node.id != null) ? node.id : node.ID;
    const s = ((node.name || '') + ' ' + (deptno != null ? deptno : '')).toLowerCase();
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
    const empno = (u.empno != null) ? u.empno : u.EMPNO;
    const s = ((u.name || '') + ' ' + (u.position || '') + ' ' + (u.email || '') + ' ' + (empno != null ? empno : '')).toLowerCase();
    return s.includes(q);
  }

  function escapeHtml(str) {
    return str.replace(/[&<>"']/g, (m) => ({
      '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    }[m]));
  }

  loadOrg();
});