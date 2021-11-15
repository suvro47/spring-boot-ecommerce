
function closeModal(){
    let d = document.getElementById("custom-modal")
    d.style.display = "none"
}
function openModal(){
    let d = document.getElementById("custom-modal")
    d.style.display = "block"
}

function deleteCartItem(id){
    document.getElementById(id).remove();
}

function increaseQuantity(id){
    let quantity = document.getElementById(`q${id}`).innerText;
    document.getElementById(`q${id}`).innerHTML = parseInt(quantity) + 1;

    // let xhr = new XMLHttpRequest();
    // xhr.open("POST", "http://localhost:8080/cart-item-quantity", true)
    //
    // let data = {
    //     "id": "1",
    //     "name": "Grocery"}
    // xhr.setRequestHeader("content-type", "application/json")
    // xhr.responseType = "json"
    // xhr.send(JSON.stringify(data));

}
function decreaseQuantity(id){
    let quantity = document.getElementById(`q${id}`).innerText;
    document.getElementById(`q${id}`).innerHTML = parseInt(quantity) <= 0 ? 0 : parseInt(quantity) -1;
}

