$(function(){
    var map = {},
        index= 0,
        url = $("#typeahead-url").val(),
        refreshTooltip = function() {
            $('[data-toggle="tooltip"]').tooltip({placement: "top"});
        };

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
            var item = map[key],
                html,
                $total = $("#total"),
                total;

            total = (+$total.text()) + item.price.retail;

            $total.text(total);
            item.index = index++;
            html = $.templates('#item-tpl').render(item);
            $('table').append(html);
            refreshTooltip();
            return "";
        },
        highlighter: function(key) {
            return $.templates('#query-tpl').render(map[key]);
        }
    });

    $('.cart').on('click', '.btn-danger', function(e) {
        e.preventDefault();
        var $e = $(this),
            id = $e.attr('data-id'),
            subtotalRef = '#' + id + '-subtotal',
            $total = $('#total'),
            total;

        total = (+$total.text()) - (+$(subtotalRef).text());
        $total.text(total);

        $(this).parent().parent().remove();
    });

    $('.cart').on('change', '.change-price', function() {
        var id = $(this).attr('id').split('-')[0],
            quantityRef = '#' + id + '-quantity',
            priceRef = '#' + id + '-price',
            subtotalRef = '#' + id + '-subtotal',
            increment,
            subtotal,
            total;

        subtotal = $(quantityRef).val() * $(priceRef).val();
        increment = subtotal - (+$(subtotalRef).text());
        total = (+$("#total").text());
        total = total ? total + increment : subtotal;

        $(subtotalRef).text(subtotal);
        $('#total').text(total);
    });
});

$(function() {
    $(".update").click(function(e) {
        e.preventDefault();
        var url = $(this).attr("href");
        $.get(url, function(data) {
            $("#modal-container").html($.templates("#update-tpl").render(data));
            $('#update-modal').modal('show');
        });
    });

    $("#modal-container").on("submit", "#stock", function(e) {
        e.preventDefault();
        var $form = $(this);
        $.ajax({
            type: "POST",
            url: $form.attr("action"),
            data: $form.serialize(),
            success: function(data) {
                $('#update-modal').modal('hide');
                $("#modal-container").html($.templates("#success-tpl").render(data));
                $('#success').modal('show');
                setTimeout(function() {
                    location.reload();
                }, 1500);
            },
            error: function() {
                $('#update-modal').modal('hide');
                $("#modal-container").html($.templates("#error-tpl").render());
                $('#error').modal('show');
            }
        });
    });
});



