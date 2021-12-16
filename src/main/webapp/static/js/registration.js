var modal = document.getElementById("reg-modal");

// Get the button that opens the modal
var btn = document.getElementById("registration");

// Get the <span> element that closes the modal
var span = document.getElementById("close-reg-modal");

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

