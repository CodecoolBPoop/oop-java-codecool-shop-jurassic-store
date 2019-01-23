function init() {
    if(performance.navigation.type == 2){
        location.reload(true);
    }
    shoppingCart.checkCartItems();
    shoppingCart.addToCart();
    shoppingCart.emptyCart();
}

init();