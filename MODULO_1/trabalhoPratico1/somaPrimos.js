const { stdin, stdout } = require("process");

const rl = require("readline").createInterface({
    input: stdin,
    output: stdout
});
let piso;
let teto;

rl.question("Digite o número inicial e o número limite separados por um espaço. ", function(answer) {
    rl.close();
    let nums = answer.split(" ");
    piso = parseInt(nums[0]);
    teto = parseInt(nums[1]);
    somaPrimos(teto, piso);
});

function somaPrimos(teto, piso) {
    if (piso < 2 || piso === undefined) {
        piso = 2;
    }
    let i = piso;
    let soma = 0;
    while (i < teto) {
        if (primo(i) === true) {
            soma += i;
        }
        i++;
    }
    console.log("A soma dos números primos a partir de " + piso + " e menores que " + teto + " é " + soma +".");
}

function primo(num) {
    let primalidade = true

    for (let i = 2; i <= num; i++){
        if (num % i === 0 && i !== num) {
            primalidade = false;
            break;
        }
    }
    
    if (primalidade === false) {
        console.log("O número " + num + " NÃO é primo.");
    }
    else {
        console.log("O número " + num + " É primo.");
        return true;
    }
}