/**
 * Created by sachin on 22/4/16.
 */
function saveAlumni() {
    sendPost("save", $('#add_modal_name').val(), $('#add_modal_homepage').val(), 
        $('#add_modal_description').val(), $('#add_modal_image').val());
}

function sendPost(action_r, name_r, homepage_r, description_r, image_r) {
    $.post("/alumni",
        {
            action: action_r,
            name: name_r,
            homepage: homepage_r,
            description: description_r,
            image: image_r

        }, function(data, status) {
            if(data == "success"){
                location.reload();
            } else {
                $.toaster({ priority : 'danger', title : 'Error', message : 'Failed. Please try again.'});
            }
        });
}