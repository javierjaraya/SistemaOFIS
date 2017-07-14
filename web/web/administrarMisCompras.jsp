<%-- 
    Document   : administrarCarroCompra
    Created on : 13-07-2017, 5:00:00
    Author     : 
--%>

<%@ include file="header.jsp" %>  

<div class="col-md-12" style="padding: 5px; border: green 1px solid; border-radius: 15px; text-align: center; margin-bottom: 20px;">
    <h4 class="TextoTituloFormulario"><strong>MIS COMPRAS REALIZADAS</strong></h4>
</div>


<div class="col-md-12" id="subContenedor" style=" padding: 3%; align-content: center; border: green 1px solid; border-radius: 15px; margin-bottom: 20px;">
    <div class="col-md-6">
        <h5><strong>Compras</strong></h5>
    </div>
    <div class="col-md-12">
        <hr style="border: green 1px solid;">
        <div id="alert"></div>    
    </div>    
    <div class="col-md-12">
        <div class="table-responsive">
            <table id="tabla" class="table">
                <thead> 
                    <tr>                        
                        <th style="width: 50px;">ID</th> 
                        <th>Fecha</th>
                        <th>Metodo Despacho</th>
                        <th>Direccion Despacho</th>
                        <th>Persona Retira</th>
                        <th>Run</th>
                        <th>Total $</th>
                        <th style="width: 150px;">Acción</th>
                    </tr> 
                </thead>
                <tbody id="contenidoTabla">

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- DIALOGO MODAL-->
<div class="modal fade bs-example-modal-md" id="dg-modela" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <section id="panel-modal">
                <div class="modal-header" style=" border: green 1px solid; border-radius: 15px; text-align: center ; margin:  1%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <img id="logo-modal" src="/SistemaOFIS/img/log.png" width="60px" style="float: left;">
                    <label class="titulo-modal" style="width: 200px; padding-top: 20px;"><h4 class="modal-title" id="modalLabel"></h4></label>
                </div>
                <form id="fm" method="POST" class="form-horizontal" enctype="multipart/form-data">
                    <div style="margin: 1%; align-content: center; border: green 1px solid; border-radius: 15px;">
                        <div class="modal-body">
                            <section class="row"> 
                                <div id="alert-modal"></div>
                                <section class="col-md-12">
                                    <div id="nombresGroup" class="form-group has-feedback">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="idCompra">ID</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="idCompra" name="idCompra" readonly >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="fechaCompra">Fecha Compra</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="fechaCompra" name="fechaCompra" value="" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="metodoDespacho">metodo Despacho</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="metodoDespacho" name="metodoDespacho" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="direccion">Direccion Despacho</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="direccion" name="direccion" readonly >
                                            </div>                                        
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="personaRetira">Persona Retira</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="personaRetira" name="personaRetira" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="idProducto">ID Producto</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="idProducto" name="idProducto" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="nombreProducto">Producto</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="cantidad">Cantidad</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="cantidad" name="cantidad" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="total">Total Compra</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="total" name="total" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="estado">Estado</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="estado" name="estado" readonly>
                                            </div>
                                        </div>
                                        <input type="hidden" value="" name="accion" id="accion">
                                    </div>
                                </section>                           
                            </section><!-- Fin Row-->
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-default" data-dismiss="modal">Cerrar</a>
                            <!--<a class="btn btn-success" onclick="guardar()">Guardar</a>-->
                        </div>   
                    </div>             
                </form>
            </section>
        </div>
    </div>
</div><!-- END DIALOGO MODAL-->

