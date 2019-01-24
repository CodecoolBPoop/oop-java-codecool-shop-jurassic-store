let shoppingCart = {
    modifyCart: function () {
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
                            let jsonData = JSON.parse(data);
                            shoppingCart.handleAdding(jsonData);
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
                                shoppingCart.handleRemoving(jsonData);
                            }
                        }
                    })
                }
            })
        }
    },

    handleAdding: function(jsonData) {
        let plusItem = document.getElementById("quantity" + jsonData["productId"]);
        plusItem.innerText = jsonData["prodQuantity"] + "pcs";
        document.getElementById("sumPrice").innerText = jsonData["sumPrice"];
    },

    handleRemoving: function(jsonData) {
        let minusItem = document.getElementById("quantity" + jsonData["productId"]);
        if(jsonData["prodQuantity"]!=null) {
            minusItem.innerText = jsonData["prodQuantity"] + "pcs";
        } else {
            let removeElem = document.getElementById("cont" + jsonData["productId"]);
            removeElem.parentNode.removeChild(removeElem);
        }
        document.getElementById("sumPrice").innerText = jsonData["sumPrice"];
    },

    emptyCart: function () {
        let clearCart = document.getElementById('clearCart');
        clearCart.addEventListener('click', function () {
            let http = new XMLHttpRequest();
            http.open("POST", "http://127.0.0.1:8080/cart-api?prodId=" + 0 + "&action=removeAll", true);
            http.send();
            http.onreadystatechange = function () {
                if (http.readyState == 4 && http.status == 200) {
                    let itemCount = document.getElementById('itemCount');
                    itemCount.style.display = "none";
                    itemCount.innerText = "";
                }
            }
        })
    }
};

shoppingCart.modifyCart();