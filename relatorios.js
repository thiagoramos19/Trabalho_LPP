const transacoes = Object.freeze([
  { id: 1, fluxo: 'entrada', categoria: 'Moveis', valor: 5000 },
  { id: 2, fluxo: 'saida', categoria: 'Moda',     valor: 1000 },
  { id: 3, fluxo: 'entrada', categoria: 'Moda',      valor: 3000 },
  { id: 4, fluxo: 'saida', categoria: 'Eletronicos',  valor: 1500 },
  { id: 5, fluxo: 'saida', categoria: 'Moda',     valor: 500  },
  { id: 6, fluxo: 'entrada', categoria: 'Eletronicos', valor: 2000 },
  { id: 7, fluxo: 'saida', categoria: 'Moveis',  valor: 800  }
]);

const calcularTotalPorFluxo = (lista, tipoFluxo) => {
 		return lista
    		.filter(item => item.fluxo === tipoFluxo)
    		.reduce((acumulador, item) => acumulador + item.valor, 0);
};

const gerarBalancoGeral = (lista) => {
const totalGanhos = calcularTotalPorFluxo(lista, 'entrada');
  	const totalSaidas = calcularTotalPorFluxo(lista, 'saida');
  
  	return {
    		totalGanhos: totalGanhos,
    		totalSaidas: totalSaidas,
    		lucroTotal: totalGanhos - totalSaidas
  	};
};

const gerarRelatorioPorCategoria = (lista) => {
  return lista.reduce((acumulador, item) => {
    const cat = item.categoria;
    const categoriaAtual = acumulador[cat] || { ganhos: 0, saidas: 0, lucro: 0   };
    const novosGanhos = item.fluxo === 'entrada' ? categoriaAtual.ganhos + item.valor : categoriaAtual.ganhos;
    const novasSaidas = item.fluxo === 'saida' ? categoriaAtual.saidas + item.valor : categoriaAtual.saidas;
    return {
      ...acumulador, [cat]: {
        ganhos: novosGanhos,
        saidas: novasSaidas,
        lucro: novosGanhos - novasSaidas
      }
    };
  }, {}); 
};

const balancoGeral = gerarBalancoGeral(transacoes);
const relatorioCategorias = gerarRelatorioPorCategoria(transacoes);

console.log("--- BALANÇO GERAL ---");
console.log(balancoGeral);

console.log("--- RELATÓRIO POR CATEGORIA ---");
console.log(relatorioCategorias);
