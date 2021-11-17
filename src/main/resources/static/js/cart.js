function closeModal() {
    let d = document.getElementById("custom-modal")
    d.style.display = "none"
}

function openModal() {
    let d = document.getElementById("custom-modal")
    d.style.display = "block"
}

function deleteCartItem(id) {
    if(confirm("Want to delete cart item?"))
    document.getElementById(id).remove();
}

function increaseQuantity(id, header, token) {
    let quantity = document.getElementById(`q${id}`).innerText;
    document.getElementById(`q${id}`).innerHTML = parseInt(quantity) + 1;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/cart",true);
    xhr.setRequestHeader(header,token);
    xhr.send(id);
}

function decreaseQuantity(id) {
    let quantity = document.getElementById(`q${id}`).innerText;
    document.getElementById(`q${id}`).innerHTML = parseInt(quantity) <= 0 ? 0 : parseInt(quantity) - 1;
}

function clearCart() {
    let items = document.getElementsByClassName("items");

    if(confirm("Are you sure to clear cart!")){
        while (items.length > 0) {
            items[0].remove();
        }
        document.getElementById("cost").innerHTML = "'Total Amount: $ 0.00"
    }
}

function sendRequest(){


    xhr.send("helllooo");
}