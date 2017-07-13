

function notificacion(mensaje, tipo, contenedor) {
    $("#" + contenedor).css("display", "block");    
    var class_alert = "alert-info";
    var icon_alert = 'fa-info';
    var titulo_alert = "Alerta";
    if (tipo == 'danger') {
        class_alert = "alert-danger";
        icon_alert = 'fa-ban';
        titulo_alert = "Error";
    } else if (tipo == 'info') {
        class_alert = "alert-info";
        icon_alert = 'fa-info';
        titulo_alert = "Informacion";
    } else if (tipo == 'success') {
        class_alert = "alert-success";
        icon_alert = 'fa-check';
        titulo_alert = "Exito";
    } else if (tipo == 'warning') {
        class_alert = "alert-warning";
        icon_alert = 'fa-warning';
        titulo_alert = "Advertencia";
    }    
    var alert = "<div class='alert " + class_alert + " alert-dismissible'><h4><i class='icon fa " + icon_alert + "'></i> " + titulo_alert + "</h4>" + mensaje + "</div>"
    $("#" + contenedor).html(alert);
    $("#" + contenedor).delay(5000).hide(600);
    location.href = "#" + contenedor;
}