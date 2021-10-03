$(document).ready(function(){
    $("#sub").click(function(){
        $('ul').empty();
        
        var $centres = $('#centres');

        $.ajax({
            type: 'GET',
            url: '/centres',
            data:{
                date: $("#date").val()
            },
            success: function(centres) {
                if(centres.length > 0){
                    
                    $.each(centres, function(i, centre) {
                        $centres.append('<li>' + centre.name + '</li>');
                    });
                }
                else{
                    $centres.html("Não existem centros disponíveis.");
                }
            }
        });
    })
});