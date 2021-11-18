function closeModal() {
    let d = document.getElementById("custom-modal")
    d.style.display = "none"
}

function openModal() {
    let d = document.getElementById("custom-modal")
    d.style.display = "block"
}


function addToCart(productId, header, token) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/add-to-cart", true);
    xhr.setRequestHeader(header, token);
    xhr.send(productId.toString());
    xhr.close();
}


function deleteCartItem(product_id, header, token) {
    if (confirm("Want to delete cart item?")) {
        // document.getElementById('1').remove();
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/delete-cart-item", true);
        xhr.setRequestHeader(header, token);
        xhr.send(product_id.toString());
        xhr.close();
    }
}

function clearCart(header, token) {
    let items = document.getElementsByClassName("items");

    if (confirm("Are you sure to clear cart!")) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/clear-cart", true);
        xhr.setRequestHeader(header, token);
        xhr.send();
        xhr.close();

        while (items.length > 0) {
            items[0].remove();
        }
        // document.getElementById("cost").innerHTML = "'Total Amount: $ 0.00"
    }
}

function increaseQuantity(product_id, header, token) {
    // let quantity = document.getElementById(`q${product_id}`).innerText;
    // document.getElementById(`q${product_id}`).innerHTML = parseInt(quantity) + 1;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/increase-quantity", true);
    xhr.setRequestHeader(header, token);
    xhr.send(product_id.toString());
    xhr.close();
}

function decreaseQuantity(product_id, header, token) {
    // let quantity = document.getElementById(`q${product_id}`).innerText;
    // document.getElementById(`q${id}`).innerHTML = parseInt(quantity) <= 0 ? 0 : parseInt(quantity) - 1;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/decrease-quantity", true);
    xhr.setRequestHeader(header, token);
    xhr.send(product_id.toString());
    xhr.close();
}