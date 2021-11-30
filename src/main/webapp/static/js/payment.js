
function main() {

    const cardBtn = document.querySelector(".cardBtn");
    const payPalBtn = document.querySelector(".paypalBtn");

    cardBtn.addEventListener("click", cardBtnClickEventHandler);
    payPalBtn.addEventListener("click", payPalBtnClickEventHandler);
}


main();