const { stdin, stdout } = require("process");

const rl = require("readline").createInterface({
    input: stdin,
    output: stdout
});

rl.question("Digite os números desejados separados por um espaço. ", function(answer) {
    rl.close();
    let nums = answer.split(" ");
    for (i in nums) {
        nums[i] = parseInt(nums[i]);
    }
    primo(nums);
});

function primo(nums) {
    for (num of nums) {
        let primalidade = true;

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
            if (nums.length === 1) {
                return true;
            }
        }
    }
}
