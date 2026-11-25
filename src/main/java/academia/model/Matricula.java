package academia.model;

import academia.enums.SituacaoMatricula;
import academia.exception.TransicaoEstadoInvalidaException;
import academia.exception.TrancamentoNaoPermitidoException;
import academia.exception.ValidacaoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Vínculo entre um aluno e um plano. Possui estado dinâmico (SituacaoMatricula). */
public class Matricula {
    private int id;
    private Aluno aluno;
    private Plano plano;
    private LocalDate dataInicio;
    private LocalDate dataUltimoTrancamento;
    private SituacaoMatricula situacao;

    public Matricula(int id, Aluno aluno, Plano plano, LocalDate dataInicio) {
        if (aluno == null || plano == null || dataInicio == null) {
            throw new ValidacaoException("Aluno, plano e data de início são obrigatórios.");
        }
        this.id = id;
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.situacao = SituacaoMatricula.ATIVA;
    }

    /** Regra de negócio: no máximo 1 trancamento a cada 12 meses. */
    public void trancar(LocalDate hoje) {
        if (dataUltimoTrancamento != null
                && ChronoUnit.MONTHS.between(dataUltimoTrancamento, hoje) < 12) {
            throw new TrancamentoNaoPermitidoException(
                    "Matrícula já foi trancada nos últimos 12 meses.");
        }
        transicionar(SituacaoMatricula.TRANCADA);
        dataUltimoTrancamento = hoje;
    }

    public void reativar() {
        transicionar(SituacaoMatricula.ATIVA);
    }

    public void vencer() {
        transicionar(SituacaoMatricula.VENCIDA);
    }

    public void cancelar() {
        transicionar(SituacaoMatricula.CANCELADA);
    }

    /** Regra de negócio: só matrícula ativa permite entrar na academia. */
    public boolean podeFazerCheckIn() {
        return situacao == SituacaoMatricula.ATIVA;
    }

    private void transicionar(SituacaoMatricula destino) {
        if (!situacao.permiteTransicaoPara(destino)) {
            throw new TransicaoEstadoInvalidaException(
                    "Não é possível ir de " + situacao + " para " + destino + ".");
        }
        situacao = destino;
    }

    public int getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public Plano getPlano() { return plano; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataUltimoTrancamento() { return dataUltimoTrancamento; }
    public SituacaoMatricula getSituacao() { return situacao; }
}
