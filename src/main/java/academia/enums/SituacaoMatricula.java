package academia.enums;

/**
 * Estados possíveis de uma matrícula e as transições permitidas entre eles.
 * CANCELADA é estado final.
 */
public enum SituacaoMatricula {
    ATIVA,
    TRANCADA,
    VENCIDA,
    CANCELADA;

    public boolean permiteTransicaoPara(SituacaoMatricula destino) {
        switch (this) {
            case ATIVA:
                return destino == TRANCADA || destino == VENCIDA || destino == CANCELADA;
            case TRANCADA:
                return destino == ATIVA || destino == CANCELADA;
            case VENCIDA:
                return destino == ATIVA || destino == CANCELADA;
            default: // CANCELADA
                return false;
        }
    }
}
