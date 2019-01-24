let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let productId;
                if (clickedBtn.dataset.action === "add") {
                    if (clickedBtn.id.includes("btn")) {
                        productId = parseInt(clickedBtn.dataset.id);
                    } else {
                        productId = parseInt(clickedBtn.dataset.id);
                    }
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
                    productId = parseInt(clickedBtn.dataset.id);
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
                                    console.log(jsonData["productId"]);
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

    }