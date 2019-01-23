let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let productId = clickedBtn.id.replace("btn", "");

                let http = new XMLHttpRequest();
                http.open("GET", "http://127.0.0.1:8080/cart-api?prodId=" + productId + "&action=add", true);
                http.send();
                http.onreadystatechange = function () {
                    if (http.readyState == 4 && http.status == 200) {
                        console.log("szákszeksz");
                    }
                }
                })
        }
    }
};