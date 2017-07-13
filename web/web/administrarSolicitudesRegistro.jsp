<%-- 
    Document   : administrarSolicitudesRegistro
    Created on : 13-07-2017, 4:53:08
    Author     : 
--%>

<%@ include file="header.jsp" %>  

<div class="col-md-12" style="padding: 5px; border: green 1px solid; border-radius: 15px; text-align: center; margin-bottom: 20px;">
    <h4 class="TextoTituloFormulario"><strong>ADMINISTRAR REGISTRO DE USUARIOS</strong></h4>
</div>

<div class="col-md-12" id="subContenedor" style=" padding: 3%; align-content: center; border: green 1px solid; border-radius: 15px; margin-bottom: 20px;">
    <h5><strong>SOLICITUDES DE USUARIOS</strong></h5>
    <hr style="border: green 1px solid;">
    <div id="alert"></div>
    <div class="table-responsive">
        <table id="tabla" class="table">
            <thead> 
                <tr> 
                    <th>Rut</th> 
                    <th>Nombre</th>
                    <th>Teléfono</th>
                    <th>Dirección</th>
                    <th>Tipo Cliente</th>
                    <th>Accion</th>
                </tr> 
            </thead>
            <tbody id="tablaUsuarios">

            </tbody>
        </table>
    </div>
</div>

<script>
    $(function () {
        cargarUsuarios();
    });
    var tabla = null;
       function cargarUsuarios() {
        $("#tablaUsuarios").empty();
        var url_json = '/SistemaOFIS/AdministrarSolicitudRegistro?accion=LISTADO';
        $.getJSON(
                url_json,
                function (datos) {
                    $.each(datos, function (k, v) {
                        var nombreCompleto = v.nombres + " " + v.apellidos;
                        var contenido = "<tr>";
                        contenido += "<td>" + v.run + "</td>";
                        contenido += "<td>" + nombreCompleto + "</td>";
                        contenido += "<td>" + v.telefono + "</td>";
                        contenido += "<td>" + v.direccion + "</td>";
                        contenido += "<td>" + v.tipoUsuario + "</td>";
                        contenido += "<td>";
                        contenido += "<a class='btn btn-warning btn-xs glyphicon glyphicon-pencil'  onclick='editar(" + v.run + ")'></a>&nbsp;";
                        contenido += "<a class='btn btn-danger btn-xs glyphicon glyphicon-trash'  onclick='borrar(" + v.run + ")  '></a>&nbsp;";
                        contenido += "</td>";
                        contenido += "</tr>";
                        $("#tablaUsuarios").append(contenido);
                    });
                    if (tabla == null) {
                        tabla = $('#tabla').DataTable(
                                {
                                    "oLanguage": {
                                        "oPaginate": {
                                            "sNext": "Siguiente",
                                            "sPrevious": "Anterior"
                                        },
                                        "sLengthMenu": "Mostrar _MENU_ Resultados",
                                        "sSearch": "Buscar",
                                        "sZeroRecords": "No se encontraron Resultados",
                                        "sInfo": "Mostrar desde el _START_ hasta el _END_ de un total de _TOTAL_ Resultados",
                                        "sInfoEmpty": "Mostrar desde el 0 Hasta el 0 de un total de 0 Resultados",
                                        "sInfoFiltered": "(Filtrado desde un total de _MAX_ Resultados)"
                                    },
                                }
                        );
                    }
                }
        );
    }
</script>

<%@ include file="footer.jsp" %>  