/**
 * Created by sachin on 22/4/16.
 */
function getStatus(term, year) {
    var current_year = new Date().getYear();
    return (current_year - year);
}