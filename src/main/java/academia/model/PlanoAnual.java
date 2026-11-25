package academia.model;

import academia.exception.ValidacaoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlanoAnual extends Plano {
    private static final double DESCONTO = 0.15;

    private int mesesFidelidade;
    private double percentualMulta;

    public PlanoAnual(int id, String nome, double valorBase, int mesesFidelidade, double percentualMulta) {
        super(id, nome, valorBase);
        if (mesesFidelidade <= 0) {
            throw new ValidacaoException("Fidelidade deve ser de pelo menos 1 mês.");
        }
        if (percentualMulta < 0 || percentualMulta > 100) {
            throw new ValidacaoException("Percentual de multa deve estar entre 0 e 100.");
        }
        this.mesesFidelidade = mesesFidelidade;
        this.percentualMulta = percentualMulta;
    }

    /** Plano anual tem 15% de desconto sobre o valor base. */
    @Override
    public double calcularMensalidade() {
        return getValorBase() * (1 - DESCONTO);
    }

    @Override
    public boolean permiteAulaPersonal() {
        return true;
    }

    /** Cancelar antes de cumprir a fidelidade cobra multa sobre os meses restantes. */
    @Override
    public double calcularMultaCancelamento(LocalDate dataInicio, LocalDate dataCancelamento) {
        long mesesCumpridos = ChronoUnit.MONTHS.between(dataInicio, dataCancelamento);
        long mesesRestantes = mesesFidelidade - mesesCumpridos;
        if (mesesRestantes <= 0) {
            return 0.0;
        }
        return mesesRestantes * calcularMensalidade() * (percentualMulta / 100.0);
    }

    public int getMesesFidelidade() { return mesesFidelidade; }
    public double getPercentualMulta() { return percentualMulta; }
}
