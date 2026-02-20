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

	

	// Quick action buttons
	document.querySelectorAll('[data-action]').forEach(btn => {
		btn.addEventListener('click', () => toast(btn.getAttribute('data-action') + ' (데모)'));
	});
})();