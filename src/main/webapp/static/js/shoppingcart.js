let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                if (clickedBtn.dataset.action === "add") {
                    let productId = parseInt(clickedBtn.dataset.id);
                    let dataToSend = {"prodId": productId, "action": "add"};
                    $.ajax({
                        url: "/cart-api",
                        type: "POST",
                        data: dataToSend,
                        success: function (data) {
                            if (clickedBtn.id === "quantity" + productId.toString()) {
                                let jsonData = JSON.parse(data);
                                let plusItem = document.getElementById("quantity" + jsonData["productId"]);
                                plusItem.innerText = jsonData["prodQuantity"];
                            }
                        }
                    })
                } else {
                    let productId = parseInt(clickedBtn.dataset.id);
                    let dataToSend = {"prodId": productId, "action": "remove"};
                    $.ajax({
                        url: "/cart-api",
                        type: "POST",
                        data: dataToSend,
                        success: function (data) {
                            if (clickedBtn.id === "remove" + productId.toString()) {
                                let jsonData = JSON.parse(data);
                                let minusItem = document.getElementById("quantity" + jsonData["productId"]);
                                if(jsonData["prodQuantity"]!=null) {
                                    minusItem.innerText = jsonData["prodQuantity"];
                                } else {
                                    let removeElem = document.getElementById("cont" + jsonData["productId"]);
                                    removeElem.parentNode.removeChild(removeElem);
                                }
                            }
                        }
                    })
                }




            })
        }
    },
    
    /*emptyCart: function () {
        let clearCart = document.getElementById('clearCart');
        clearCart.addEventListener('click', function () {
            let http = new XMLHttpRequest();
            http.open("GET", "http://127.0.0.1:8080/cart-api?prodId=" + 0 + "&action=removeAll", true);
            http.send();
            http.onreadystatechange = function () {
                if (http.readyState == 4 && http.status == 200) {
                    console.log("szákszeksz");
                    }
                }
            })
    }*/
};

shoppingCart.addToCart();