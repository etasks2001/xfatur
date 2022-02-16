



$(document).ready(function(){
	let languageConfig={
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
	let columns =cad.dataset.columns.split(',');
	for(let i = 0; i<columns.length;i++){
		columns[i]={data:columns[i]};
	}
	let columndefault =cad.dataset.columndefault.split(',');
	columndefault[0] = Number(columndefault[0]);
	let orderablefalse = cad.dataset.orderablefalse.split('');
	for(let i =0;i<orderablefalse.length;i++){
		orderablefalse[i]={ orderable: false, targets: Number(orderablefalse[i]) };
	}
	
	$("#table_search").DataTable({
		language: languageConfig,
        scrollX: true,
        style:'compact',
        search: {
            return:true
        },
        deferRender:true,
		searching:true,
		processing:true,
		serverSide:true,
		responsive:true,
		lengthMenu:[10,15],
		order:columndefault,
		ajax:{
			url:"/cadastro/pesquisar/datatables",
			data:"data"
		},
		columnDefs: orderablefalse,
	        dom: 'Qlfrtip',

	columns:columns
	});
});