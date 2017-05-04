$(document).ready(function() {
    // Utilizados nos alerts da aplicação
    setTimeout(function() {
        $(".alert-timer").slideUp()();
    }, 3000);
    
    // Utilizado na pagina de cadastro do usuario
    $("#dispostoReceber").on("change", function() {
        if(this.checked)
            $("#divQuantReceber").show();
        else
            $("#divQuantReceber").hide();
    });
    
    // Utilizado nos rates Yo do index do usuário
    $(function() {
        $(".rate-yo-read").rateYo({
            readOnly: true
        });
    });
    
    $("#media-notas-amigos").on("click", function () {
       $("#avaliacoes-amigos").slideToggle()();
    });
    $("#media-notas-hospede").on("click", function () {
       $("#avaliacoes-hospede").slideToggle()();
    });
    $("#media-notas-anfitriao").on("click", function () {
       $("#avaliacoes-anfitriao").slideToggle()();
    });
    $("#media-notas-leva").on("click", function () {
       $("#avaliacoes-leva").slideToggle()();
    });
    $("#media-notas-participa").on("click", function () {
       $("#avaliacoes-participa").slideToggle()();
    });
});
