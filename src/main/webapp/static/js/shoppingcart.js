let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-danger");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let productId = clickedBtn.id.replace("btn", "");
                let action = clickedBtn.innerText.toLocaleLowerCase();
                let counter = document.getElementById("counter" + productId);
                let count = parseInt(counter.innerText);
                console.log(count);
                let allow = false;
                if (count > 1 && action != "add"){
                    allow = true}
                if (allow || action == "add") {
                    let http = new XMLHttpRequest();
                    http.open("GET", "http://127.0.0.1:8080/cart-api?prodId=" + productId + "&action=" + action, true);
                    http.send();
                    http.onreadystatechange = function () {
                        if (http.readyState == 4 && http.status == 200) {
                            console.log("szákszeksz");
                            if (action == "add") {

                                counter.innerText = count + 1;
                            } else {
                                counter.innerText = count - 1;
                            }
                        }
                    }
                }
            })
        }
    },
    
    emptyCart: function () {
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
    }
};

shoppingCart.addToCart();