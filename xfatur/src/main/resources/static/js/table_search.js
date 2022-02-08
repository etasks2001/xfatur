$(document).ready(function(){
	$("#table_search").DataTable({
		processing:true,
		serverSide:true,
		responsive:true,
		lengthMenu:[10,15,20,25],
		ajax:{
			url:"",
			data:"data"
		}
	columns:[
		{data:'id'},
		{data:'ncm'},
		{data:'descricao'}
		
		
	]
		
	});
	
	
	
});