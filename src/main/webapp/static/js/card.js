function addCartbutton(){
    const buttons = document.querySelectorAll("#add-card")
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", addToCart)
    }
}

function addToCart(e){
    const productId = e.target.value;
    const productPiece = e.target.parentNode.children[1].children[0].value;
    const item = {
        "id": productId,
        "piece": productPiece,
    };
    saveItemIntoStorage(item)
}

function saveItemIntoStorage(item){
    let items = JSON.parse(sessionStorage.getItem("cart"));
    if (items == null){
        items = [item];
        console.log(items)
    }else {
        let isOncard = false;
        for (let i = 0; i < items.length; i++) {
            if (items[i].id === item.id){
                items[i].piece = item.piece;
                isOncard = true;
            }
        }
        if (!isOncard){
            items.push(item)
        }
    }
    sessionStorage.setItem("cart", JSON.stringify(items));
    console.log(sessionStorage.getItem("cart"));
}


addCartbutton();

