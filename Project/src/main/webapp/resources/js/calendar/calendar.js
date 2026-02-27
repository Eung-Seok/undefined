(function() {
	let calendar;

	// 1. 유틸리티 함수 (Toast)
	window.__toast = function(msg) {
		const t = document.createElement('div');
		t.textContent = msg;
		Object.assign(t.style, {
			position: 'fixed', right: '18px', bottom: '18px', padding: '10px 12px',
			background: '#111827', color: 'white', borderRadius: '12px',
			boxShadow: '0 10px 24px rgba(0,0,0,.18)', zIndex: '9999', transition: 'opacity .2s'
		});
		document.body.appendChild(t);
		setTimeout(() => { t.style.opacity = '0'; }, 1400);
		setTimeout(() => { t.remove(); }, 1700);
	};

	// 2. 초기화
	document.addEventListener('DOMContentLoaded', () => {
		const ctx = window.contextPath || '';
		const EVENTS_API = ctx + '/api/calendar/events';
		const SYNC_API = ctx + '/api/calendar/sync';

		initUI(SYNC_API, EVENTS_API);
		initFullCalendar(EVENTS_API);

		// 페이지 로드 시 동기화 실행 (자동)
//		autoSync(SYNC_API, EVENTS_API);
	});

	// FullCalendar 초기화 및 필드 매핑
	function initFullCalendar(apiUrl) {
		const calendarEl = document.getElementById('google-calendar');
		if (!calendarEl) return;

		calendar = new FullCalendar.Calendar(calendarEl, {
			initialView: 'dayGridMonth',
			locale: 'ko',
			headerToolbar: { left: 'prev,next', center: 'title', right: 'today' },
			height: 450,

			// [핵심] API 데이터를 FullCalendar 형식으로 변환
			events: apiUrl,
			eventDataTransform: function(eventData) {
				const parseDate = (dateSource) => {
					if (!dateSource) return null;
					if (Array.isArray(dateSource)) {
						// [2024, 2, 26, ...] 형태일 경우
						return new Date(dateSource[0], dateSource[1] - 1, dateSource[2], dateSource[3] || 0, dateSource[4] || 0);
					}
					return dateSource; // 문자열일 경우 그대로 반환
				};

				return {
					id: eventData.id,
					title: eventData.name,      // DTO: name -> FullCalendar: title
					start: parseDate(eventData.startDate),
					end: parseDate(eventData.endDate),
					extendedProps: {
						type: eventData.type,
						projectId: eventData.projectId,
						eId: eventData.eId
					},
					color: '#4285F4'
				};
			},
			eventClick: (info) => alert('일정: ' + info.event.title)
		});
		calendar.render();
		fetchTableEvents(apiUrl);
	}

	// 하단 리스트 테이블 (DTO 필드명 사용)
	async function fetchTableEvents(apiUrl) {
		const tbody = document.getElementById('calendar-data-body');
		if (!tbody) return;

		try {
			const response = await fetch(apiUrl);
			const data = await response.json();

			tbody.innerHTML = data.length ? '' : '<tr><td colspan="3">일정이 없습니다.</td></tr>';

			data.forEach(event => {
				// startDate (LocalDateTime) 처리
				let formattedDate = "-";
				if (event.startDate) {
					let d;
					if (Array.isArray(event.startDate)) {
						d = new Date(event.startDate[0], event.startDate[1] - 1, event.startDate[2]);
					} else {
						d = new Date(event.startDate);
					}

					if (!isNaN(d.getTime())) {
						// getMonth()는 0부터 시작하므로 +1 필수
						formattedDate = `${d.getMonth() + 1}/${d.getDate()}`;
					}
				}

				const tr = document.createElement('tr');
				tr.innerHTML = `
                    <td>${formattedDate}</td>
                    <td>${escapeHtml(event.name || '(제목 없음)')}</td>
                    <td>${escapeHtml(event.type || '-')}</td>
                `;
				tbody.appendChild(tr);
			});
		} catch (error) {
			console.error('Data load error:', error);
			tbody.innerHTML = '<tr><td colspan="3">일정을 불러올 수 없습니다.</td></tr>';
		}
	}

	// 동기화 로직 (이전과 동일)
	async function autoSync(syncUrl, eventsUrl) {
		await runSync(syncUrl, eventsUrl, true);
	}

	async function runSync(syncUrl, eventsUrl, isQuiet = false) {
		try {
			const response = await fetch(syncUrl, { method: 'POST' });
			const result = await response.text();

			if (result === "success") {
				if (!isQuiet) window.__toast("동기화 완료!");
				if (calendar) calendar.refetchEvents();
				fetchTableEvents(eventsUrl);
			}
		} catch (error) {
			console.error('Sync error:', error);
		}
	}

	function initUI(syncUrl, eventsUrl) {
		const syncBtn = document.getElementById('sync-btn');
		if (syncBtn) {
			syncBtn.addEventListener('click', async () => {
				syncBtn.disabled = true;
				window.__toast("동기화 중...");
				await runSync(syncUrl, eventsUrl);
				syncBtn.disabled = false;
			});
		}
	}

	function escapeHtml(str) {
		if (!str) return "";
		const p = document.createElement('p');
		p.textContent = str;
		return p.innerHTML;
	}
})();