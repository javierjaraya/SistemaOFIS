<%--    
    Document   : index
    Created on : 13-07-2017, 3:54:42
    Author     : 
--%>

<%@ include file="header.jsp" %>   

<div class="row">
    <div class="col-xs-12">        
        <div class="box">            
            <!--<div class="box-heading"><span>Featured</span></div>-->
            <h1> Productos </h1>
            <div class="product-filter">
                <!--<div class="display"><b>Display</b></div>-->
                <div class="limit"><b>Ver:</b>
                    <select id="por_pagina" onchange="load(1)">
                        <option selected="selected" value="12">12</option>
                        <option value="24">24</option>
                        <option value="40">40</option>
                        <option value="80">80</option>
                        <option value="100">100</option>
                    </select>
                </div>
                <div class="sort"><b>Ordernar por:</b>
                    <select id="orden" onchange="load(1)">
                        <option selected="selected" value="Defecto">Defecto</option>
                        <option value="A-Z">Nombre (A - Z)</option>
                        <option value="Z-A">Nombre (Z - A)</option>
                        <option value="Menor-Mayor">Precio (Menor < Mayor)</option>
                        <option value="Mayor-Menor">Precio (Mayor > Menor)</option>
                    </select>
                </div>
            </div>

            <div id="loader" class="text-center"><img src="/SistemaOFIS/img/loader.gif"></div>
            <div id="alert"></div>
            <div class="outer_div">
                <!-- Datos Productos aqui -->
                <div id="div-cont" class="row" style="min-height: 350px; padding-left: 20px; padding-bottom: 20px; padding-right: 20px; "> 

                </div>
            </div>
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
                                            <label class="col-sm-4 control-label" for="imagen">Imagen</label>
                                            <div class="col-sm-6">
                                                <div id="imagenPrevisualizada">
                                                    <img src="/SistemaOFIS/img/productos/images.png" width="100px;" height="100px;">
                                                </div>                                                
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="nombreProducto">Nombre Producto</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" placeholder="Nombre" readonly >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="descripcionProducto">Descripción Producto</label>
                                            <div class="col-sm-6">
                                                <textarea class="form-control" rows="3" id="descripcionProducto" name="descripcionProducto" placeholder="Descripción" readonly></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="stock">Cantidad</label>
                                            <div class="col-sm-6">
                                                <input type="number" class="form-control" min="1" id="cantidad" name="cantidad" placeholder="" value="1" onchange="actulizarTotal()">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="precio">Total $</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="total" name="total" placeholder="$" readonly>
                                            </div>                                        
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="direccion">Direccion Despacho</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Direccion">
                                            </div>                                        
                                        </div>
                                        <input type="hidden" value="" name="accion" id="accion">
                                        <input type="hidden" value="" name="idProducto" id="idProducto">
                                        <input type="hidden" value="" name="precio" id="precio">
                                    </div>
                                </section>                           
                            </section><!-- Fin Row-->
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-default" data-dismiss="modal">Cancelar</a>
                            <a class="btn btn-success" onclick="pagar()">Pagar</a>
                        </div>   
                    </div>             
                </form>
            </section>
        </div>
    </div>
</div><!-- END DIALOGO MODAL-->

