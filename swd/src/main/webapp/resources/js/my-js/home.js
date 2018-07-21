$(document).ready(function () {
    function generateItem(item) {
        var html = '<div class="item  col-xs-4 col-lg-4"><div class="thumbnail">' +
                '<img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />' +
                '<div class="caption">' +
                '<h4 class="group inner list-group-item-heading">' + item.name + '</h4>' +
                '<p class="group inner list-group-item-text">' + item.decription + '</p>' +
                '<div class="row">' +
                ' <div class="col-xs-12 col-md-6">' +
                '     <p class="lead">' + item.price + '</p>' +
                '</div>' +
                ' <div class="col-xs-12 col-md-6">' +
                '<a class="btn btn-success" href="http://www.jquery2dotnet.com">Add to cart</a>' +
                ' </div>' +
                '</div>' +
                '</div>' +
                '</div></div>';
        return html;
    }
    function addNewItem() {
        var name = $("#txtname").val();
        var description =  $("#txtdescription").val();
        var price = $("#txtprice").val();
        var item = {
            "name" :  name,
            "decription": description, 
            "price" : price
        };
        $.ajax({
            dataType: "json",
            type: 'POST',
            data: JSON.stringify(item),
            contentType: "application/json",
            url: "/Lab12-web/ShoppingCart/item/add",
            success: function (data) {
                getListProduct();
            },
            error: function (data) {

            }
        });
    }
    function proccessAction() {
        $('#btnAddNew').click(function () {
            $("#dialog").dialog({
                resizable: false,
                height: "auto",
                width: "auto",
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                },
                modal: true,
                buttons: {
                    "Save": function () {
                        addNewItem();
                        $(this).dialog("close");
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
    function showItem(listItem) {
        $.each(listItem, function (key, value) {
            $("#products").append(generateItem(value));
        });
    }
    function getListProduct() {
        $.ajax({
            dataType: "json",
            type: 'GET',
            url: "/Lab12-web/ShoppingCart/item/list",
            contentType: "application/json",
            success: function (data) {
                $("#products").empty();
                showItem(data);
            },
            error: function (data) {

            }
        });
    }
    getListProduct();
    proccessAction();
});


