package Beans;

public class Membro {
	private String mail;
	private String username;
	private String[] eventi;

	public Membro(String mail, String username) {
		super();
		this.mail = mail;
		this.username = username;
	}

	public Membro(String mail, String username, String[] eventi) {
		super();
		this.mail = mail;
		this.username = username;
		this.eventi = eventi;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getEventi() {
		return eventi;
	}

	public void setEventi(String[] eventi) {
		this.eventi = eventi;
	}
	
	public void mostraInteresse(Evento e) {
		// definizione del metodo fatta nella servlet in caso lo si sposta qua

	}



}