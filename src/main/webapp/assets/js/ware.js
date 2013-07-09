$(function(){
    var map = {},
        url = $("#typeahead-url").val();

    $('#typeahead').typeahead({
        source: function (query, process) {
            return $.get(url, { keyword: query }, function (data) {
                var keys = [];
                $.each(data, function(i, item) {
                    var key = item.id + "_" + item.code + "_" + item.name;
                    keys.push(key);
                    map[key] = item;
                });
                return process(keys);
            });
        },
        updater: function(key) {
            var item = map[key];
            return item.name;
        },
        highlighter: function(key) {
            return $.templates('#query-tpl').render(map[key]);
        }
    });
});

$(function() {
    $(".update").click(function(e) {
        e.preventDefault();
        var url = $(this).attr("href");
        $.get(url, function(data) {
            $("#modal-container").html($.templates("#update-tpl").render(data));
            $('#ware-modal').modal('show');
        });
    });

    $("#create").click(function(e) {
        e.preventDefault();
        $("#modal-container").html($.templates("#create-tpl").render());
        $("#ware-modal").modal("show");
    });

    $("#modal-container").on("submit", "#stock", function(e) {
        e.preventDefault();
        var $form = $(this);
        $.ajax({
            type: "POST",
            url: $form.attr("action"),
            data: $form.serialize(),
            success: function(data) {
                $('#ware-modal').modal('hide');
                $("#modal-container").html($.templates("#success-tpl").render(data));
                $('#success').modal('show');
                setTimeout(function() {
                    location.reload();
                }, 1500);
            },
            error: function() {
                $('#ware-modal').modal('hide');
                $("#modal-container").html($.templates("#error-tpl").render());
                $('#error').modal('show');
            }
        });
    });
});