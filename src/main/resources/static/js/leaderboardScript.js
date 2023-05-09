const tabButtons = document.querySelectorAll('.tab-button');
const tabContents = document.querySelectorAll('.tab-content');

function showTab(tabIndex) {
    tabContents.forEach(tabContent => {
        tabContent.classList.remove('active');
    });
    tabContents[tabIndex].classList.add('active');
}

tabButtons.forEach((tabButton, tabIndex) => {
    tabButton.addEventListener('click', () => {
        tabButtons.forEach(tabButton => {
            tabButton.classList.remove('active');
        });
        tabButton.classList.add('active');
        showTab(tabIndex);
    });
});