<script>
    $(function () {
        cargar();
    });
    var tabla = null;
    function cargar() {
        $("#contenidoTabla").empty();
        var url_json = '/SistemaOFIS/administrarCompra?accion=LISTADO_MIS_COMPRAS';
        $.getJSON(
                url_json,
                function (datos) {
                    $.each(datos, function (k, v) {
                        var contenido = "<tr>";                        
                        contenido += "<td>" + v.idCompra + "</td>";
                        contenido += "<td>" + v.fechaCompra + "</td>";
                        contenido += "<td>" + v.metodoDespacho + "</td>";
                        contenido += "<td>" + v.direccion + "</td>";
                        contenido += "<td>" + v.personaRetira + "</td>";
                        contenido += "<td>" + v.run + "</td>";
                        contenido += "<td>$ " + v.total + "</td>";
                        contenido += "<td>";
                        contenido += "<a class='btn btn-info btn-xs glyphicon glyphicon-search'  onclick='ver(" + v.idCompra + ")'></a>&nbsp;";
                        contenido += "</td>";
                        contenido += "</tr>";
                        $("#contenidoTabla").append(contenido);
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

    function ver(id) {
        document.getElementById("fm").reset();
        document.getElementById('accion').value = "GUARDAR";
        document.getElementById('idCompra').value = id;
        $('#modalLabel').html("Detalle Compra");
        $('#dg-modela').modal(this);//CALL MODAL MENSAJE                                    
        rellenarFormulario(id);
    }

    function rellenarFormulario(id) {
        var url_json = "/SistemaOFIS/administrarCompra";
        $.ajax({
            type: "POST",
            url: url_json,
            data: 'accion=BUSCAR_BY_ID&idCompra=' + id,
            success: function (data) {
                var data = eval('(' + data + ')');

                document.getElementById('idCompra').value = data.idCompra;
                document.getElementById('fechaCompra').value = data.fechaCompra;
                document.getElementById('metodoDespacho').value = data.metodoDespacho;
                document.getElementById('direccion').value = data.direccion;
                document.getElementById('personaRetira').value = data.personaRetira;
                document.getElementById('total').value = "$ "+data.total;                
                document.getElementById('idProducto').value = data.detalle.idProducto;  
                document.getElementById('nombreProducto').value = data.detalle.nombreProducto;  
                document.getElementById('cantidad').value = data.detalle.cantidad; 
                document.getElementById('estado').value = data.estado; 

            }
        });
    }

    function guardar() {
        if (validar()) {
            var url = "/SistemaOFIS/administrarProducto?" + $("#fm").serialize();
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (datos) {
                    var datos = eval('(' + datos + ')');
                    if (datos.success) {
                        $('#dg-modela-producto').modal('hide')
                        notificacion(datos.statusText, 'success', 'alert');
                        cargar();
                    } else {
                        $('#dg-modela-producto').modal('hide')
                        notificacion(datos.statusText, 'danger', 'alert');
                    }
                }
            });
        }
    }

    function validar() {
        if (document.getElementById("nombreProducto").value == "") {
            notificacion("Debe ingresar el nombre del producto", 'warning', 'alert-modal');
            return false;
        } else if (document.getElementById("descripcionProducto").value == "") {
            notificacion("Debe ingresar la descripcion del producto", 'warning', 'alert-modal');
            return false;
        } else if (document.getElementById("stock").value == "") {
            notificacion("Debe ingresar el stock del producto", 'warning', 'alert-modal');
            return false;
        } else if (document.getElementById("precio").value == "") {
            notificacion("Debe ingresar el precio del producto", 'warning', 'alert-modal');
            return false;
        } else if (document.getElementById("idCategoria").value == 0) {
            notificacion("Debe seleccionar una categoria", 'warning', 'alert-modal');
            return false;
        }
        if (document.getElementById("imagenRemplazada").value == "TRUE") {
            if (document.getElementById("imagen").value == "") {
                notificacion("Debe seleccionar una imagen", 'warning', 'alert-modal');
                return false;
            } else if (validarArchivo(document.getElementById("imagen").value) == false) {
                notificacion("El archivo seleccionado no es un formato valido", 'warning', 'alert-modal');
                return false;
            }
        }
        return true;
    }

</script>
<%@ include file="footer.jsp" %>  