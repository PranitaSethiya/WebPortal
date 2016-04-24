/**
 * Created by sachin on 23/4/16.
 */

$(function () {
    $('#add_datetimepicker_event').datetimepicker({
        minDate: new Date()
    });
});

function updateEventView() {
    if($('#add_modal_type').val() == "Event") {
        $('#add_datetimepicker_event_parent').show();
        $('#add_modal_venue_parent').show();
    } else {
        $('#add_datetimepicker_event_parent').hide();
        $('#add_modal_venue_parent').hide();
    }
}

function deleteAnnouncement(announcement) {

    var html =
        '<div id="deleteModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Delete Announcement</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure you want to delete Announcement <b>' + announcement['name'] + '</b>?' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        "<button type='button' class='btn btn-danger' id='deleteAnnouncementButton' >Yes</button>" +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#deleteModalHere').html(html);
    $('#deleteAnnouncementButton').click(function () {
        deleteActionAnnouncement(announcement['name']);
    });
    $('#deleteModal').modal();
}

function deleteActionAnnouncement(announcement_name) {
    sendPost("delete", announcement_name);
}

function editAnnouncement(announcement) {
    var html =
        '<div id="editModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Announcement</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>' +
        '<label for="edit_modal_name">Name</label>' +
        '<input id="edit_modal_name" name="name" type="text" class="form-control" placeholder=""' +
        'autofocus value="' + announcement['name'] + '">' +
        '<label for="edit_modal_homepage">Homepage</label>' +
        '<input id="edit_modal_homepage" name="homepage" type="text" class="form-control"' +
        'placeholder="http://announcement.xyz" value=' + announcement['homepage'] + '>' +
        '<label for="edit_modal_description">Description</label>' +
        '<textarea id="edit_modal_description" name="description" type="text"' +
        'class="form-control">' + announcement['description'] + '</textarea>' +
        '<label for="edit_modal_image">Image</label>' +
        '<input id="edit_modal_image" name="image" type="text" class="form-control"' +
        'placeholder="http://announcement.xyz/logo.png" value=' + announcement['image'] + '>' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" onclick="editSaveAnnouncement();">Update</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#editModalHere').html(html);
    $('#editModal').modal();
}

function editSaveAnnouncement() {
    sendPost("update", $('#edit_modal_name').val(), $('#edit_modal_homepage').val(),
        $('#edit_modal_description').val(), $('#edit_modal_image').val());
}

function saveAnnouncement() {
    sendPost("save", $('#add_modal_title').val(), $('#add_modal_details').val(),
        $('#add_modal_link').val(), $('#add_modal_type').val(), $('#add_datetimepicker_event').val(), $('#add_modal_venue').val());
}

function sendPost(action_r, title_r, details_r, link_r, type_r, event_date_r, event_venue_r) {
    console.log(action_r, title_r, details_r, link_r, type_r, event_date_r, event_venue_r);
    $.post("/announcement",
        {
            title: title_r,
            details: details_r,
            link: link_r,
            announcement_type: type_r,
            event_datetime:  event_date_r, 
            event_venue: event_venue_r

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}