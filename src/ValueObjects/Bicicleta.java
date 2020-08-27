package ValueObjects;

public class Bicicleta extends Veiculo {

    private boolean marcha;

    public Bicicleta(boolean marcha, VagaEstacionamento vagaEstacionamento) {
        super(vagaEstacionamento);
        this.marcha = marcha;
    }

    public boolean isMarcha() {
        return marcha;
    }

    public void setMarcha(boolean marcha) {
        this.marcha = marcha;
    }


    
}
