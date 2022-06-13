	$('#perfis').ready(function(){
		var perfis = [[${usuario.perfis}]];
		
		$.each(perfis, function(k, v) {
			console.log(v);
			$('#perfis option[value="'+ v.id +'"]').attr('selected', 'selected');
		})
	});
