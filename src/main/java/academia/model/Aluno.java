package academia.model;

import academia.exception.ResponsavelObrigatorioException;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    private static final int FALTAS_PARA_BLOQUEIO = 3;
    private static final int DIAS_DE_BLOQUEIO = 7;

    private String objetivo;
    private String nomeResponsavel;
    private int faltasConsecutivas;
    private LocalDate bloqueadoAte;

    public Aluno(int id, String nome, String cpf, LocalDate dataNascimento,
                 String objetivo, String nomeResponsavel) {
        super(id, nome, cpf, dataNascimento);
        // Regra de negócio: menor de idade só entra com responsável cadastrado.
        if (ehMenorDeIdade() && (nomeResponsavel == null || nomeResponsavel.isBlank())) {
            throw new ResponsavelObrigatorioException(
                    "Aluno menor de idade precisa de um responsável cadastrado.");
        }
        this.objetivo = objetivo;
        this.nomeResponsavel = nomeResponsavel;
    }

    /** Regra de negócio: 3 faltas consecutivas bloqueiam novas reservas por 7 dias. */
    public void registrarFalta() {
        faltasConsecutivas++;
        if (faltasConsecutivas >= FALTAS_PARA_BLOQUEIO) {
            bloqueadoAte = LocalDate.now().plusDays(DIAS_DE_BLOQUEIO);
            faltasConsecutivas = 0;
        }
    }

    public void registrarPresenca() {
        faltasConsecutivas = 0;
    }

    public boolean estaBloqueado() {
        return bloqueadoAte != null && !LocalDate.now().isAfter(bloqueadoAte);
    }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public int getFaltasConsecutivas() { return faltasConsecutivas; }
    public LocalDate getBloqueadoAte() { return bloqueadoAte; }
}
