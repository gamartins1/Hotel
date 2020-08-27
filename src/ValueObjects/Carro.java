package ValueObjects;

public class Carro extends Veiculo {

    private int ano;
    private String modelo;

    public Carro(VagaEstacionamento vagaEstacionamento) {
        super(vagaEstacionamento);
    }
    


	public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
}
