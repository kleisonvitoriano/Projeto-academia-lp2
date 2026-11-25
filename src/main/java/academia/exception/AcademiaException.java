package academia.exception;

/** Exceção base do sistema. Todas as exceções de negócio herdam dela. */
public class AcademiaException extends RuntimeException {
    public AcademiaException(String mensagem) {
        super(mensagem);
    }
}
