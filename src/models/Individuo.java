package models;

import java.util.ArrayList;
import java.util.List;

public class Individuo {
    private List<Double> espacos = new ArrayList<>();
    private List<Double> valores = new ArrayList<>();
    private List<String> cromossomo = new ArrayList<>();
    private Double limiteEspaco;
    private Double notaAvaliacao;
    private int geracao;

    public Individuo(List<Double> espacos, List<Double> valores, Double limiteEspaco) {
        this.espacos = espacos;
        this.valores = valores;
        this.limiteEspaco = limiteEspaco;
        this.notaAvaliacao = 0.0;
        this.geracao = 0;

        for(int i = 0;i < this.espacos.size();i++){
            if(java.lang.Math.random() < 0.5){
                this.cromossomo.add("0");
            }else{
                this.cromossomo.add("1");
            }
        }
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
}
