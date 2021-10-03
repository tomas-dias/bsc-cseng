$(document).ready(function(){
    $("#sub").click(function(){
        $('ul').empty();
        
        var $vaccinated = $('#vaccinated');

        $.ajax({
            type: 'GET',
            url: '/vaccinated',
            data:{
                date: $("#date").val()
            },
            success: function(vaccinated) {
                $.each(vaccinated, function(key, value) {
                    $vaccinated.append('<li>' + key + ': ' + value + '</li>');
                });
            }
        });
    })
});