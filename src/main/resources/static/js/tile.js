export class Tile {
    //чтобы привязать плитку к полю
    // startupGeneratedValue
    constructor(gridElement) {
        this.tileElement = document.createElement('div');
        this.tileElement.classList.add('tile');
        this.startupGeneratedValue = Math.random() > 0.5 ? 2 : 4;
        this.setValue(this.startupGeneratedValue);
        gridElement.append(this.tileElement);

    }

    // getValue() {
    //     return this.value;
    // }

    setXY(x, y) {
        this.x = x;
        this.y = y;
        this.tileElement.style.setProperty('--x', x);
        this.tileElement.style.setProperty('--y', y);
    }

    setValue(value) {
        this.value = value;
        this.tileElement.textContent = this.value;
        const bgLightness = 100 - Math.log2(value) * 9;
        this.tileElement.style.setProperty('--bg-lightness', `${bgLightness}%`);
        //цвет текста зависит от цвета плитки
        this.tileElement.style.setProperty('--text-lightness', `${bgLightness < 50 ? 90 : 10}`);
    }

    removeFromDOM() {
        this.tileElement.remove();
    }

    //возвращает промис, кодга анимация закончится
    waitForTransitionEnd () {
        return new Promise(resolve => {
            this.tileElement.addEventListener('transitionend', resolve, { once: true });
        });
    }

    waitForAnimationEnd () {
        return new Promise(resolve => {
            this.tileElement.addEventListener('animationend', resolve, { once: true });
        });
    }
}