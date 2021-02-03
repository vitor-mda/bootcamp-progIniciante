# Código escrito no Google Colab

clientes = ["Marcelo", "Joana D'arc", "Maria de Fátima"]
produtos = [{
    "nome" : "computador",
    "preco" : 1000.50
}, {
    "nome" : "mouse",
    "preco" : 120.10
}, {
    "nome" : "Monitor LCD",
    "preco" : 999.99
}]

produtos_comprados = []
cliente = ""

def intro():
  print("*** SISTEMA DE NOTAS FISCAIS ***")

def achar_nome():
  nome = input("Digite o nome que deseja pesquisar: ")
  if len(nome) < 4:
    print("ERRO: por favor, digite um nome válido.")
    return achar_nome()
  else:
    for c in clientes:
      if nome.upper() in c.upper():
        print("\nOlá, {}!\n".format(c))
        global cliente
        cliente = c
        return
    
    print("Nome não encontrado.")
    return achar_nome()

def mostrar_produtos():
  i = 1
  for p in produtos:
    print("{} - {}, R${:.2f}".format(i, p["nome"], p["preco"]))
    i += 1

def pegar_indice():
  indice = input("\nPor favor, digite o número do produto (ou apenas aperte a tecla ENTER para terminar): ")
  if indice == "":
    return -1
  if not indice.isdigit():
    print("ERRO: número inválido.")
    return pegar_indice()

  indice = int(indice) - 1

  if indice < 0 or indice > len(produtos) - 1:
    print("ERRO: número inválido.")
    return pegar_indice()

    return indice

def pegar_quantidade():
  quantidade = input("\nDigite a quantidade de produtos: ")

  if not quantidade.isdigit():
    print("Por favor, digite uma quantidade válida (apenas números).")
    return pegar_quantidade()

  quantidade = int(quantidade)

  if quantidade <= 0:
    print("Por favor, digite um número superior a 0.")
    return pegar_quantidade()

  return quantidade

def adicionar_produto():
  indice = pegar_indice()

  if indice < 0:
    return
  
  produto = produtos[indice]
  quantidade = pegar_quantidade()

  for produto_comprado in produtos_comprados:
    if produto["nome"] == produto_comprado["nome"]:
      produto_comprado["quantidade"] += quantidade
      return
  
  produtos_comprados.append({
      "nome" : produto["nome"],
      "preco" : produto["preco"],
      "quantidade" : quantidade,
      "imposto" : produto["preco"] * 0.10
  })
  
  if input("\nDeseja adicionar outro produto? (digite 'S' se sim, ou tecle apenas ENTER se não): ").upper() == "S":
    adicionar_produto()

def resultado():
  total = 0.0

  print("\n-----------------------------\nNOTA FISCAL\n\nCLIENTE: {}\nITENS COMPRADOS:".format(cliente))

  for p in produtos_comprados:    
    total_item = (p["preco"] + p["imposto"]) * p["quantidade"]
    total += total_item
    print("{}x | {}\n     Valor: R${:.2f} (un)\n   Imposto: R${:.2f} (un)\n     Total: R${:.2f}".format(p["quantidade"], p["nome"].capitalize(), p["preco"], p["imposto"], total_item))

  print("TOTAL DA NOTA: {:.2f}\nVOLTE SEMPRE!\n-----------------------------".format(total))

def main():

  intro()
  achar_nome()
  mostrar_produtos()
  adicionar_produto()

  if len(produtos_comprados) > 0:
    resultado()
  else:
    if input("Emissão de nota fiscal cancelada. Digite qualquer coisa para recomeçar o programa ou 0 para encerrar: ") != "0":
      main()

main()
