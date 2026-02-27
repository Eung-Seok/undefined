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

document
.addEventListener(
	'DOMContentLoaded',
	function() {
		var calendarEl = document
			.getElementById('google-calendar');

		var calendar = new FullCalendar.Calendar(
			calendarEl,
			{
				initialView: 'dayGridMonth', // 월간 보기
				locale: 'ko', // 한국어 설정
				headerToolbar: {
					left: 'prev,next',
					center: 'title',
					right: 'today'
				},
				height: 450, // 카드 크기에 맞춰 조절

				// 핵심: 우리가 만든 Spring Controller 주소에서 데이터를 가져옵니다.
				events: window.contextPath + 'api/calendar/events',
				eventClick: function(info) {
					alert('일정: ' + info.event.title);
				},
				eventColor: '#4285F4', // 구글 브랜드 색상
				loading: function(isLoading) {
					if (isLoading) {
						console.log("구글 일정 로딩 중...");
					}
				}
			});

		calendar.render();
	});