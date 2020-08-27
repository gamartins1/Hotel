package ValueObjects;


public class Motocicleta extends Veiculo {

    private int cilindrada;

    public Motocicleta(VagaEstacionamento vagaEstacionamento) {
        super(vagaEstacionamento);
    }
    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
    
    

}
