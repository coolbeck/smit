/**
 * Created by Admin on 19/06/2017.
 */
$(function() {
    insertNewBook("Harry Potter");
    insertNewBook("Don Quixote");
    insertNewBook("The Odyssey");
    showAvailableBooks();
    showUnavailableBooks();

    $("#btnInsertNewBook").click(function()
    {
        insertNewBook($('#newBook').val());
        showAvailableBooks();
    });
});

function insertNewBook(name){
    $.ajax({
        type: 'POST',
        url:    "/service/saveNewBook/"+name,
        success: function(result) {
            if(result.isOk == false)
                alert(result.message);
        },
        async:   false
    });
}

function showAvailableBooks(){
    $.get("/service/getAvailableBooks", function(data, status){
        var html = '<div class="row header">Available books</div>';
        $.each(data, function(i, item){
            html += '<div class="row">' + item.name + '' +
                '<span class="actions"><a href="#" data-id="'+item.id+'" data-status="unavailable" onclick="changeStatus(this)">Order this book</a></span></div>';
        });
        $('#available').html(html);
    });
}

function showUnavailableBooks(){
    $.get("/service/getUnavailableBooks", function(data, status){
        var html = '<div class="row header">Unavailable books</div>';
        $.each(data, function(i, item){
            html += '<div class="row">' + item.name +
                '<span class="actions"><a href="#" data-id="'+item.id+'" data-status="available" onclick="changeStatus(this)">Return this book</a></span></div>';
        });
        $('#unavailable').html(html);
    });
}

function changeStatus($item){
    var id = $($item).data('id');
    var status = $($item).data('status');
    $.post("/service/updateStatus/"+id + "/"+status, function(data, status){
        if(status == 'success'){
            showAvailableBooks();
            showUnavailableBooks();
        }
    });
}