$(function(){

    //Append book to the screen
    const appendBook = function (data){
        console.log(data);
        var bookCode = '<a href="#" class="book-link" data-id="' +
            data.id + '">' + data.name + '</a>';
        $('#book-list').append('<div>' + bookCode + '</div>');
    };

    //Get list of book
    // $.get('/books/', function (response){
    //     for(i in response){
    //         console.log("i" + i);
    //         console.log("response" + response[i]);
    //         appendBook(response[i]);
    //     }
    // });

    //show book form
    $('#show-add-book-form').click(function (){
        $('#book-form').css('display', 'flex');
    });

    //hide book form
    $('#book-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.book-link', function (){
        var link = $(this);
        var bookId = link.data('id');

        $.ajax({
            method: "GET",
            url: '/books/' + bookId,
            success: function (response){
                var code = '<span>Год выпуска' + response.year + '</span>';
                link.parent().append(code);
            },
            error: function (response){
                if(response.status == 404){
                    alert("Not found");
                }
            }
        });
        return false;
    });
    //save book
    $('#save-book').click(function (){
        var data = $('#book-form form').serialize();
        console.log(data);
        $.ajax({
            method: "POST",
            url: '/books/',
            data: data,
            success: function (response){
                $('#book-form').css('display', 'none');
                var book = {};
                book.id = response;
                var dataArray = $('#book-form form').serializeArray();
                for(i in dataArray){
                    book[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBook(book)
            }
        });
        //location.reload();
        return false;
    });
});