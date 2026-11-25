package academia.exception;

/** Mudança de estado não permitida pela regra de transição. */
public class TransicaoEstadoInvalidaException extends AcademiaException {
    public TransicaoEstadoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
