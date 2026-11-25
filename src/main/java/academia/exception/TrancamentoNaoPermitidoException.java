package academia.exception;

/** Matrícula já foi trancada nos últimos 12 meses. */
public class TrancamentoNaoPermitidoException extends AcademiaException {
    public TrancamentoNaoPermitidoException(String mensagem) {
        super(mensagem);
    }
}
