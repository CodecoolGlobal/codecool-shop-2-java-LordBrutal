function addCartbutton(){
    const buttons = document.querySelectorAll("#add-card")
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", addToCart)
    }
}

function addToCart(){
    console.log("test")
}



addCartbutton();

