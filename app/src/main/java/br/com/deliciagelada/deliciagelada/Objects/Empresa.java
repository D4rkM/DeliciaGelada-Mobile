package br.com.deliciagelada.deliciagelada.Objects;

/**
 * Created by Lápis Lazulo on 08/12/2017.
 */

public class Empresa {

    private String telefone;
    private String nomeLocal;
    private Long latitude;
    private Long longitude;

    public static Empresa create(String telefone, String nomeLocal, Long latitude, Long longitude){

        Empresa e = new Empresa();
        e.setLatitude(latitude);
        e.setLongitude(longitude);
        e.setNomeLocal(nomeLocal);
        e.setTelefone(telefone);
        return e;

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
