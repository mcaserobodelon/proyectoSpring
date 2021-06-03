// Llamamos a la función cuando damos al botón de hacer la pregunta
$("body").on('click', '#botonpregunta', agregarPregunta);


// Función que agrega la pregunta
function agregarPregunta() {

	var pregunta = $('#pregunta').val();
	var idLibro = $('#idLibro').val();
	
	if (pregunta == "") {
		return document.location.reload(true);
	}
	
	$('#pregunta').val('');
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	var datos = {
		"pregunta": pregunta,
		"idLibro": idLibro
	};
	
	$.ajax({
		url: "/qanda/pregunta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		
		success: function(response) {
			// Se recoge la pregunta del controlador y se obtienen sus cosas
			var pfecha = response.fechaPregunta;
			var pusuario = response.nombreUsuario;
			var ptexto = response.textoPregunta;
		
		},	
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	
	});

}