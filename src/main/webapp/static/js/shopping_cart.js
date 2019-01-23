let shoppingCart = {
    addToCart: function () {
        let cartBtns = document.getElementsByClassName("btn-cart");
        for (let i = 0; i < cartBtns.length; i++) {
            cartBtns[i].addEventListener("click", function () {
                let clickedBtn = event.target;
                let productId = clickedBtn.value;
                fetch("/cart-api", {
                    method: 'POST',
                    body: JSON.stringify({"prodId": productId, "action": "add"}),
                    headers:{
                        'Content-Type': 'application/json'
                    }
                }).then(res => res.json())
                    .then(response => console.log('Success:', JSON.stringify(response)))
                    .catch(error => console.error('Error:', error));
            })
        }
    }
}