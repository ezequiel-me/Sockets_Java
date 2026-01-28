package udpserializacion;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private String mensaje;
    private String codif;

    public Mensaje(){}

    public Mensaje(String mensaje, String codif) {
        this.mensaje = mensaje;
        this.codif = codif;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodif() {
        return codif;
    }

    public void setCodif(String codif) {
        this.codif = codif;
    }
}
