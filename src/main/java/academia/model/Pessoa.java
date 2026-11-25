package academia.model;

import academia.exception.ValidacaoException;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    protected Pessoa(int id, String nome, String cpf, LocalDate dataNascimento) {
        this.id = id;
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
    }

    public boolean ehMenorDeIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears() < 18;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ValidacaoException("CPF deve ter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new ValidacaoException("Data de nascimento inválida.");
        }
        this.dataNascimento = dataNascimento;
    }
}
