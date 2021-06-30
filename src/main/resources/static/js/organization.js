$(function (){

    //ORGANIZATION
    //show book form
    $('#show-add-organization-form').click(function (){
        $('#organization-form').css('display', 'flex');
    });

    const appendOrganization = function (data){
        var organizationCode = '<a href="#" class="organization-link" data-id="' +
            data.id + '" >'+ data.id + '. ' + data.name + '</a>';
        $('#organization-list').append('<div>' + organizationCode + '</div>');
    };


    //Get list of Employee
    const getAllOrganization = function (){
        $.get('/organizations/', function (response){
            for(i in response){
                appendOrganization(response[i]);
            }
        });
    }

    getAllOrganization();



    $('#save-organization').click(function (){
        var data = $('#organization-form form').serialize();
        console.log(data);
        $.ajax({
            method: "POST",
            url: '/organizations/',
            data: data,
            success: function (response){
                $('#organization-form').css('display', 'none');
                var organization = {};
                organization.id = response;
                var dataArray = $('#organization-form form').serializeArray();
                for(i in dataArray){
                    organization[dataArray[i]['name']] = dataArray[i]['value'];
                }

                console.log("before append");
                appendOrganization(organization)
            }
        });
        //location.reload();
        return false;
    });



    //GET EMPLOYEES OF ORGANIZATION
    $(document).on('click', '.organization-link', function (){
        var link = $(this);
        var orgId = link.data('id');
        console.log("orgId = " + orgId);
        $.ajax({
            method: "GET",
            url: '/organizations/employees/' + orgId,
            success: function (response){
                console.log(response.name + " " + response.id);
                for(i in response){
                    var code = '<p>' + response[i].name  + '</p>';
                    link.parent().append(code);
                }

            },
            error: function (response){
                if(response.status == 404){
                    alert("Not found");
                }
                if(response.status == 500){
                    alert("Server error");
                }
            }
        });
        return false;
    });

    $('#delete-organization').click(function (){
        var orgId = +document.getElementById('delete-organization-input').value;
        $.ajax({
            method: "DELETE",
            url: '/organizations/' + orgId,
            success: function (response){
                getAllOrganization();
            }
        });
        //location.reload();
        return false;

    });

});