/**
 * Created by sachin on 17/4/16.
 */
// $("#nav-home-btn").click(function (e) {
//     // e.preventDefault();
//     $("#wrapper").addClass("toggled");
// });
$("#nav-res-btn").click(function (e) {

    var resources = ['Reserve resource', 'View/Cancel reserved resources'];
    var links = ['reserve_resource', 'view_resources'];
    var userType = sessionStorage.getItem('userType').toLowerCase();
    if(userType == 'staff'){
        resources = ['Add new resource'].concat(resources);
        links = ['add_resource'].concat(links);
    }
    
    makeList(e, "Resources", resources, links);
});
$("#nav-crs-btn").click(function (e) {
    var courses = ['View courses', 'Course Info'];
    var links = ['courses', 'course_info'];
    var userType = sessionStorage.getItem('userType').toLowerCase();
    if(userType == 'faculty'){
        courses = ['Create course'].concat(courses);
        links = ['create_course'].concat(links);
    }
    
    makeList(e, "Courses", courses, links);
});
$("#nav-post-btn").click(function (e) {
    var post = ['Announcements', 'Discussion board', 'Exam offering', 'Results'];
    var links = ['announcements', 'discussion_board', 'exams', 'results'];
    makeList(e, "Posts", post, links);
});
// $("#nav-student-btn").click(function (e) {
//
//     var students = ['View Ph.D Students', 'View Alumni Students'];
//     var links = ['phd_students', 'alumni'];
//     makeList(e, "Students", students, links);
// });
function makeList(e, title, lst, links) {

    e.preventDefault();
    $("#wrapper").removeClass("toggled");

    var cList = $('ul.sidebar-nav');
    cList.empty();
    var brand = $('<li/>').addClass('sidebar-brand').text(title).appendTo(cList);
    $.each(lst, function (i) {
        var li = $('<li/>').appendTo(cList);
        $('<a/>', {href: links[i]}).text(lst[i]).appendTo(li);
    });
}