package academia.exception;

/** Dado inválido ou ausente (nome vazio, data no futuro, etc.). */
public class ValidacaoException extends AcademiaException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
