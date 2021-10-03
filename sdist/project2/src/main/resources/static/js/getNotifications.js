$(document).ready(function(){
    $("#sub").click(function(){
        $('ul').empty();
        
        var $notifications = $('#notifications');

        $.ajax({
            type: 'GET',
            url: '/notifications',
            data:{
                email: $("#email").val(),
                password: $("#password").val()
            },
            success: function(notifications) {

                $("#div").remove();

                if(notifications.length > 0)
                {
                    $.each(notifications, function(i, notification) {
                        $notifications.append('<li>' + notification.message + '</li>');
                    });
                }
                else
                {
                    $notifications.html("Não existem notificações.");
                }
            }
        });
    })
});