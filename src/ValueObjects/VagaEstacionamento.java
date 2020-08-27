package ValueObjects;

import java.util.Collection;
import java.util.HashSet;

public class VagaEstacionamento {

    private int numero;
    private Collection<Veiculo> veiculo = new HashSet<>();
    private Estacionamento estacionamento;

    public VagaEstacionamento(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Collection<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Collection<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    
}
