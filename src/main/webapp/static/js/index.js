let shoppingCart = {
    checkCartItems: function () {
        let itemCount = document.getElementById('itemCount');
        if (itemCount.innerText == 0) {
            itemCount.style.display = "none";
            itemCount.innerText = "";
        }
    },
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
                            let itemCount = document.getElementById('itemCount');
                            itemCount.style.display = "block";
                            if (itemCount.innerText == "") {
                                itemCount.innerText = 1;
                            } else {
                                itemCount.innerText = parseInt(itemCount.innerText)+1;
                            }
                        }
                    })
            }
            })
        }

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