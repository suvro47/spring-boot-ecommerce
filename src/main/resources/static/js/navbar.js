$('.ui.vertical.menu').hide();
$('.ui.dropdown').dropdown();
$('.ui.accordion').accordion();
$(document).ready(function () {
    $("html").click((event) => {
        if (event.target.id === "menu-toggle-button" || $(event.target).parents("#menu-toggle-button").length) {
            $('.ui.vertical.menu').toggle("250", "linear")
        } else if (!(event.target.id === "menu" || $(event.target).parents("#menu").length)) {
            $('.ui.vertical.menu').hide("250", "linear")
        }
    });
});
function caretChanger(event){
    console.log("came here.")
    let caretIcon = document.getElementById("categories-caret");
    if(caretIcon.classList.contains("down")){
        caretIcon.classList.remove("down");
        caretIcon.classList.add("up");
    } else if(caretIcon.classList.contains("up")){
        caretIcon.classList.remove("up");
        caretIcon.classList.add("down");
    }
}