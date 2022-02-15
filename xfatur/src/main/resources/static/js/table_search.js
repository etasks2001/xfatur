$(document).ready(function(){
	$("#table_search").DataTable({
		language:{

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
		},
        scrollX: true,
        style:'compact',
        search: {
            return: true
        },

		searching:true,
		processing:true,
		serverSide:true,
		responsive:true,
		lengthMenu:[5,10],
		order:[2,'asc'],
		ajax:{
			url:"/cadastro/pesquisar/datatables",
			data:"data"
		},
		columnDefs: [
		    { orderable: false, targets: 0 }
		  ],
	        dom: 'Qlfrtip',

	columns:[
		{data:'id'},
		{data:'ncm'},
		{data:'descricao'},
	]
		
	});
});