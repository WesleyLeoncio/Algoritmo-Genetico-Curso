package models;

import java.util.ArrayList;
import java.util.List;

public class Individuo implements Comparable<Individuo>{
    private final List<Produto> produtos;
    private List<String> cromossomo = new ArrayList<>();
    private final Double limiteEspaco;

    private Double notaAvaliacao;
    private Double espacoUsado;
    private int geracao;

    public Individuo(List<Produto> produtos, Double limiteEspaco) {
        this.produtos = produtos;
        this.limiteEspaco = limiteEspaco;
        this.notaAvaliacao = 0.0;
        this.espacoUsado = 0.0;
        this.geracao = 0;

        for(int i = 0;i < this.produtos.size();i++){
            if(java.lang.Math.random() < 0.5){
                this.cromossomo.add("0");
            }else{
                this.cromossomo.add("1");
            }
        }

    }

    public void avaliacao(){
        double nota = 0.0;
        double somaEspacos = 0.0;
        for(int i = 0; i < this.cromossomo.size();i++){
            if(this.cromossomo.get(i).equals("1")){
                nota += this.produtos.get(i).getValor();
                somaEspacos += this.produtos.get(i).getEspaco();
            }
        }
        // Se passar do limte maximo atribua uma nota 1
        if(somaEspacos > this.limiteEspaco){
            nota = 1.0;
        }
        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }

    public List<Individuo> crossover(Individuo outroIndividuo){
        //Gera a nota de corte aleatoriamente
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        List<String> filho1 = new ArrayList<>();
        filho1.addAll(outroIndividuo.getCromossomo().subList(0,corte));
        filho1.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));

        List<String> filho2 = new ArrayList<>();
        filho2.addAll(cromossomo.subList(0,corte));
        filho2.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));

        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new Individuo(produtos, this.limiteEspaco));
        filhos.add(new Individuo(produtos, this.limiteEspaco));

        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(this.getGeracao()+1);

        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(this.getGeracao()+1);

        return filhos;
    }

    public Individuo mutacao(Double taxaMutacao){
        for (int i = 0; i < this.cromossomo.size(); i++){
            if(Math.random() < taxaMutacao){
                if(this.cromossomo.get(0).equals("1")){
                    this.cromossomo.set(i, "0");
                }else{
                    this.cromossomo.set(i, "1");
                }
            }
        }
        return this;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public List<Produto> getProdutos() {
        return produtos;
    }

    public Double getEspacoUsado() {
        return espacoUsado;
    }

    public List<String> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<String> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    @Override
    public int compareTo(Individuo o) {
        return o.getNotaAvaliacao().compareTo(this.notaAvaliacao);
    }

}
