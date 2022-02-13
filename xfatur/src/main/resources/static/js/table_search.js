$(document).ready(function(){
	$("#table_search").DataTable({
		processing:true,
		serverSide:true,
		responsive:true,
		lengthMenu:[5,15,10],
		ajax:{
			url:"/cadastro/pesquisar/datatables",
			data:"data"
		},
	columns:[
		{data:'id'},
		{data:'ncm'},
		{data:'descricao'},
		
		
	]
		
	});
	
	
	
});