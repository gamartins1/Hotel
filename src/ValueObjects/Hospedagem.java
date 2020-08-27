package ValueObjects;


import Exceptions.DataInvalidaException;
import java.util.Date;

public class Hospedagem {

    private Date entrada;
    private Date saida;
    private Reserva reserva;
    private Cliente cliente;
    private Quarto quarto;
    private String formaaPagamento;
    private int id;
    private boolean hospedagem_utilizada;

    public Hospedagem(Cliente cliente, Quarto quarto) {
        this.cliente = cliente;
        this.quarto = quarto;
    }
    
    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) throws DataInvalidaException {
        if(saida.getTime() < entrada.getTime() &&(!entrada.equals(null))) {
            throw new DataInvalidaException("Data de saída antes da data de entrada");
        } else
            this.saida = saida;
    }

    public void setSaida2(Date saida) {
        this.saida = saida;
    }
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getFormaPagamento() {
        return formaaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaaPagamento = formaPagamento;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isHospedagem_utilizada() {
		return hospedagem_utilizada;
	}

	public void setHospedagem_utilizada(boolean hospedagem_utilizada) {
		this.hospedagem_utilizada = hospedagem_utilizada;
	}    
}
