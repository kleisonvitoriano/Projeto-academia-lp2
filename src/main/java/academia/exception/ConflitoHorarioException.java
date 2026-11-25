package academia.exception;

/** Instrutor já possui compromisso no mesmo horário. */
public class ConflitoHorarioException extends AcademiaException {
    public ConflitoHorarioException(String mensagem) {
        super(mensagem);
    }
}
