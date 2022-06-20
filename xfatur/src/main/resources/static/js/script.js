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

}

function setMask(input, pattern) {

	if (input) {
		let mask = new Inputmask(pattern);
		mask.mask(input);
	}

}

window.onload = function() {
	setMask(document.getElementById("classificacaofiscal-ncm"), "9999.9999");
	setMask(document.getElementById("origem-codigo"), "9");
	setMask(document.getElementById("tipoitem-codigo"), "99");
	setMask(document.getElementById("tiposelo-codigo"), "999999");
	setMask(document.getElementById("tributacao-codigo"), "99");
	setMask(document.getElementById("pais-codigoBacen"), "9999");
	setMask(document.getElementById("produto-codigoProduto"), {
		mask : "999.**.999"
	});
	setMask(document.getElementById("produto-codigoDeBarras"), {
		regex : "^[0-9]*$"
	});

	let inputs = document.querySelectorAll("input :not([id*='test'])");
	if (inputs) {
		for (var i = 0; i < inputs.length; i++) {
			inputs[i].addEventListener("input", uppercase);
			inputs[i].addEventListener("blur", onblur);
		}
	}
	
	
	

	
	var abreviacao = document.getElementById("unidade-abreviacao");
	if (abreviacao) {
		abreviacao.addEventListener("input", lowercase);
	}

	let formheader = document.querySelector('[data-selecteduri]');

	if (formheader) {

		let selecteduri = formheader.dataset.selecteduri;
		let urlArray = selecteduri.split('/');

		if (selecteduri.search("/form") >= 0) {
			cadname = urlArray[urlArray.length - 2];
		} else if (selecteduri.search("/pesquisar") >= 0) {
			cadname = urlArray[urlArray.length - 1];
		} else if (selecteduri.search("/editar") >= 0) {
			cadname = urlArray[urlArray.length - 3];
		}

		let link = document.querySelector(`[href='/${cadname}/form']`);

		link.classList.toggle('bg-dark');
		link.classList.toggle('bg-white');
		link.classList.toggle('bg-primary');
		link.classList.toggle('bg-white');

	}
}