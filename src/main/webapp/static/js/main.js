function init() {
    if(performance.navigation.type == 2){
        location.reload(true);
    }
    index.checkCartItems();
    index.addToCart();
    index.emptyCart();
}

init();