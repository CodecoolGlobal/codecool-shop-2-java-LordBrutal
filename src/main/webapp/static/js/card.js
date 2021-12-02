function addCartbutton(){
    const buttons = document.querySelectorAll("#add-card")
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", addToCart)
    }
}

function addToCart(e){
    const productId = e.target.value;
    const productPiece = e.target.parentNode.children[1].children[0].value;
    const productName = e.target.parentNode.parentNode.parentNode.querySelector(".card-title").innerText
    const item = {
        "id": productId,
        "name": productName,
        "piece": productPiece,
    };
    saveItemIntoStorage(item)
}

function saveItemIntoStorage(item){
    let items = JSON.parse(sessionStorage.getItem("cart"));
    if (items == null){
        items = [item];
        //console.log(items)
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
    cartOpenButton(sessionStorage.getItem("cart"))
}

async function fetchUrl(url) {
    const response = await fetch(url);
    return response.json();
}


function cartOpenButton() {
    console.log(sessionStorage.getItem("cart"))

    let data = fetchUrl("/cart?cart=" + sessionStorage.getItem("cart"));
    data.then(data => {
        console.log(data.cartItems)

    let modalContent = document.querySelector(".modal-content");
    modalContent.innerHTML = "";

    let closeButton = document.createElement("span");
    closeButton.classList.add("close");
    closeButton.innerHTML = "&times;";
    let header = document.createElement("h3");
    header.innerText = "Cart Details";

    modalContent.appendChild(closeButton);
    modalContent.appendChild(header);

    let ul = document.createElement("ul");
    for (let i = 0; i < data.cartItems.length; i++) {
        let li = document.createElement("li");
        li.innerText = data.cartItems[i].name + " " + "id:" +data.cartItems[i].id + " " + "piece: " + data.cartItems[i].pieces
        ul.appendChild(li)
    }
    modalContent.appendChild(ul);

    const modal = document.getElementById("myModal");
    const btn = document.getElementById("myBtn");
    const span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
    });
}

function main() {
    addCartbutton();
    cartOpenButton();
}

main();



