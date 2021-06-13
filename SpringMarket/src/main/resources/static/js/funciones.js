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
			var tbody = document.getElementById('tablaqanda').getElementsByTagName('tbody')[0];
			var row = tbody.insertRow();
			var cell1 = row.insertCell(0);
  			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);

            // Se asignan los datos a los nodos
			cell1.innerHTML = pfecha;
  			cell2.innerHTML = pusuario;
			cell3.innerHTML = ptexto;
			
			//Creamos el botón para borrar la pregunta
			var botonBorrarPregunta = document.createElement("button");
			botonBorrarPregunta.setAttribute("id", "botonBorrarPregunta");
			botonBorrarPregunta.innerHTML = "Borra esta pregunta";
			botonBorrarPregunta.classList.add("btn");
			botonBorrarPregunta.classList.add("btn-outline-danger");
			
			tdpopciones1.appendChild(botonBorrarPregunta);
			
		
		},	
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	
	});

}