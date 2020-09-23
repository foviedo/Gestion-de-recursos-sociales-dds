package domain;

import javax.persistence.*;

@Entity
public class Moneda {
	
	@Id
    private String id;
    private String description;
    private String symbol;

    public Moneda(String i, String string, String string2) {
		this.id = i;
		this.description = string;
		this.symbol = string2;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
