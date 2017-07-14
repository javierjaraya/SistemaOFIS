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
                    <th>idSolicitud</th> 
                    <th>Rut</th>
                    <th>fecha Solicitud</th>
                    <th>Resolución</th>
                </tr> 
            </thead>
            <tbody id="tablaUsuarios">

            </tbody>
        </table>
        <input type="hidden" id ="accion" name ="accion">
    </div>
</div>
<!-- MODAL CONFIRMACION-->
<div class="modal fade bs-example-modal-sm" id="dg-confirmacion" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <section id="panel-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <img id="logo-confirmacion" src="/SistemaOFIS/img/log.png" width="50px">
                    <label class="titulo-modal"><h4 class="modal-title" id="titulo-mensaje"></h4></label>
                </div>
                <div class="modal-body">
                    <section class="row">
                        <section class="col-md-12">
                            <div id="contenedor-confirmacion">

                            </div>
                        </section>
                    </section>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" onclick="confirmarBorrar()">Eliminar</button>
                    <input type="hidden" value="" id="idSolicitudBorrar" name="idSolicitudBorrar">
                </div>
            </section>
        </div>
    </div>
</div>
<!-- END MODAL CONFIRMACION-->
<script>
    $(function () {
        cargarUsuarios();
    });
    var tabla = null;
    function cargarUsuarios() {
        $("#tablaUsuarios").empty();
        var url_json = '/SistemaOFIS/administrarSolicitudRegistro?accion=LISTADO';
        $.getJSON(
                url_json,
                function (datos) {
                    $.each(datos, function (k, v) {
                        var contenido = "<tr>";
                        contenido += "<td>" + v.idSolicitud + "</td>";
                        contenido += "<td>" + v.run + "</td>";
                        contenido += "<td>" + v.fechaSolicitud + "</td>";
                        contenido += "<td>";
                        contenido += "<a class='btn btn-success btn-xs glyphicon glyphicon-ok' onclick='confirmar(" + v.idSolicitud + ")'></a>&nbsp;";
                        contenido += "<a class='btn btn-danger btn-xs glyphicon glyphicon-remove' onclick='eliminar(" + v.idSolicitud + ")'></a>&nbsp;";
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
    function confirmar(id) {

    }
    function eliminar(id) {
        confirmacion('Confirmacion', '¿Esta seguro?, una vez eliminado no se podran recuperar los datos.');
        document.getElementById('idSolicitudBorrar').value = id;
    }
    function confirmacion(titulo, mensaje) {
        $('#titulo-confirmacion').html(titulo);
        $('#contenedor-confirmacion').html(mensaje);
        $('#dg-confirmacion').modal(this)//CALL MODAL MENSAJE
    }
    function confirmarBorrar() {
        var id = document.getElementById('idSolicitudBorrar').value;
        var url_json = "/SistemaOFIS/administrarSolicitudRegistro?accion=BORRAR&idSolicitudBorrar=" + id;
        $.ajax({
            type: "POST",
            url: url_json,
            success: function (result) {
                if (result.success) {
                    $('#dg-modela').modal('hide')
                    notificacion(result.statusText, 'success', 'alert');
                    cargarUsuarios();
                } else {
                    $('#dg-modela').modal('hide')
                    notificacion(result.statusText, 'danger', 'alert');
                }
            }
        });
    }
</script>

<%@ include file="footer.jsp" %>  