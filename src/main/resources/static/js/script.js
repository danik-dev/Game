import { Grid } from "./grid.js";
import { Tile } from "./tile.js";

const gameBoard = document.getElementById('game-board');
let totalScore = 0;
const grid = new Grid(gameBoard);

function waitForButtonClick() {
    return new Promise(function(resolve) {
        var playButton = document.getElementById('play-btn');
        playButton.addEventListener('click', function() {
            resolve();
        });
    });
}

function setupInputOnce() {
    window.addEventListener('keydown', handleInput, {once: true});
}

let startTime; // время начала отсчета
let elapsedTime = 0; // прошедшее время в миллисекундах
let timerInterval; // интервал для таймера

function updateTimer() {
    const timer = document.getElementById('timer');
    const elapsedTimeInSeconds = Math.floor(elapsedTime / 1000); // переводим время в секунды
    const seconds = elapsedTimeInSeconds % 60;
    const minutes = Math.floor(elapsedTimeInSeconds / 60) % 60;
    const hours = Math.floor(elapsedTimeInSeconds / 3600);
    const formattedTime = `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    timer.textContent = formattedTime;
}
function resetTimer() {
    clearInterval(timerInterval);
    elapsedTime = 0;
    updateTimer();
}

// function gameReset() {
//     grid = new Grid(gameBoard);
//     resetTimer();
//     totalScore = 0;
//     grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));
//     grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));
//     setupInputOnce();
// }

waitForButtonClick().then(function() {
    setupInputOnce();
    startTime = Date.now() - elapsedTime;
    timerInterval = setInterval(() => {
        elapsedTime = Date.now() - startTime;
        updateTimer();
    }, 10);
});

grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));
grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));
async function handleInput(event) {
    switch(event.key) {
        case 'ArrowUp': 
            if (!canMoveUp()) {
                setupInputOnce();
                return;
            }
            await moveUp();
            break;
        case 'ArrowDown': 
            if (!canMoveDown()) {
                setupInputOnce();
                return;
            }
            await moveDown();
            break;
        case 'ArrowLeft':
            if (!canMoveLeft()) {
                setupInputOnce();
                return;
            }
            await moveLeft(); 
            break;
        case 'ArrowRight':
            if (!canMoveRight()) {
                setupInputOnce();
                return;
            }
            await moveRight(); 
            break;
        //если нажата не стрелочка
        default:
            setupInputOnce();
            return;
    }
    const newTile = new Tile(gameBoard);
    grid.getRandomEmptyCell().linkTile(newTile);

    if (!canMoveUp() && !canMoveDown() && !canMoveLeft() && !canMoveRight()) {
        await newTile.waitForAnimationEnd();
        clearInterval(timerInterval);
        sendGameSessionResult();
        // window.addEventListener("keydown", (event) => {
        //     if (event.key === " ") {
        //         gameReset();
        //     }
        // }, {once: true})
        alert("Try again!");
        return;
    }


    //после нажатия подписываемся заного
    setupInputOnce();
}

async function moveUp() {
    await slideTiles(grid.cellsGroupedByColumn);
}

async function moveDown() {
    await slideTiles(grid.cellsGroupedByReversedColumn);
}

async function moveLeft() {
    await slideTiles(grid.cellsGroupedByRow);
}

async function moveRight() {
    await slideTiles(grid.cellsGroupedByReversedRow);
}

async function slideTiles(groupedCells) {
    //каждый promice будет дожидаться окончания анимации своей плитки
    const promises = [];
    
    groupedCells.forEach(group => slideTilesInGroup(group, promises));

    await Promise.all(promises);
    //логика объединения значений
    grid.cells.forEach(cell => {
        if (cell.hasTileForMerge()) {
            cell.mergeTiles();
            totalScore += cell.cellPoints;
        }
        // cell.hasTileForMerge() && cell.mergeTiles();
    });
    updateScore();
    // totalScore += changePoints;
}

function slideTilesInGroup(group, promises) {
    for (let i = 1; i < group.length; i++) {
        if (group[i].isEmpty()) {
            continue;
        }
  
        const cellWithTile = group[i];
    
        let targetCell;
        let j = i - 1;
        while (j >= 0 && group[j].canAccept(cellWithTile.linkedTile)) {
            targetCell = group[j];
            j--;
        }
    
        if (!targetCell) {
            continue;
        }

        promises.push(cellWithTile.linkedTile.waitForTransitionEnd());

        //перелинковали плитку к новой ячейке
        if (targetCell.isEmpty()) {
            targetCell.linkTile(cellWithTile.linkedTile);
        } else {
            targetCell.linkTileForMerge(cellWithTile.linkedTile);
        }

        

        //освободить текущую ячейку от плитки
        cellWithTile.unlinkTile();
    }
}

function canMoveUp() {
    return canMove(grid.cellsGroupedByColumn);
}
  
function canMoveDown() {
    return canMove(grid.cellsGroupedByReversedColumn);
}
  
function canMoveLeft() {
    return canMove(grid.cellsGroupedByRow);
}
  
function canMoveRight() {
    return canMove(grid.cellsGroupedByReversedRow);
}
  
function canMove(groupedCells) {
    return groupedCells.some(group => canMoveInGroup(group));
}
  
function canMoveInGroup(group) {
    return group.some((cell, index) => {
      if (index === 0) {
        return false;
      }
  
      if (cell.isEmpty()) {
        return false;
      }
  
      const targetCell = group[index - 1];
      return targetCell.canAccept(cell.linkedTile);
    });
}

function updateScore() {
    const scoreElement = document.getElementById("score");
    scoreElement.textContent = totalScore;
}

function sendGameSessionResult () {
    startTime = Date.now() - elapsedTime;
    let date = new Date(startTime);
    let date_formatted = date.getFullYear().toString()+"-"+((date.getMonth()+1).toString().length==2?(date.getMonth()+1).toString():"0"+(date.getMonth()+1).toString())+"-"+(date.getDate().toString().length==2?date.getDate().toString():"0"+date.getDate().toString())+"-"+(date.getHours().toString().length==2?date.getHours().toString():"0"+date.getHours().toString())+"-"+((parseInt(date.getMinutes()/5)*5).toString().length==2?(parseInt(date.getMinutes()/5)*5).toString():"0"+(parseInt(date.getMinutes()/5)*5).toString())+"-00";
    console.log("date formatted: " + date_formatted);
    const data = {getStarted: date_formatted, duration: elapsedTime, score: totalScore};
    console.log("i try to send request")
    fetch('/game-session-results', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            console.log(response);
        })
        .catch(error => {
            console.log(error);
        });
}