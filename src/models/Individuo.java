package models;

import java.util.ArrayList;
import java.util.List;

public class Individuo implements Comparable<Individuo>{
    private List<Double> espacos;
    private List<Double> valores;
    private List<String> cromossomo = new ArrayList<>();
    private Double limiteEspaco;
    private Double notaAvaliacao;
    private Double espacoUsado;
    private int geracao;

    public Individuo(List<Double> espacos, List<Double> valores, Double limiteEspaco) {
        this.espacos = espacos;
        this.valores = valores;
        this.limiteEspaco = limiteEspaco;
        this.notaAvaliacao = 0.0;
        this.espacoUsado = 0.0;
        this.geracao = 0;

        for(int i = 0;i < this.espacos.size();i++){
            if(java.lang.Math.random() < 0.5){
                this.cromossomo.add("0");
            }else{
                this.cromossomo.add("1");
            }
        }

    }

    public void avaliacao(){
        Double nota = 0.0;
        Double somaEspacos = 0.0;
        for(int i = 0; i < this.cromossomo.size();i++){
            if(this.cromossomo.get(i).equals("1")){
                nota += this.valores.get(i);
                somaEspacos += this.espacos.get(i);
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
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspaco));
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspaco));

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
    public Double getEspacoUsado() {
        return espacoUsado;
    }

    public void setEspacoUsado(Double espacoUsado) {
        this.espacoUsado = espacoUsado;
    }

    public List<Double> getEspacos() {
        return espacos;
    }

    public void setEspacos(List<Double> espacos) {
        this.espacos = espacos;
    }

    public List<Double> getValores() {
        return valores;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }

    public List<String> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<String> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public Double getLimiteEspaco() {
        return limiteEspaco;
    }

    public void setLimiteEspaco(Double limiteEspaco) {
        this.limiteEspaco = limiteEspaco;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
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
