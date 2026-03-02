(function() {
	// Simple toast
	function toast(msg) {
		const t = document.createElement('div');
		t.textContent = msg;
		t.style.position = 'fixed';
		t.style.right = '18px';
		t.style.bottom = '18px';
		t.style.padding = '10px 12px';
		t.style.background = '#111827';
		t.style.color = 'white';
		t.style.borderRadius = '12px';
		t.style.boxShadow = '0 10px 24px rgba(0,0,0,.18)';
		t.style.zIndex = '9999';
		document.body.appendChild(t);
		setTimeout(() => { t.style.opacity = '0'; t.style.transition = 'opacity .2s'; }, 1400);
		setTimeout(() => { t.remove(); }, 1700);
	}
	window.__toast = toast;

	// Role-based UI (front demo only)
	const role = document.body.getAttribute('data-role') || 'MEMBER';
	document.querySelectorAll('[data-requires]').forEach(el => {
		const need = el.getAttribute('data-requires').split(',').map(s => s.trim());
		if (!need.includes(role)) el.style.display = 'none';
	});

	// Quick action buttons
	document.querySelectorAll('[data-action]').forEach(btn => {
		btn.addEventListener('click', () => toast(btn.getAttribute('data-action') + ' (데모)'));
	});
})();

document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll("tr.clickable-row").forEach((row) => {
    row.style.cursor = "pointer";

    row.addEventListener("click", (e) => {
      // 행 안에 버튼/링크 클릭했을 때는 row 이동 막기 (있을 경우 대비)
      const tag = e.target.tagName;
      if (tag === "A" || tag === "BUTTON" || e.target.closest("a, button")) return;

      const href = row.dataset.href;
      if (href) window.location.href = href;
    });
  });
});