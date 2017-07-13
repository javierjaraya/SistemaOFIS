<%-- 
    Document   : administrarProductos
    Created on : 13-07-2017, 4:52:34
    Author     : 
--%>

<%@ include file="header.jsp" %>  

<div class="col-md-12" style="padding: 5px; border: orangered 1px solid; border-radius: 15px; text-align: center; margin-bottom: 20px;">
    <h4 class="TextoTituloFormulario"><strong>ADMINISTRAR PRODUCTOS</strong></h4>
</div>

<div class="col-md-12" id="subContenedor" style=" padding: 3%; align-content: center; border: orangered 1px solid; border-radius: 15px; margin-bottom: 20px;">
    <div class="col-md-6">
        <h5><strong>PRODUCTOS</strong></h5>
    </div>
    <div class="col-md-6">
        <form class="form-inline">            
            <a onclick="agregarProducto()" class="btn btn-warning btn-sm" style="float: right;">Agregar Producto</a>
        </form>        
    </div>
    <div class="col-md-12">
        <hr style="border: orangered 1px solid;">
        <div id="alert"></div>    
    </div>    
    <div class="col-md-12">
        <div class="table-responsive">
            <table id="tabla" class="table">
                <thead> 
                    <tr>
                        <th style="">Grafico</th> 
                        <th style="width: 50px;">ID</th> 
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Stock</th>
                        <th>Precio</th>
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
                <div class="modal-header" style=" border: orangered 1px solid; border-radius: 15px; text-align: center ; margin:  1%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <img id="logo-modal" src="/SistemaOFIS/img/log.png" width="60px" style="float: left;">
                    <label class="titulo-modal" style="width: 200px; padding-top: 20px;"><h4 class="modal-title" id="modalLabel"></h4></label>
                </div>
                <form id="fm" method="POST" class="form-horizontal" enctype="multipart/form-data">
                    <div style="margin: 1%; align-content: center; border: orangered 1px solid; border-radius: 15px;">
                        <div class="modal-body">
                            <section class="row"> 
                                <div id="alert-modal"></div>
                                <section class="col-md-12">
                                    <div id="nombresGroup" class="form-group has-feedback">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="nombreProducto">Nombre Producto</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" placeholder="Nombre" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="descripcionProducto">Descripción Producto</label>
                                            <div class="col-sm-6">
                                                <textarea class="form-control" rows="5" id="descripcionProducto" name="descripcionProducto" placeholder="Descripción"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="stock">Stock</label>
                                            <div class="col-sm-6">
                                                <input type="number" class="form-control" id="stock" name="stock" placeholder="" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="precio">Precio</label>
                                            <div class="col-sm-6">
                                                <input type="number" class="form-control" id="precio" name="precio" placeholder="$" >
                                            </div>                                        
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="idCategoria">Categoria</label>
                                            <div class="col-sm-6">
                                                <select class="form-control" id="idCategoria" name="idCategoria"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="imagen">Imagen</label>
                                            <div class="col-sm-6">
                                                <div id="imagenPrevisualizada">

                                                </div>
                                                <input type="file" class="form-control" id="imagen" name="imagen" placeholder="" >
                                            </div>
                                        </div>
                                        <input type="hidden" value="" name="accion" id="accion">
                                        <input type="hidden" value="" name="idProducto" id="idProducto">
                                        <input type="hidden" value="" name="idImagen" id="idImagen">
                                        <input type="hidden" value="" name="imagenRemplazada" id="imagenRemplazada">
                                    </div>
                                </section>                           
                            </section><!-- Fin Row-->
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-default" data-dismiss="modal">Cancelar</a>
                            <a class="btn btn-warning" onclick="guardar()">Guardar</a>
                        </div>   
                    </div>             
                </form>
            </section>
        </div>
    </div>
</div><!-- END DIALOGO MODAL-->

<!-- MODAL CONFIRMACION-->
<div class="modal fade bs-example-modal-sm" id="dg-confirmacion" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <section id="panel-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <img id="logo-confirmacion" src="/SistemaOFIS/img/log.png" width="60px">
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
                    <button type="button" class="btn btn-danger" onclick="confirmarBorrar()">Borrar</button>
                    <input type="hidden" value="" id="idProductoEliminar" name="idProductoEliminar">
                </div>
            </section>
        </div>
    </div>
</div><!-- END MODAL CONFIRMACION-->

