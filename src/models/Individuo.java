package models;

import java.util.ArrayList;
import java.util.List;

public class Individuo {
    private List<Double> espacos = new ArrayList<>();
    private List<Double> valores = new ArrayList<>();
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
        if(somaEspacos > this.limiteEspaco){
            nota = 1.0;
        }
        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }

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
}
