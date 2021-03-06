$(document).ready(function() {
	
	let languageConfig = {
			emptyTable: 'Sem dados',
		    loadingRecords: "Carregando...",
		    infoEmpty: 'Mostrando 0 de 0 of 0 linhas',
		    info: 'Mostrando _START_ até _END_ de _TOTAL_ linhas',
		    lengthMenu:'Mostrar _MENU_ linhas',
		    search: 'Pesquisa (PRESSIONE ENTER):',
		    paginate: {
		        first:      "Primeiro",
		        last:       "Ùltimo",
		        next:       "Próximo",
		        previous:   "Anterior"
		    },
	}; 

	
	moment.locale('pt-BR');
	var table = $('#table-usuarios').DataTable({
		language: languageConfig,		
		searching : true,
		lengthMenu : [ 5, 10 ],
		processing : true,
		serverSide : true,
		responsive : true,
		ajax : {
			url : '/u/pesquisa/datatables',
			data : 'data'
		},
		columns : [
				{data : 'id'},
				{data : 'email'},
				{	data : 'ativo', 
					render : function(ativo) {
						return ativo == true ? 'Sim' : 'Não';
					}
				},
				{	data : 'perfis',									 
					render : function(perfis) {
						var aux = new Array();
						$.each(perfis, function( index, value ) {
							  aux.push(value.desc);
						});
						return aux;
					},
					orderable : false,
				},
				{	data : 'id',	
					render : function(id) {
						return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
								 .concat('href="').concat('/u/editar/credenciais/').concat(id, '"', ' ') 
								 .concat('role="button" title="Editar" data-toggle="tooltip" data-placement="right">', ' ')
								 .concat('<i class="fas fa-duotone fa-pen-to-square"></i></a>');
					},
					orderable : false
				},
		]
	});
});	