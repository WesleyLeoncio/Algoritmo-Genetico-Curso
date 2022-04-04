package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoGenetico {
    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList<>();
    private int geracao;
    private Individuo melhorSolucao;

    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public void inicializarPopulacao(List<Double> espacos, List<Double> valores, Double limiteEspaco) {
        for (int i = 0; i < this.tamanhoPopulacao; i++) {
            this.populacao.add(new Individuo(espacos, valores, limiteEspaco));
        }
        this.melhorSolucao = this.populacao.get(0);
    }

    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    public void melhorIndividuo(Individuo individuo) {
        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
            this.melhorSolucao = individuo;
        }
    }

    public Double somaAvaliacoes() {
        Double soma = 0.0;
        for (Individuo individuo : this.populacao) {
            soma += individuo.getNotaAvaliacao();
        }
        return soma;
    }

    public int selecionaPai(Double somaAvaliacao) {
        int pai = -1;
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.0;
        int i = 0;
        while (i < this.populacao.size() && soma < valorSorteado) {
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai++;
            i++;
        }
        return pai;
    }

    public void visualizarGeracao() {
        Individuo melhor = this.populacao.get(0);
        System.out.println("G: " + melhor.getGeracao() +
                " Valor: " + melhor.getNotaAvaliacao() +
                " Espaço: " + melhor.getEspacoUsado() +
                " Cromossomo: " + melhor.getCromossomo());
    }

    public List resolver(Double taxaMutacao, int numeroGeracoes, List espacos,
                         List valores, Double limiteEspacos) {

        this.inicializarPopulacao(espacos, valores, limiteEspacos);
        for (Individuo individuo : this.populacao) {
            individuo.avaliacao();
        }
        this.ordenaPopulacao();
        this.visualizarGeracao();

        for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
            Double somaAvaliacao = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList<>();

            for (int i = 0; i < this.populacao.size() / 2; i++) {
                int pai1 = this.selecionaPai(somaAvaliacao);
                int pai2 = this.selecionaPai(somaAvaliacao);

                List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.getPopulacao().get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
            }

            this.setPopulacao(novaPopulacao);
            for (Individuo individuo : this.getPopulacao()) {
                individuo.avaliacao();
            }
            this.ordenaPopulacao();
            this.visualizarGeracao();
            Individuo melhor = this.populacao.get(0);
            this.melhorIndividuo(melhor);

        }
        System.out.println("Melhor solução G -> " + this.melhorSolucao.getGeracao() +
                " Valor: " + this.melhorSolucao.getNotaAvaliacao() +
                " Espaço: " + this.melhorSolucao.getEspacoUsado() +
                " Cromossomo: " + this.melhorSolucao.getCromossomo());
        return this.melhorSolucao.getCromossomo();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
}
