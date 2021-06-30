$(function (){

    //Department
    //show book form
    $('#show-add-department-form').click(function (){
        $('#department-form').css('display', 'flex');
    });

    const appendDepartment = function (data){
        var organizationCode = '<a href="#" class="department-link" data-id="' +
            data.id + '">' + data.id + '. ' + data.name + '</a>';
        $('#department-list').append('<div>' + organizationCode + '</div>');
    };

    const getAllDepartments = function (){
        //Get list of Employee
        $.get('/departments/', function (response){
            for(i in response){
                appendDepartment(response[i]);
            }
        });
    }

    getAllDepartments();


    $('#save-department').click(function (){
        var data = $('#department-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/departments/',
            data: data,
            success: function (response){
                $('#organization-form').css('display', 'none');
                var department = {};
                department.id = response;
                var dataArray = $('#department-form form').serializeArray();
                for(i in dataArray){
                    department[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendDepartment(department)
            },
            error: function (response){
                console.log("wtf, dude");
            }
        });
        //location.reload();
        return false;
    });

    $('#delete-department').click(function (){
        var depId = +document.getElementById('delete-department-input').value;
        console.log(depId);
        $.ajax({
            method: "DELETE",
            url: '/departments/' + depId,
            success: function (response){
                getAllDepartments();
            }
        });
        //location.reload();
        return false;

    });

});