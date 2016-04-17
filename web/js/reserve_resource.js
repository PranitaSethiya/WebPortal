/**
 * Created by sachin on 17/4/16.
 */
function updateMinDate() {
    $('.resource_additional').attr("min", new Date().toISOString().slice(0,10));
}