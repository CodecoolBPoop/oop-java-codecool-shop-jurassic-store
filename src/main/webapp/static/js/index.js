let index = {
    checkCartItems: function () {
        let itemCount = document.getElementById('itemCount');
        if (itemCount.innerText == 0) {
            itemCount.style.opacity = "0";
            itemCount.innerText = "";
        }
    },

    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let dataToSend = {"prodId": parseInt(clickedBtn.dataset.id), "action": "add"};
                $.ajax({
                        url: "/cart-api",
                        type: "POST",
                        data: dataToSend,
                    success: function (data) {
                        index.itemCountForCartIcon();
                        let cartBtn = document.getElementById("cartLink");
                        cartBtn.href = "/shopping-cart";
                    }
                })
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
                    itemCount.style.opacity = "0";
                    itemCount.innerText = "";
                    let cartBtn = document.getElementById("cartLink");
                    cartBtn.href = "#";
                    }
                }
            })
    },

    itemCountForCartIcon: function () {
        let itemCount = document.getElementById('itemCount');
        itemCount.style.opacity = "1";
        if (itemCount.innerText == "") {
            itemCount.innerText = 1;
        } else {
            itemCount.innerText = parseInt(itemCount.innerText)+1;
        }
    }

};