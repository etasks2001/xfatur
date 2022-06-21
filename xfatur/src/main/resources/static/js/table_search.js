$(document).ready(function(){
	
	
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
	
	let cad = document.getElementById('cad');
	
	if(cad){
		let cadname = cad.dataset.cadname;
		let columns = cad.dataset.columns.split(',');
		let columndefault = cad.dataset.columndefault.split(',');
		let titles = cad.dataset.titles.split(',');
		let orderable = cad.dataset.orderable.split(',');
		let columnDefs = [];

		columnDefs.push({title:' ', width: "10px", orderable : false, data : 'id', "render" : function(id) {
			return `<a class="btn btn-primary btn-sm btn-block" href="/${cadname}/editar/${id}" role="button"><i class="fa-regular fa-pen-to-square"></i></a>`;}});
		

		for(let i = 0; i<columns.length;i++){
			columnDefs.push({
						data:columns[i],
						title:titles[i],
						targets:i,
						orderable: orderable[i].toLowerCase() == 'true'
					}
			);
		}
		
		
//		columnDefs.push({title:' ', width: "10px", orderable : false,	data : 'id', "render" : function(id) {
//            return `<a class="btn btn-danger btn-sm btn-block" href="/${cadname}/excluir/${id}" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>`;}});
		
		
		
		columndefault[0] = Number(columndefault[0])+1;
		
		
		
		$("#table_search").DataTable({
			language: languageConfig,
	        scrollX: true,
	        //style:'compact',
	        columns:columnDefs,
	        search: {
	            return:true
	        },
	        //deferRender:true,
			searching:true,
			processing:true,
			serverSide:true,
			//responsive:true,
			lengthMenu:[10,15],
			order:columndefault,
			ajax:{
				url:"/cadastro/pesquisar/datatables",
				data:{
					cadname:cadname				
				},
			},
//	        dom: 'Qlfrtip',
	        initComplete: function () {
	        	  $('#table_search_filter label input').focus();
	        	}
		});
	}
	
	
	
	
	
});