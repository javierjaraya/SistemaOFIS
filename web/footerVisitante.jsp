</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        cargarMenu();
    });
    function cargarMenu() {
        var idPerfil = document.getElementById("idPerfilMenu").value;
        if (idPerfil == 1) {//admin
            carga("/SistemaOFIS/menus/menuAdministrador.jsp", "menu");
        } else if (idPerfil == 2) {//bodega
            carga("/SistemaOFIS/menus/menuBodega.jsp", "menu");
        } else if (idPerfil == 3) {//cliente
            carga("/SistemaOFIS/menus/menuCliente.jsp", "menu");
        } else {
            carga("/SistemaOFIS/menus/menuVisitante.jsp", "menu");
        }
    }

    function carga(url, id) {
        //console.log("CARGANDO: " + url + "  en " + id);
        var pagecnx = createXMLHttpRequest();
        pagecnx.onreadystatechange = function () {
            if (pagecnx.readyState == 4 && (pagecnx.status == 200 || window.location.href.indexOf("http") == -1))
                document.getElementById(id).innerHTML = pagecnx.responseText;
        }
        pagecnx.open('GET', url, true)
        pagecnx.send(null)
    }

    function createXMLHttpRequest() {
        var xmlHttp = null;
        if (window.ActiveXObject)
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        else if (window.XMLHttpRequest)
            xmlHttp = new XMLHttpRequest();
        return xmlHttp;
    }
</script>
</html>


