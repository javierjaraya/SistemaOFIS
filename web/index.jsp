<%-- 
    Document   : index
    Created on : 13-07-2017, 3:59:23
    Author     : 
--%>

<%@ include file="headerVisitante.jsp" %>  

<!--Slideshow Part Start-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin-bottom: 15px;">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="/SistemaOFIS/img/slider/1.jpg" alt="...">
            <div class="carousel-caption">
                <!-- Texto descripcion aqui -->
            </div>
        </div>
        <div class="item">
            <img src="/SistemaOFIS/img/slider/2.jpg" alt="...">
            <div class="carousel-caption">
                <!-- Texto descripcion aqui -->
            </div>
        </div>
        <div class="item">
            <img src="/SistemaOFIS/img/slider/3.jpg" alt="...">
            <div class="carousel-caption">
                <!-- Texto descripcion aqui -->
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Anterior</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Siguiente</span>
    </a>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.carousel').carousel();
    });
</script>
<!--Slideshow Part End-->

<%@ include file="footerVisitante.jsp" %>       
