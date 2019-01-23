let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let productId = parseInt(clickedBtn.id.replace("btn", ""));
                let dataToSend = {"prodId": productId, "action" : "add"};
                $.ajax({
                    url: "/cart-api",
                    type: "POST",
                    data: dataToSend,
                    success: function () {
                        console.log("success");
                    }
                })
            })
        }

    }


}