<script>
    $(function () {
        cargar();
        cargarCategorias();
    });
    var tabla = null;
    function cargar() {
        $("#contenidoTabla").empty();
        var url_json = '/SistemaOFIS/administrarProducto?accion=LISTADO';
        $.getJSON(
                url_json,
                function (datos) {
                    $.each(datos, function (k, v) {
                        var contenido = "<tr>";
                        contenido += "<td><img src='/SistemaOFIS/img/productos/images.png' width='50px' height='50px'></td>";
                        contenido += "<td>" + v.idProducto + "</td>";
                        contenido += "<td>" + v.nombreProducto + "</td>";
                        contenido += "<td>" + v.descripcionProducto + "</td>";
                        contenido += "<td>" + v.stock + "</td>";
                        contenido += "<td>" + v.precio + "</td>";
                        contenido += "<td>";
                        contenido += "<a class='btn btn-warning btn-xs glyphicon glyphicon-pencil'  onclick='editar(" + v.idProducto + ")'></a>&nbsp;";
                        contenido += "<a class='btn btn-danger btn-xs glyphicon glyphicon-trash'  onclick='borrar(" + v.idProducto + ")  '></a>";
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


    function cargarCategorias() {
        $("#idCategoria").empty();
        var url_json = '/SistemaOFIS/administrarCategoria?accion=LISTADO';
        $.getJSON(
                url_json,
                function (datos) {
                    $.each(datos, function (k, v) {
                        $("#idCategoria").append("<option value='" + v.idCategoria + "'> " + v.nombreCategoria + "</option>");
                    });
                }
        );
    }

    function editar(id) {
        document.getElementById("fm").reset();
        document.getElementById('accion').value = "ACTUALIZAR";
        document.getElementById('idProducto').value = id;
        $('#modalLabel').html("Editar Producto");
        $('#dg-modela').modal(this)//CALL MODAL MENSAJE                                    
        rellenarFormulario(id);
    }

    function rellenarFormulario(id) {
        document.getElementById('imagen').style.display = 'none';
        document.getElementById('imagenPrevisualizada').style.display = 'block';
        var url_json = '../Servlet/administrarProducto.php';
        $.ajax({
            type: "POST",
            url: url_json,
            data: 'accion=BUSCAR_BY_ID&idProducto=' + id,
            success: function (data) {
                var data = eval('(' + data + ')');
                document.getElementById('idProducto').value = data.idProducto;
                document.getElementById('nombreProducto').value = data.nombreProducto;
                document.getElementById('descripcionProducto').value = data.descripcionProducto;
                document.getElementById('stock').value = data.stock;
                document.getElementById('precio').value = data.precio;
                document.getElementById('idCategoria').value = data.idCategoria;
                document.getElementById('imagenPrevisualizada').innerHTML = "<img src='../../" + data.imagen.rutaImagen + "' width='75px' height='75px'>&nbsp;&nbsp;<a class='btn btn-danger btn-xs glyphicon glyphicon-remove'  onclick='borrarPrevisualizacion()'></a>";
                document.getElementById('idImagen').value = data.imagen.idImagen;
                document.getElementById('imagenRemplazada').value = "FALSE";
            }
        });
    }

    function borrarPrevisualizacion() {
        document.getElementById('imagenRemplazada').value = "TRUE";
        document.getElementById('imagen').style.display = 'block';
        document.getElementById('imagenPrevisualizada').style.display = 'none';
    }

    function agregarProducto() {
        document.getElementById("fm").reset();
        document.getElementById('accion').value = "AGREGAR";
        document.getElementById('imagenRemplazada').value = "TRUE";
        document.getElementById('imagen').style.display = 'block';
        document.getElementById('imagenPrevisualizada').style.display = 'none';
        $('#modalLabel').html("Crear Producto");
        $('#dg-modela').modal(this)//CALL MODAL MENSAJE
    }

    function guardar() {
        if (validar()) {
            var url = "/SistemaOFIS/administrarProducto?accion=AGREGAR&" + $("#fm").serialize();
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (datos) {
                    var datos = eval('(' + datos + ')');
                    if (datos.success) {
                        $('#dg-modela').modal('hide')
                        notificacion(datos.statusText, 'success', 'alert');
                        cargar();
                    } else {
                        $('#dg-modela').modal('hide')
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

    function validarArchivo(archivo) {
        extensiones_permitidas = new Array(".gif", ".jpg", ".png");
        //recupero la extensión de este nombre de archivo 
        extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();

        for (var i = 0; i < extensiones_permitidas.length; i++) {
            if (extensiones_permitidas[i] == extension) {
                return true;
            }
        }
        return false;
    }

    function confirmacion(titulo, mensaje) {
        document.getElementById('logo-confirmacion').src = "../../Files/img/log.png";
        $('#titulo-confirmacion').html(titulo);
        $('#contenedor-confirmacion').html(mensaje);
        $('#dg-confirmacion').modal(this)//CALL MODAL MENSAJE
    }

    function borrar(id) {
        confirmacion('Confirmacion', '¿Esta seguro?, una vez eliminado no se podran recuperar los datos.');
        document.getElementById('idProductoEliminar').value = id;
    }

    function confirmarBorrar() {
        var id = document.getElementById('idProductoEliminar').value;
        $.post('../Servlet/administrarProducto.php?accion=BORRAR', {idProducto: id}, function (result) {
            if (result.success) {
                $('#dg-confirmacion').modal('toggle'); //Cerrar Modal
                cargar();//Refrescamos la tabla
                notificacion(result.mensaje, 'success', 'alert');
            } else {
                $('#dg-confirmacion').modal('toggle'); //Cerrar Modal
                notificacion(result.errorMsg, 'danger', 'alert');
            }
        }, 'json');
    }

    function ver(idCategoria) {
        window.location = "administrarSubCategorias.php?idCategoria=" + idCategoria;
    }
</script>

<%@ include file="footer.jsp" %>  