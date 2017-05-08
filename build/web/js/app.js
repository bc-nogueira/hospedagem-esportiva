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
    
    // Utilizado nos forms com calendário
    var configuracaoDatepicker = {
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
        nextText: 'Próximo',
        prevText: 'Anterior'
    };
    
    $("#chegada").datepicker(configuracaoDatepicker);
    $("#chegada").mask('00/00/0000');
    $("#saida").datepicker(configuracaoDatepicker);
    $("#saida").mask('00/00/0000');
});
