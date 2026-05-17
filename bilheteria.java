public class lpp {
    abstract class Ingresso {
        protected double precoSetor;

        public Ingresso(double precoSetor) { this.precoSetor = precoSetor; }
        public abstract double getValorFinal();
        public abstract String getTipoDescricao();
    }

    class IngressoInteira extends Ingresso {
        public IngressoInteira(double preco) { super(preco); }
        @Override public double getValorFinal() { return precoSetor; }
        @Override public String getTipoDescricao() { return "Inteira"; }
    }

    class MeiaEntrada extends Ingresso {
        public MeiaEntrada(double preco) { super(preco); }
        @Override public double getValorFinal() { return precoSetor * 0.5; }
        @Override public String getTipoDescricao() { return "Meia-Entrada"; }
    }
    abstract class Setor {
        protected String nome;
        protected double precoBase;

        public Setor(String nome, double precoBase) {
            this.nome = nome;
            this.precoBase = precoBase;
        }
        public String getNome() { return nome; }
        public double getPrecoBase() { return precoBase; }
    }
    class SetorPista extends Setor {
        private int capacidade;
        private int vendidos;

        public SetorPista(String nome, double preco, int capacidade) {
            super(nome, preco);
            this.capacidade = capacidade;
            this.vendidos = 0;
        }

        public boolean temEspaco() { return vendidos < capacidade; }
        public void registrarVenda() { vendidos++; }
        public int getDisponivel() { return capacidade - vendidos; }
    }
    class SetorArquibancada extends Setor {
        private boolean[] assentos;

        public SetorArquibancada(String nome, double preco, int totalAssentos) {
            super(nome, preco);
            this.assentos = new boolean[totalAssentos];
        }

        public void exibirMapa() {
            System.out.println("\nMapa " + nome + ":");
            for (int i = 0; i < assentos.length; i++) {
                System.out.print(assentos[i] ? "[X] " : "[" + (i + 1) + "] ");
                if ((i + 1) % 10 == 0) System.out.println();
            }
        }
        public boolean reservar(int num) {
            if (num < 1 || num > assentos.length || assentos[num - 1]) return false;
            assentos[num - 1] = true;
            return true;
        }
        public static void processarVendaPista(SetorPista setor, int tipo) {
            if (setor.temEspaco()) {
                Ingresso ing = (tipo == 2) ? new MeiaEntrada(setor.getPrecoBase()) : new IngressoInteira(setor.getPrecoBase());
                setor.registrarVenda();
                confirmar(setor.getNome(), ing);
            } else {
                System.out.println("ESGOTADO!");
            }
        }

        public static void processarVendaArquibancada(SetorArquibancada setor, int tipo, int num) {
            if (setor.reservar(num)) {
                Ingresso ing = (tipo == 2) ? new MeiaEntrada(setor.getPrecoBase()) : new IngressoInteira(setor.getPrecoBase());
                confirmar(setor.getNome(), ing);
            } else {
                System.out.println("Assento ocupado ou inválido!");
            }
        }

        public static void confirmar(String setor, Ingresso ing) {
            System.out.println("\n--- RECIBO ---");
            System.out.println("Setor: " + setor);
            System.out.println("Categoria: " + ing.getTipoDescricao());
            System.out.println("Total: R$ " + ing.getValorFinal());
            System.out.println("----------------\n");
        }
}
