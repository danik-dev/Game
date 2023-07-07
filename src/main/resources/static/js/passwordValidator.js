const pInputField1 = document.getElementById("p-input-field-1");
const pInputField2 = document.getElementById("p-input-field-2");
const submitButton = document.getElementById("submit-btn");
const form = document.querySelector(".sign-up-form");

let requirementsPassed, passwordsEqual;

const requirementList = document.querySelectorAll(".pass-requirement-list li");
const requirements= [
    {regex: /.{8,}/, index: 0},
    {regex: /[0-9]/, index: 1},
    {regex: /[a-z]/, index: 2},
    {regex: /[^A-Za-z0-9]/, index: 3},
    {regex: /[A-Z]/, index: 4}
];

pInputField1.addEventListener("keyup", (e) => {
    requirementsPassed = true;
    requirements.forEach(item => {
        const isValid = item.regex.test(e.target.value);
        const requirementItem = requirementList[item.index];
        
        if (isValid) {
            requirementItem.firstElementChild.className = "check";
            requirementItem.classList.add("valid");
        } else {
            requirementItem.firstElementChild.className = "uncheck";
            requirementItem.classList.remove("valid");
            requirementsPassed = false;
        }
    }) 
});

form.addEventListener("keyup", function(event){
    if (event.target.closest(".pass-input-field")) {
        submitButton.disabled = true;
        passwordsEqual = pInputField1.value === pInputField2.value;
        if (passwordsEqual === true && requirementsPassed === true) {
            submitButton.disabled = false;
        }
        // console.log(passwordsEqual ? "equal" : "not equal");
        // console.log(requirementsPassed ? "passed" : "not passed");
    }
});

// function changeVisible() {
//     pInputField1.type = pInputField1.type === "password" ? "text" : "password";
// }