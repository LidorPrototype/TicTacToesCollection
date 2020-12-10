let board = [
  ['', '', ''],
  ['', '', ''],
  ['', '', '']
];

let w;
let h;

let ai = 'X';
let human = 'O';
let currentPlayer = human;

function setup() {
    createCanvas(500, 500);
    w = width / 3;
    h = height / 3;
    bestMove();
}

function equals3(a, b, c) {
    return a == b && b == c && a == c && a != '';
}

function checkWinner() {
    let winner = null;
    // Horizontal Win
    for (let i = 0; i < 3; i++) {
        if (equals3(board[i][0], board[i][1], board[i][2])) {
            winner = board[i][0];
        }
    }
    // Vertical Win
    for (let i = 0; i < 3; i++) {
        if (equals3(board[0][i], board[1][i], board[2][i])) {
            winner = board[0][i];
        }
    }
    // Diagonal Win
    if (equals3(board[0][0], board[1][1], board[2][2])) {
        winner = board[0][0];
    }
    if (equals3(board[2][0], board[1][1], board[0][2])) {
        winner = board[2][0];
    }

    let openSpots = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (board[i][j] == '') {
                openSpots++;
            }
        }
    }

    if (winner == null && openSpots == 0) {
        return 'tie';
    } else {
        return winner;
    }
}

function mousePressed() {
    if (currentPlayer == human) {
        let i = floor(mouseX / w);
        let j = floor(mouseY / h);
        if (board[i][j] == '') {
            board[i][j] = human;
            currentPlayer = ai;
            bestMove();
        }
    }
}

function draw() {
    background(255);
    strokeWeight(25);
    stroke(128, 128, 128);  

    line(w, 0, w, height);
    line(w * 2, 0, w * 2, height);
    line(0, h, width, h);
    line(0, h * 2, width, h * 2);

    for (let j = 0; j < 3; j++) {
        for (let i = 0; i < 3; i++) {
            let x = ((w * i) + (w / 2));
            let y = ((h * j) + (h / 2));
            let spot = board[i][j];
            textSize(32);
            let radius = (w / 4);
            if (spot == human) {
                noFill();
                stroke(1, 156, 146);  
                ellipse(x, y, (radius * 2));
            } else if (spot == ai) {
                stroke('black');  
                line((x - radius), (y - radius), (x + radius), (y + radius));
                line((x + radius), (y - radius), (x - radius), (y + radius));
            }
        }
    }

    let result = checkWinner();
    if (result != null) {
        noLoop();
        let resultP = createP('');
        resultP.style('font-size', '32pt');
        if (result == 'tie') {
            resultP.html('Tie!');
        } else {
            resultP.html(`<p style="text-indent: 12%"><i>${result} wins!</i></p>`);
        }
    }
}