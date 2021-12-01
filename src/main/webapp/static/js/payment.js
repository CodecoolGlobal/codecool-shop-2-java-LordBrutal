
function main() {

    const cardBtn = document.querySelector(".cardBtn");
    const payPalBtn = document.querySelector(".paypalBtn");

    cardBtn.addEventListener("click", cardBtnClickEventHandler);
    payPalBtn.addEventListener("click", payPalBtnClickEventHandler);
}
function cardBtnClickEventHandler(event) {
    const cardModal = document.querySelector("#paymentModal");
    const cardModalContent = document.querySelector(".modal-content");
    cardModalContent.innerHTML = "";
    cardModalContent.innerHTML = `<span class="close">&times;</span>
            <div class="card_credentials">
                <form action="#">
                <label for="card_number">Card number</label><br>
                <input type="number" id="card_number" placeholder="1234567890" required><br>
                <label for="card_holder">Card holder</label><br>
                <input type="text" id="card_holder" placeholder="John Doe" required><br>
                <label for="expiry_date">Expiration date</label><br>
                <div class="exp-wrapper" id="expiry_date">
                    <input autocomplete="off" class="exp" id="month" maxlength="2" pattern="[0-9]*" inputmode="numerical" placeholder="MM" type="text" data-pattern-validate />
                    <input autocomplete="off" class="exp" id="year" maxlength="2" pattern="[0-9]*" inputmode="numerical" placeholder="YY" type="text" data-pattern-validate />
                </div>
                <label for="cvv_code">CVV code</label><br>
                <input id="cvv_code" type="password" required><br>
                <button type="button">Submit</button>
                </form>

            </div>`
    cardModal.style.display = "block";
}


main();