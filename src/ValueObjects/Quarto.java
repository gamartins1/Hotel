package ValueObjects;

import java.util.ArrayList;
import java.util.Collection;

public class Quarto {

    private double valorDiaria;
    private TipoQuarto tipoQuarto;
    private Reserva reserva;
    private Collection<Hospedagem> hospedagem = new ArrayList<>();
    private int numero;
    private boolean quartoDisponivel;

    public Quarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Quarto(TipoQuarto tipoQuarto, double valorDiaria) {
    	this.tipoQuarto = tipoQuarto;
    	this.valorDiaria = valorDiaria;
	}

	public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Collection<Hospedagem> getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Collection<Hospedagem> hospedagem) {
        this.hospedagem = hospedagem;
    }

	public boolean isQuartoDisponivel() {
		return quartoDisponivel;
	}

	public void setQuartoDisponivel(boolean quartoDisponivel) {
		this.quartoDisponivel = quartoDisponivel;
	}
    
    
}
