/**
 * Created by sachin on 17/4/16.
 */
$("#nav-home-btn").click(function (e) {
    // e.preventDefault();
    $("#wrapper").addClass("toggled");
});
$("#nav-res-btn").click(function (e) {
    var resources = ['Add new resource', 'Reserve resource', 'View/Cancel reserved resources'];
    var links = ['add_resource', 'reserve_resource', 'view_resources'];
    makeList(e, "Resources", resources, links);
});
$("#nav-crs-btn").click(function (e) {
    var courses = ['Create course', 'View courses'];
    var links = ['create_course', 'view_courses'];
    makeList(e, "Courses", courses, links);
});
$("#nav-post-btn").click(function (e) {
    var post = ['Announcements', 'Discussion board', 'Exam offering', 'Results'];
    var links = ['announcements', 'discussion_board', 'exams', 'results'];
    makeList(e, "Posts", post, links);
});
$("#nav-student-btn").click(function (e) {

    var students = ['View PhD Students', 'View Alumni Students'];
    var links = ['phd_students', 'alumni_students'];
    makeList(e, "Students", students, links);
});
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