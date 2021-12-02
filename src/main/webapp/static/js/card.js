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
    if(sessionStorage.getItem("cart") !== null || sessionStorage.getItem("cart") !== undefined) {
        cartOpenButton();
    }
}

async function fetchUrl(url) {
    const response = await fetch(url);
    return response.json();
}


function cartOpenButton() {
    console.log(sessionStorage.getItem("cart"))

    let data = fetchUrl("/cart?cart=" + sessionStorage.getItem("cart"));
    data.then(data => {console.log(data)
        if (data !== null || true) {
    let modalContent = document.querySelector(".modal-content");
        modalContent.innerHTML = "";

    let closeButton = document.createElement("span");
    closeButton.classList.add("close");
    closeButton.innerHTML = "&times;";
    let header = document.createElement("h3");
    header.innerText = "Cart Details";

    let table = document.createElement("table")
        table.setAttribute("id", "customers");
        let tr = document.createElement("tr")
            let thname = document.createElement("th")
            thname.innerText = "Name"
            let thpieces = document.createElement("th")
                thpieces.innerText ="price"
            let thprice = document.createElement("th")
                thprice.innerText = "price"

        tr.appendChild(thname);
        tr.appendChild(thpieces);
        tr.appendChild(thprice);
        table.appendChild(tr);
        for (const element of data.cartItems) {
            let tbody = document.createElement("tr")
                let tdname = document.createElement("td")
                    tdname.innerText = element.name;
            let tdpieces = document.createElement("td")
                tdpieces.innerText = element.piece;
            let tdprice = document.createElement("td")
                tdprice.innerText = element.price;

            tbody.appendChild(tdname);
            tbody.appendChild(tdpieces);
            tbody.appendChild(tdprice);
            table.appendChild(tbody);
        }


            let totalDisplay = document.createElement("h3")
            totalDisplay.setAttribute("id", "total");
            totalDisplay.innerText = data.totalPrice

    modalContent.appendChild(closeButton);
    modalContent.appendChild(header);
    modalContent.appendChild(table);
    modalContent.appendChild(totalDisplay);
    let button = document.createElement("button");
    button.style = "width: max-content"
    button.value = "submit";
    button.innerText = "checkout"
    modalContent.append(button);

    button.addEventListener("click", function() {
        window.location.href = "#";
    } )

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
            }
    });
}

function main() {
    addCartbutton();
    if(sessionStorage.getItem("cart") !== null || sessionStorage.getItem("cart") !== undefined) {
        cartOpenButton();
    }
}

main();



