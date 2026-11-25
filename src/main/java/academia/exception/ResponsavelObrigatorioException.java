package academia.exception;

/** Aluno menor de idade sem responsável cadastrado. */
public class ResponsavelObrigatorioException extends AcademiaException {
    public ResponsavelObrigatorioException(String mensagem) {
        super(mensagem);
    }
}
