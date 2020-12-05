// Acessar o arquivo JSON com os dados dos funcionários e guardar na variável "funcionarios"
var funcionarios;

let request = new XMLHttpRequest();
request.open("GET", "funcionarios.json");
request.responseType = "json";
request.send();
request.onload = function() {
    funcionarios = request.response;
    console.log("funcionarios.json acessado com sucesso.");
}

// Função que executa quando uma função é selecionada na página. Habilita o botão da página
function change() {
    document.getElementById("button").attributes.removeNamedItem("hidden");
    document.getElementsByName("func").forEach(function(e) {
        e.attributes.removeNamedItem("onchange");
    });
}

// Função que executa quando o botão é clicado na página
function bClick() {
    let setor = document.getElementById("setor");
    setor = setor[setor.selectedIndex].value;
    setor = setor === "--" ? false : setor;
    switch (document.querySelector("input[name = func]:checked").value) {
        case "maior":
            maiorSalario(setor); break;
        case "menor":
            menorSalario(setor); break;
        case "media":
            mediaSalario(setor); break;
    }
}

// Função chamada para inserir tabela com dados
function inserirDados(dados, func) {
    let headers;
    let caption;
    
    switch (func) {
        case "maior":
            caption = "<caption>MAIOR SALÁRIO</caption>"; break;
        case "menor":
            caption = "<caption>MENOR SALÁRIO</caption>"; break;
        default:
            caption = "<caption>MÉDIA SALARIAL</caption>";
    }

    if (func !== "media") {
        headers = "<tr><th>Nome</th><th>Setor</th><th>Salário</th></tr>";
        document.getElementById("output").innerHTML = caption + headers + "<tr><td>" + dados[0] + "</td><td>" + dados[1] + "</td><td class=\"cash\">R$ " + dados[2] + "</td></tr>";
    }
    else {
        headers = "<tr><th>Funcionários</th><th>Setor</th><th>Média</th></tr>";
        document.getElementById("output").innerHTML = caption + headers + "<tr><td>" + dados[2] + "</td><td>" + dados[1] + "</td><td class=\"cash\">R$ " + dados[0] + "</td></tr>";
    }
    
    let atts = document.getElementById("results").attributes;
    if (atts.length === 2) {
        atts.removeNamedItem("style");
    }
}

// Função que retorna o nome do funcionário que tem o maior salário da empresa
// Se o setor for especificado, retornará o maior salário do setor
function maiorSalario(setor) {
    let maiorSalario = { "nome" : "", "salario" : -Infinity , "setor" : "" };

    if (setor) {
        for (f of funcionarios.funcionarios) {
            if (f.salario > maiorSalario.salario && f.setor === setor) {
                maiorSalario.nome = f.nome;
                maiorSalario.salario = f.salario;
                maiorSalario.setor = f.setor;
            }
        }
        console.log("Funcionário com maior salário do setor " + setor + ": " + maiorSalario.nome + ", com o salário de " + maiorSalario.salario + ".");
        inserirDados([maiorSalario.nome, maiorSalario.setor, maiorSalario.salario], "maior");
        return [maiorSalario.nome, maiorSalario.setor, maiorSalario.salario];
    }
    for (f of funcionarios.funcionarios) {
        if (f.salario > maiorSalario.salario) {
            maiorSalario.nome = f.nome;
            maiorSalario.salario = f.salario;
            maiorSalario.setor = f.setor;
        }
    }
    console.log("Funcionário com o maior salário da empresa: " + maiorSalario.nome + ", do setor " + maiorSalario.setor + ", com o salário de " + maiorSalario.salario + ".");
    inserirDados([maiorSalario.nome, maiorSalario.setor, maiorSalario.salario], "maior");
    return [maiorSalario.nome, maiorSalario.setor, maiorSalario.salario];
}

// Função que retorna o nome do funcionário que tem o menor salário da empresa
// Se o setor for especificado, retornará o menor salário do setor
function menorSalario(setor) {
    let menorSalario = { "nome" : "", "salario" : Infinity , "setor" : "" };
    
    if (setor) {
        for (f of funcionarios.funcionarios) {
            if (f.salario < menorSalario.salario && f.setor === setor) {
                menorSalario.nome = f.nome;
                menorSalario.salario = f.salario;
                menorSalario.setor = f.setor;
            }
        }
        console.log("Funcionário com menor salário do setor " + setor + ": " + menorSalario.nome + ", com o salário de " + menorSalario.salario + ".");
        inserirDados([menorSalario.nome, menorSalario.setor, menorSalario.salario], "menor");
        return [menorSalario.nome, menorSalario.setor, menorSalario.salario];
    }
    for (f of funcionarios.funcionarios) {
        if (f.salario < menorSalario.salario) {
            menorSalario.nome = f.nome;
            menorSalario.salario = f.salario;
            menorSalario.setor = f.setor;
        }
    }
    console.log("Funcionário com o menor salário da empresa: " + menorSalario.nome + ", do setor " + menorSalario.setor + ", com o salário de " + menorSalario.salario + ".");
    inserirDados([menorSalario.nome, menorSalario.setor, menorSalario.salario], "menor");
    return [menorSalario.nome, menorSalario.setor, menorSalario.salario];
}

// Função que retorna a média salarial da empresa
// Se o setor for especificado, retornará a média do setor
function mediaSalario(setor) {
    let numFuncionarios = 0;
    let media = 0;

    if (setor) {
        for (f of funcionarios.funcionarios) {
            if (f.setor === setor) {
                numFuncionarios++;
                media += f.salario;
            }
        }
        media /= numFuncionarios;
        console.log("A media salarial do setor " + setor + " é: " + media + ".");
        inserirDados([parseInt(media), setor, numFuncionarios], "media");
        return [media, setor, numFuncionarios];
    }
    for (f of funcionarios.funcionarios) {
        numFuncionarios++;
        media += f.salario;
    }
    media /= numFuncionarios;
    console.log("A media salarial da empresa é: " + media + ".");
    inserirDados([parseInt(media), "", numFuncionarios], "media");
    return [media, numFuncionarios];
}
