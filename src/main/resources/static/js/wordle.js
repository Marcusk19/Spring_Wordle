
var height = 6; //number of guesses
var width = 5; // length of word

var row = 0; // current guess (attempt #)
var col = 0; // current letter for attempt

var gameOver = false;

var apiUrl = 'http://localhost/api';
var word = "MARKY";

window.onload = function(){
    initialize();
}

fetch(apiUrl + '/choose_solution')
.then(response => {
    return response.text();
})
.then((text) => {
    console.log(text);
})

function initialize() {

    // create the board
    for(let r = 0; r < height; r++) {
        for(let c = 0; c < width; c++) {
            let tile = document.createElement("span");
            tile.id = r.toString() + "-" + c.toString();
            tile.classList.add("box")
            tile.innerText = "";

            document.getElementById("board").appendChild(tile);
        }
    }

    // Listen for keypress
    document.addEventListener("keyup", (e) => {
        if(gameOver) return;
        // alert(e.code)
        if("KeyA" <= e.code  && e.code <= "KeyZ"){
            if(col < width){
                let currTile = document.getElementById(row.toString() + '-' + col.toString());
                if(currTile.innerText == "") {
                    currTile.innerText = e.code[3];
                    col += 1;
                }
            }
        }
        else if(e.code == "Backspace") {
            if(0 < col && col <= width) {
                col -= 1;
                let currTile = document.getElementById(row.toString() + '-' + col.toString());
                currTile.innerText = "";
            }
        }
        else if(e.code == "Enter") {
            check();
            if(row < height) row += 1;
            col = 0;
        }

    })
}

function check(){
    // check if game is over
    fetch(apiUrl + '/check_game')
    .then(response => {
        return response.text();
    })
    .then((data) => {
        let endGame = data;
        console.log(endGame);
        
        if(endGame == "true") {
            // only get solution if the game is over
            fetch(apiUrl + '/solution')
            .then(response => {
                return response.text();
            })
            .then((text) => {
                let solution = text;
                document.getElementById("answer").innerText = solution;
            })

            gameOver = true;
            return;
        }
    })

    var guess = ""
    for(let c = 0; c < width; c++) {
        let currTile = document.getElementById(row.toString() + '-' + c.toString());
        let letter = currTile.innerText;
        guess = guess + letter;
    }
    console.log(guess);

    fetch(apiUrl + '/validate?guess=' + guess)
    .then(response => {
        return response.json();
    })
    .then((data) => {
        let validate_arr = data;

        for(let i = 0; i < width; i++) {
            console.log(validate_arr[i]);
            let currTile = document.getElementById((row-1).toString() + '-' + i.toString());
            console.log(row.toString() + '-' + i.toString());
            if(validate_arr[0] == "invalid") {
                currTile.innerText = "";
            } else {
                currTile.classList.add(validate_arr[i]);
            }
        }
        if(validate_arr[0] == "invalid") row -= 1;
    })
}