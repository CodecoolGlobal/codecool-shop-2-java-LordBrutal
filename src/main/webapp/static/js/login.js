export function loginButton(){
    const loginButton = document.querySelector("#login-btn");
    loginButton.addEventListener("click", loginButtonEvent);
}

function loginButtonEvent(){
    const modal = document.querySelector("#log-modal")
    modal.style.display = "block";
    closeLogModal();
}

function closeLogModal(){
    const closeButton = document.querySelector("#close-log-modal")
    console.log(closeButton)
    closeButton.addEventListener("click", closeModal);
}

function closeModal(){
    console.log("close")
    const modal = document.querySelector("#log-modal")
    modal.style.display = "none";
}