<script>
    $(document).ready(function () {
        load(1);
    });

    function load(pagina) {
        por_pagina = document.getElementById("por_pagina").value;
        orden = document.getElementById("orden").value;
        var parametros = {"accion": "LISTADO", "pagina": pagina, "por_pagina": por_pagina, "orden": orden};
        $("#loader").fadeIn('slow');
        $.ajax({
            url: 'administrarProducto',
            data: parametros,
            beforeSend: function (objeto) {
                $("#loader").html("<img src='/SistemaOFIS/img/loader.gif'>");
            },
            success: function (data) {
                var data = eval(data);
                var html = "";
                $.each(data, function (k, v) {
                    html += "<div class='product-cuadro'>"
                            + "<div class='imagen'><a href='#'><img src='/SistemaOFIS/img/productos/images.png' width='135px' height='135px' alt='iPhone'></a></div>"
                            + "<div class='nombre'><a href=''>" + v.nombreProducto + "</a></div>"
                            + "<div class='precio'>$" + number_format(v.precio, 0, ',', '.') + "</div>"
                            + "<div class='cart'>"
                            + "  <input type='button' value='Comprar' onclick='comprar(" + v.idProducto + ");' class='button'>"
                            + "</div>"
                            + "</div>";
                });

                $("#div-cont").html(html).fadeIn('slow');
                $("#loader").html("");
            }
        });
    }

    function comprar(id) {
        document.getElementById("fm").reset();
        document.getElementById('accion').value = "GUARDAR";
        document.getElementById('idProducto').value = id;
        $('#modalLabel').html("Detalle Compra");
        $('#dg-modela').modal(this)//CALL MODAL MENSAJE                                    
        rellenarFormulario(id);
    }

    function rellenarFormulario(id) {
        var url_json = "/SistemaOFIS/administrarProducto";
        $.ajax({
            type: "POST",
            url: url_json,
            data: 'accion=BUSCAR_BY_ID&idProducto=' + id,
            success: function (data) {
                var data = eval('(' + data + ')');

                document.getElementById('idProducto').value = data.data.idProducto;
                document.getElementById('nombreProducto').value = data.data.nombreProducto;
                document.getElementById('descripcionProducto').value = data.data.descripcionProducto;
                document.getElementById('cantidad').max = data.data.stock;
                document.getElementById('precio').value = data.data.precio;
                actulizarTotal();
            }
        });
    }

    function actulizarTotal() {
        var precio = document.getElementById('precio').value;
        var cantidad = document.getElementById('cantidad').value;
        document.getElementById('total').value = "$ " + number_format((precio * cantidad), 0);
    }

    function pagar() {
        if (validar()) {
            var idProducto = document.getElementById('idProducto').value;
            var cantidad = document.getElementById('cantidad').value;
            var direccion = document.getElementById('direccion').value;

            var parametros = {"accion": "AGREGAR", "idProducto": idProducto, "cantidad": cantidad, "direccion": direccion};
            $("#loader").fadeIn('slow');
            $.ajax({
                url: '/SistemaOFIS/administrarCompra',
                data: parametros,
                beforeSend: function (objeto) {
                    $("#loader").html("<img src='/SistemaOFIS/img/loader.gif'>");
                },
                success: function (data) {                    
                    var data = eval('(' + data + ')');
                    $("#loader").html("");
                    if (data.success == true) {
                        notificacion(data.statusText, 'success', 'alert');
                        $('#dg-modela').modal('hide')
                    } else {
                        notificacion(data.statusText, 'warning', 'alert');
                        $('#dg-modela').modal('hide')
                    }
                }
            });
        }
    }

    function validar() {
        if (document.getElementById("cantidad").value == "") {
            notificacion("Debe ingresar una cantidad", 'warning', 'alert-modal');
            return false;
        } else if (document.getElementById("direccion").value == "") {
            notificacion("Debe ingresar la direccion de despacho", 'warning', 'alert-modal');
            return false;
        }
        return true;
    }

    function number_format(amount, decimals) {
        amount += ''; // por si pasan un numero en vez de un string
        amount = parseFloat(amount.replace(/[^0-9\.]/g, '')); // elimino cualquier cosa que no sea numero o punto

        decimals = decimals || 0; // por si la variable no fue fue pasada

        // si no es un numero o es igual a cero retorno el mismo cero
        if (isNaN(amount) || amount === 0)
            return parseFloat(0).toFixed(decimals);

        // si es mayor o menor que cero retorno el valor formateado como numero
        amount = '' + amount.toFixed(decimals);

        var amount_parts = amount.split('.'),
                regexp = /(\d+)(\d{3})/;

        while (regexp.test(amount_parts[0]))
            amount_parts[0] = amount_parts[0].replace(regexp, '$1' + ',' + '$2');

        return amount_parts.join('.');
    }
</script>

<%@ include file="footer.jsp" %>  