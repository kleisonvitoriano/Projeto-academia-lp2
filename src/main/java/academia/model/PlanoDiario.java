package academia.model;

import academia.exception.ValidacaoException;

import java.time.LocalDate;

public class PlanoDiario extends Plano {
    private LocalDate dataValidade;

    public PlanoDiario(int id, String nome, double valorDiaria, LocalDate dataValidade) {
        super(id, nome, valorDiaria);
        if (dataValidade == null) {
            throw new ValidacaoException("Data de validade é obrigatória.");
        }
        this.dataValidade = dataValidade;
    }

    @Override
    public double calcularMensalidade() {
        return getValorBase(); // cobra apenas a diária
    }

    /** Plano diário não dá direito a aula personal. */
    @Override
    public boolean permiteAulaPersonal() {
        return false;
    }

    @Override
    public double calcularMultaCancelamento(LocalDate dataInicio, LocalDate dataCancelamento) {
        return 0.0;
    }

    public boolean estaValidoEm(LocalDate data) {
        return data.equals(dataValidade);
    }

    public LocalDate getDataValidade() { return dataValidade; }
}
