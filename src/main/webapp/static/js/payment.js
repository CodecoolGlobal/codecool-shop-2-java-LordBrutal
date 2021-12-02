
function main() {

    const cardBtn = document.querySelector(".cardBtn");
    const payPalBtn = document.querySelector(".paypalBtn");

    cardBtn.addEventListener("click", cardBtnClickEventHandler);
    payPalBtn.addEventListener("click", payPalBtnClickEventHandler);
}
function cardBtnClickEventHandler() {
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
                <button type="button" id="submit_card">Submit</button>
                </form>

            </div>`
    const cardSubmit = document.querySelector("#submit_card");
    cardSubmit.addEventListener("click", async () => {
        const cardNumber = document.querySelector("#card_number");
        const cardHolder = document.querySelector("#card_holder");
        const expirationDateYear = document.querySelector("#year");
        const expirationDateMonth = document.querySelector("#month");
        const cvvCode = document.querySelector("#cvv_code");

        const creditCard = {
            "cardNumber": cardNumber.value,
            "cardHolder": cardHolder.value,
            "expYear": expirationDateYear.value,
            "expMonth": expirationDateMonth.value,
            "cvv": cvvCode.value
        }

        const creditCardJson = JSON.stringify(creditCard);
        console.log(creditCardJson);
        if (creditCardJson!=="{}") {
            const url = `http://localhost:8888/payment/validation?card_infos=${creditCardJson}`;
            let response = await fetchUrl(url);
            if (response===false) {
                window.location.href = "/";
            }
            else {
                window.location.href = "/confirmation";
            }
        }



    })
    cardModal.style.display = "block";

    const closeButton = document.querySelector(".close");
    closeButton.addEventListener("click", () => {
        cardModal.style.display = "none";
    })
}

function payPalBtnClickEventHandler() {
    const cardModal = document.querySelector("#paymentModal");
    let modalContentNodeList = [];

    let labelUserName = createLabelElement("username", "User name");
    let inputUserName = createInputElement("text", "username", true, "John Doe");
    let labelPassword = createLabelElement("user-password", "Password");
    let inputPassword = createInputElement("password", "user-password", true, "");

    modalContentNodeList.push(labelUserName, inputUserName, labelPassword, inputPassword);
}

function createLabelElement(inputId, innerText) {
    let label = document.createElement('label');
    label.innerText = innerText;
    label.for = inputId;
    return label;
}

function createInputElement(type, id, required, placeholder) {
    let input = document.createElement('input');
    input.type =  type;
    input.id = id;
    input.required = required;
    input.placeholder = placeholder;

    return input;
}

async function fetchUrl(url) {
    const rawResponse = await fetch(url);
    return await rawResponse.json();
}

main();