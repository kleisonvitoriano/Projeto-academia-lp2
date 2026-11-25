package academia.model;

import academia.exception.ConflitoHorarioException;
import academia.exception.ValidacaoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Instrutor extends Pessoa {
    private String cref;
    private String especialidade;
    private final List<LocalDateTime> agenda = new ArrayList<>();

    public Instrutor(int id, String nome, String cpf, LocalDate dataNascimento,
                     String cref, String especialidade) {
        super(id, nome, cpf, dataNascimento);
        if (cref == null || cref.isBlank()) {
            throw new ValidacaoException("CREF é obrigatório para instrutor.");
        }
        this.cref = cref;
        this.especialidade = especialidade;
    }

    public boolean temConflito(LocalDateTime horario) {
        return agenda.contains(horario);
    }

    /** Regra de negócio: instrutor não pode ter dois compromissos no mesmo horário. */
    public void reservarHorario(LocalDateTime horario) {
        if (temConflito(horario)) {
            throw new ConflitoHorarioException(
                    "Instrutor " + getNome() + " já tem compromisso em " + horario + ".");
        }
        agenda.add(horario);
    }

    public void liberarHorario(LocalDateTime horario) {
        agenda.remove(horario);
    }

    public String getCref() { return cref; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public List<LocalDateTime> getAgenda() { return Collections.unmodifiableList(agenda); }
}
