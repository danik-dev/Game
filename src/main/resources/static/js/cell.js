export class Cell {
    cellPoints;
    constructor(gridElement, x, y) {
        // создаём пустой div
        const cell = document.createElement("div");
        // добавляем ему класс cell
        cell.classList.add("cell");
        // добавим созданный элемент в div game-board
        gridElement.append(cell);
        this.x = x;
        this.y = y;
    }

    linkTile(tile) {
        tile.setXY(this.x, this.y);
        this.linkedTile = tile;
    }

    unlinkTile() {
        this.linkedTile = null;
    }

    isEmpty() {
        return !this.linkedTile;
    }

    //отличие в том, что мы сохраняем плитку, 
    //чтобы после объединения её удалить
    linkTileForMerge(tile) {
        tile.setXY(this.x, this.y);
        this.linkedTileForMerge = tile;
      }

    unlinkTileForMerge() {
        this.linkedTileForMerge = null;
    }

    //true когда к плитке привязали ячейку на объединение
    hasTileForMerge() {
        return !!this.linkedTileForMerge;
      }

    //пороверяем парность объединения
    canAccept(newTile) {
        return (
          this.isEmpty() ||
          (!this.hasTileForMerge() && this.linkedTile.value === newTile.value)
        );
      }

    mergeTiles() {
        this.cellPoints = this.linkedTile.value + this.linkedTileForMerge.value;
        this.linkedTile.setValue(this.linkedTile.value + this.linkedTileForMerge.value);
        this.linkedTileForMerge.removeFromDOM();
        this.unlinkTileForMerge();
    }
}