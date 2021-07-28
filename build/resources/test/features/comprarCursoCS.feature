#language: pt
Funcionalidade: : Comprar Curso CS
  Cenario: Pesquisar Curso com Clique e Incluir no Carrinho
    Dado que acesso o site da Iterasys
    Quando pesquiso por "Mantis"
    E clico na lupa
    Entao vejo a lista de resultados para o curso "Mantis"
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como "Mantis" e o preco do curso de "R$ 49,99"

  Cenario: Pesquisar Curso com Enter e Incluir no Carrinho
    Dado que acesso o site da Iterasys
    Quando pesquiso por "Mantis"
    E clico pressiono Enter
    Entao vejo a lista de resultados para o curso "Mantis"
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como "Mantis" e o preco do curso de "R$ 49,99"

  Esquema do Cenario: Pesquisar Curso e Incluir no Carrinho
    Dado que acesso o site da Iterasys
    Quando pesquiso por <curso>
    E clico pressiono Enter
    Entao vejo a lista de resultados para o curso <curso>
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como <curso> e o preco do curso de <preco>
    Exemplos:
      | curso               | preco       |
      | "Mantis"            | "R$ 49,99"  |
      | "Preparatório CTFL" | "R$ 169,00" |