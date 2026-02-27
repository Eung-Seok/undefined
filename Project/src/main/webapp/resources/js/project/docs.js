document.addEventListener("DOMContentLoaded", function () {

	console.log("docs.js 정상 실행");

	const openBtn = document.getElementById("openUploadModal");
	const modal = document.getElementById("uploadModal");
	const closeBtn = document.getElementById("closeModal");

	console.log(openBtn, modal, closeBtn);

	if (!openBtn || !modal || !closeBtn) {
		console.log("모달 요소 못찾음");
		return;
	}

	openBtn.addEventListener("click", () => {
		console.log("업로드 클릭됨");
		modal.style.display = "flex";
		modal.classList.remove("hidden");
	});

	closeBtn.addEventListener("click", () => {
		modal.classList.add("hidden");
	});

	modal.addEventListener("click", (e) => {
		if (e.target === modal) {
			modal.classList.add("hidden");
		}
	});

});