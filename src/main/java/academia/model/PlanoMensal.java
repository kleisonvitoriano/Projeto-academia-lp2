package academia.model;

import academia.exception.ValidacaoException;

import java.time.LocalDate;

public class PlanoMensal extends Plano {
    private int diaVencimento;

    public PlanoMensal(int id, String nome, double valorBase, int diaVencimento) {
        super(id, nome, valorBase);
        if (diaVencimento < 1 || diaVencimento > 28) {
            throw new ValidacaoException("Dia de vencimento deve estar entre 1 e 28.");
        }
        this.diaVencimento = diaVencimento;
    }

    @Override
    public double calcularMensalidade() {
        return getValorBase();
    }

    @Override
    public boolean permiteAulaPersonal() {
        return true;
    }

    @Override
    public double calcularMultaCancelamento(LocalDate dataInicio, LocalDate dataCancelamento) {
        return 0.0; // plano mensal cancela sem multa
    }

    public int getDiaVencimento() { return diaVencimento; }
}
