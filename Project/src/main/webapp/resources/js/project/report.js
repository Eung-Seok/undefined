document.addEventListener("DOMContentLoaded", function () {
  // 이슈 토글
  const checkbox = document.getElementById("issueCheck");
  const section = document.getElementById("issueSection");
  checkbox.addEventListener("change", function () {
    section.style.display = this.checked ? "block" : "none";
  });

  const typeEl  = document.getElementById("periodType");
  const startEl = document.getElementById("periodStart");
  const endEl   = document.getElementById("periodEnd");

  const fmt = (x) => {
    const mm = String(x.getMonth() + 1).padStart(2, "0");
    const dd = String(x.getDate()).padStart(2, "0");
    return `${x.getFullYear()}-${mm}-${dd}`;
  };

  const setDaily = () => {
    const d = new Date();
    startEl.value = fmt(d);
    endEl.value = fmt(d);
  };

  const setWeekly = () => {
    const d = new Date();
    const day = d.getDay(); // 0(일)~6(토)
    const diffToMon = (day === 0 ? -6 : 1 - day);

    const mon = new Date(d);
    mon.setDate(d.getDate() + diffToMon);

    const sun = new Date(mon);
    sun.setDate(mon.getDate() + 6);

    startEl.value = fmt(mon);
    endEl.value = fmt(sun);
  };

  const setMonthly = () => {
    const d = new Date();
    const first = new Date(d.getFullYear(), d.getMonth(), 1);
    const last  = new Date(d.getFullYear(), d.getMonth() + 1, 0); // 말일

    startEl.value = fmt(first);
    endEl.value = fmt(last);
  };

  const applyPeriod = () => {
    const t = typeEl.value;
    if (t === "DAILY") setDaily();
    else if (t === "WEEKLY") setWeekly();
    else if (t === "MONTHLY") setMonthly();
    // ETC는 자동 세팅 안 하고 사용자가 직접 선택하게 둠
  };

  // ✅ 처음 로드 시: 이미 값이 없다면 periodType 기준으로 자동 세팅
  if (!startEl.value && !endEl.value) {
    applyPeriod();
  }

  // ✅ 기간 유형 바꿀 때마다 자동 세팅
  typeEl.addEventListener("change", applyPeriod);
});