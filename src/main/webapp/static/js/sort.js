for (let element of document.getElementsByClassName("dropdown-menu")) {
    element.addEventListener("hidden.bs.dropdown", redirect);
}

function getQueryVariable(variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i=0;i<vars.length;i++) {
        let pair = vars[i].split("=");
        if(pair[0] === variable){
            return pair[1];
        }
    }
    return(false);
}

function redirect(event) {
    let url = "localhost:8080/";
    let category = getQueryVariable("category");
    let supplier = getQueryVariable("supplier");
    if (category && supplier) {

        url += "?category=" + category + "&?supplier=" + category;
    }
    console.log(event.target);

    window.location.replace(url);
}