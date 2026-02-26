(() => {
    const $ = (s) => document.querySelector(s);
    const $$ = (s) => Array.from(document.querySelectorAll(s));

    const $form = $("#pmForm");
    const $search = $("#pmSearch");
    const $count = $("#pmCount");

    const $bulkRole = $("#pmBulkRole");
    const $applyBulk = $("#pmApplyBulk");
    const $checkAll = $("#pmCheckAll");
    const $uncheckAll = $("#pmUncheckAll");

    const $submitTop = $("#pmSubmitTop");
    const $submit = $("#pmSubmit");
    const $cancel = $("#pmCancel");

    function updateCount() {
        if (!$count) return;
        const checked = $$(".pm-check:checked").length;
        $count.textContent = String(checked);
    }

    function syncRow(row, selected) {
        row.classList.toggle("is-selected", selected);
        const roleSel = row.querySelector(".pm-role");
        if (roleSel) roleSel.disabled = !selected;
    }

    function setChecked(row, checked) {
        const ch = row.querySelector(".pm-check");
        if (!ch) return;
        ch.checked = checked;
        syncRow(row, checked);
    }

    function submitIfValid() {
        const any = $$(".pm-check").some(ch => ch.checked);
        if (!any) {
            alert("추가할 사용자를 1명 이상 선택하세요.");
            return;
        }
        $form?.submit();
    }

    function initRowHandlers() {
        $$(".pm-row").forEach(row => {
            const ch = row.querySelector(".pm-check");
            if (!ch) return;

            // 초기 동기화
            syncRow(row, ch.checked);

            // 체크박스 직접 변경 시
            ch.addEventListener("change", () => {
                syncRow(row, ch.checked);
                updateCount();
            });

            // 행 클릭 시 체크 토글
            row.addEventListener("click", (e) => {
                // input/select/button/a/label 클릭은 패스(중복 토글 방지)
                if (e.target.closest("input, select, button, a, label")) return;

                ch.checked = !ch.checked;
                syncRow(row, ch.checked);
                updateCount();
            });
        });

        updateCount();
    }

    // Bulk apply role to selected rows
    function initBulkHandlers() {
        if ($applyBulk && $bulkRole) {
            $applyBulk.addEventListener("click", () => {
                const role = $bulkRole.value;
                if (!role) return;

                $$(".pm-row").forEach(row => {
                    const ch = row.querySelector(".pm-check");
                    const sel = row.querySelector(".pm-role");
                    if (ch && ch.checked && sel) sel.value = role;
                });
            });
        }

        if ($checkAll) {
            $checkAll.addEventListener("click", () => {
                $$(".pm-row").forEach(row => setChecked(row, true));
                updateCount();
            });
        }

        if ($uncheckAll) {
            $uncheckAll.addEventListener("click", () => {
                $$(".pm-row").forEach(row => setChecked(row, false));
                updateCount();
            });
        }
    }

    // Search filter
    function initSearchHandler() {
        if (!$search) return;

        $search.addEventListener("input", () => {
            const q = ($search.value || "").trim().toLowerCase();

            $$(".pm-row").forEach(row => {
                if (!q) { row.style.display = ""; return; }
                const s = [
                    row.dataset.empno || "",
                    row.dataset.name || "",
                    row.dataset.email || "",
                    row.dataset.dept || ""
                ].join(" ").toLowerCase();
                row.style.display = s.includes(q) ? "" : "none";
            });
        });
    }

    // Submit / Cancel
    function initActionHandlers() {
        if ($submitTop) $submitTop.addEventListener("click", submitIfValid);
        if ($submit) $submit.addEventListener("click", (e) => { e.preventDefault(); submitIfValid(); });
        if ($cancel) $cancel.addEventListener("click", () => history.back());
    }

    // Init
    document.addEventListener("DOMContentLoaded", () => {
        initRowHandlers();
        initBulkHandlers();
        initSearchHandler();
        initActionHandlers();
    });
})();