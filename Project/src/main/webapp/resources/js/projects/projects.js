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

document.addEventListener("DOMContentLoaded", function () {

    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");

    // 시작일 선택 시 → 종료일 최소값 제한
    startDate.addEventListener("change", function () {
        if (startDate.value) {
            endDate.min = startDate.value;

            // 이미 선택된 종료일이 시작일보다 이전이면 초기화
            if (endDate.value && endDate.value < startDate.value) {
                endDate.value = "";
            }
        }
    });

    // 종료일 선택 시 → 시작일 최대값 제한
    endDate.addEventListener("change", function () {
        if (endDate.value) {
            startDate.max = endDate.value;

            // 이미 선택된 시작일이 종료일보다 이후이면 초기화
            if (startDate.value && startDate.value > endDate.value) {
                startDate.value = "";
            }
        }
    });

});