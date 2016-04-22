/**
 * Created by sachin on 22/4/16.
 */
$(function () {
    $('#keywords').tablesorter();
});

function showModal(title, content, buttonPositive, buttonNegative) {

    var html = '<div id="myModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">' + title + '</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>' + content + '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal">' + buttonPositive + '</button>';

    if (buttonNegative != undefined)
        html += '<button type="button" class="btn btn-danger" data-dismiss="modal">' + buttonNegative + '</button>';
    
    html +=
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    $('#modalHere').html(html);
    $('#myModal').modal();

}
