package academia.model;

import academia.exception.ValidacaoException;

import java.time.LocalDate;

/** Classe-mãe dos planos. Cada tipo de plano implementa suas próprias regras (polimorfismo). */
public abstract class Plano {
    private int id;
    private String nome;
    private double valorBase;

    protected Plano(int id, String nome, double valorBase) {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome do plano é obrigatório.");
        }
        if (valorBase <= 0) {
            throw new ValidacaoException("Valor base do plano deve ser maior que zero.");
        }
        this.id = id;
        this.nome = nome;
        this.valorBase = valorBase;
    }

    public abstract double calcularMensalidade();

    public abstract boolean permiteAulaPersonal();

    public abstract double calcularMultaCancelamento(LocalDate dataInicio, LocalDate dataCancelamento);

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getValorBase() { return valorBase; }
}
