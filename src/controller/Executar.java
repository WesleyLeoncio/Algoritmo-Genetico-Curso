package controller;

import models.Individuo;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class Executar {
    public static void main(String[] args) {
        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("Iphone 6", 0.000089, 2911.12));
        listaProdutos.add(new Produto("TV 55' ", 0.400, 4346.99));
        listaProdutos.add(new Produto("TV 50' ", 0.290, 3999.90));
        listaProdutos.add(new Produto("TV 42' ", 0.200, 2999.00));
        listaProdutos.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProdutos.add(new Produto("Ventilador Panasonic", 0.496, 199.90));
        listaProdutos.add(new Produto("Microondas Electrolux", 0.0424, 308.66));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProdutos.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        listaProdutos.add(new Produto("Geladeira Brastemp", 0.635, 849.00));
        listaProdutos.add(new Produto("Geladeira Consul", 0.870, 1199.89));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.00));

        //TODO: NÃO SEI PORQUE ESSE MOLUCO FEZ ISSO.
        List<Double> espacos = new ArrayList<>();
        List<Double> valores = new ArrayList<>();
        List<String> nomes = new ArrayList<>();
        final String separador = "///////////////////////////////////////////////////";

        for (Produto produto : listaProdutos) {
            espacos.add(produto.getEspaco());
            valores.add(produto.getValor());
            nomes.add(produto.getNome());
        }

        Double limite = 3.0;
        //TODO: MELHORAR CODIGO PASSASNDO O PROODUTO INVEZ DE PASSAR OS 2 ARRAYS
        Individuo individuo1 = new Individuo(espacos, valores, limite);
        individuo1.avaliacao();
        System.out.println(separador);
        System.out.println("Cromossomo: " + individuo1.getCromossomo());
        System.out.println("Notas: " + individuo1.getNotaAvaliacao());
        System.out.println("Espaço usado: " + individuo1.getEspacoUsado());
        System.out.println(separador);

        Individuo individuo2 = new Individuo(espacos, valores, limite);
        individuo2.avaliacao();
        System.out.println(separador);
        System.out.println("Cromossomo: " + individuo2.getCromossomo());
        System.out.println("Notas: " + individuo2.getNotaAvaliacao());
        System.out.println("Espaço usado: " + individuo2.getEspacoUsado());
        System.out.println(separador);

        individuo1.crossover(individuo2);
        individuo1.mutacao(0.05);
        individuo2.mutacao(0.05);
    }
}