$(function (){


    $('#show-add-task-form').click(function (){
        $('#task-form').css('display', 'flex');
    });

    const appendTask = function (data){
        console.log("from append: " + data.topic + " " + data.name);
        var taskCode = '<a href="#" class="task-link" data-id="' +
            data.id + '">' + data.id + '. ' + data.topic + '</a>';
        $('#task-list').append('<div>' + taskCode + '</div>');
    };

    //Get list of Employee
    const getAllTask = function (){
        $.get('/tasks/', function (response){
            for(i in response){
                appendTask(response[i]);
            }
        });
    }

    getAllTask();




    $('#save-task').click(function (){
        var data = $('#task-form form').serialize();
        console.log(data.name);
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function (response){
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray){
                    console.log("from ajax: " + task[dataArray[i]['name']]);
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                console.log("append, bro");
                appendTask(task)
            },
            error: function (response){
                console.log("wtf, dude");
            }
        });
        //location.reload();
        return false;
    });

    $('#delete-task').click(function (){
        var taskId = +document.getElementById('delete-task-input').value;
        console.log(taskId);
        $.ajax({
            method: "DELETE",
            url: '/tasks/' + taskId,
            success: function (response){
                getAllTask();
            }
        });
        //location.reload();
        return false;

    });

});