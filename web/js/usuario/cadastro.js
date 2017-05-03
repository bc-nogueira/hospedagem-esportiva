$(document).ready(function() {
    $("#dispostoReceber").on("change", function() {
        if(this.checked)
            $("#divQuantReceber").show();
        else
            $("#divQuantReceber").hide();
    });
});
