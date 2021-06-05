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
			var pusuario = response.nombreCliente;
			var ptexto = response.textoPregunta;

            // Se crean los nodos para la tabla
			var filaPregunta = document.createElement("tr");
			filaPregunta.setAttribute("id", "filaPregunta");
			var tdpfecha = document.createElement("td");
			var tdpusuario = document.createElement("td");
			var tdptexto = document.createElement("td")
			var tdpopciones1 = document.createElement("td");

            // Se asignan los datos a los nodos
			tdpfecha.textContent = `${pfecha}`;
			tdpusuario.textContent = `${pusuario}`;
			tdptexto.textContent = `${ptexto}`;
		
		},	
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	
	});

}