package ValueObjects;


import Exceptions.EmailException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cliente extends Pessoa {

	private int id;
    private String email;
    private Date ultimaHospedagem;
    private List<Reserva> reservas = new ArrayList<>();
    private Map<Integer, Hospedagem> hospedagens = new HashMap<>();

    public Reserva[] listarReservas() {
        return reservas.toArray(new Reserva[0]);
    }

    public Hospedagem[] listarHospedagem() {
        return hospedagens.values().toArray(new Hospedagem[0]);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
        if(email.indexOf("@") == -1)
            throw new EmailException("Endereço Inválido");
        else
            this.email = email;
    }

    public Date getUltimaHospedagem() {
        return ultimaHospedagem;
    }

    public void setUltimaHospedagem(Date ultimaHospedagem) {
        this.ultimaHospedagem = ultimaHospedagem;
    }

    private List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    private Map<Integer, Hospedagem> getHospedagens() {
        return hospedagens;
    }

    public void setHospedagens(Map<Integer, Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

        
}
