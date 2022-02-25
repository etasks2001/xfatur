
let uppercase = function() {
	let p = this.selectionStart;
	this.value = this.value.toUpperCase();
	this.setSelectionRange(p, p);
}
let lowercase = function() {
	let p = this.selectionStart;
	this.value = this.value.toLowerCase();
	this.setSelectionRange(p, p);
}

let onblur = function(e) {

	let input = e.target;
	let id = input.id;

	let errorfield = document.getElementById("error_" + id);

	input.classList.remove("is-invalid");

	if (errorfield) {
		errorfield.innerHTML = "";
	}
	console.log(input.id);

}

window.onload = function() {

	var ncm_mask = document.getElementById("cf-ncm");

	if (ncm_mask) {
		var im = new Inputmask("9999.9999");
		im.mask(ncm_mask);
	}
	

	let inputs = document.querySelectorAll("input");
	if (inputs) {
		for (var i = 0; i < inputs.length; i++) {
			inputs[i].addEventListener("input", uppercase);
			inputs[i].addEventListener("blur", onblur);
		}
	}
	
	
	
	
	var abreviacao = document.getElementById("abreviacao");
	
	if (abreviacao) {
		abreviacao.addEventListener("input", lowercase);
	}

	
	
	
	
}
