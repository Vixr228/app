$(function(){

    //Append employee to screen
    const appendEmployee = function (data){
        //console.log(data);
        var employeeCode = '<a href="#" class="employee-link" data-id="' +
            data.id + '">' + data.id + '. ' +data.name + ' ' + data.surname + '</a>';
        $('#employee-list').append('<div>' + employeeCode + '</div>');
    };

    const getAllEmployees = function (){
        $.get('/employees/', function (response){
            for(i in response){
                appendEmployee(response[i]);
            }
        });
    }

    //Get list of Employee
    getAllEmployees();

    //Show employee form
    $('#show-add-employee-form').click(function (){
        $('#employee-form').css('display', 'flex');
    });


    //Save employee
    $('#save-employee').click(function (){
        var data = $('#employee-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/employees/',
            data: data,
            success: function (response){
                $('#employee-form').css('display', 'none');
                var employee = {};
                employee.id = response;

                var dataArray = $('#employee-form form').serializeArray();
                for(i in dataArray){
                    employee[dataArray[i]['name']] = dataArray[i]['value'];
                }

                console.log("before append");
                appendEmployee(employee)
            }
        });
        //location.reload();
        return false;
    });

    //get Employee
    $(document).on('click', '.employee-link', function (){
        return false;
    });

    $('#delete-employee').click(function (){
        var empId = +document.getElementById('delete-employee-input').value;
        console.log(empId);
        $.ajax({
            method: "DELETE",
            url: '/employees/' + empId,
            success: function (response){
              getAllEmployees();
            }
        });
        //location.reload();
        return false;

    });

});