package Modelo.vo;

public class Requerimiento_1 {
    private Integer ID_Proyecto;
    private String Ciudad;
    private String Banco_vinculado;
    private String Constructora;
    private String Clasificacion;

    //CONSTRUCTORES
    public Requerimiento_1() {}


    //SETTERS AND GETTERS
    public Integer getID_Proyecto() {
        return ID_Proyecto;
    }

    public void setID_Proyecto(Integer iD_Proyecto) {
        ID_Proyecto = iD_Proyecto;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getBanco_vinculado() {
        return Banco_vinculado;
    }

    public void setBanco_vinculado(String banco_vinculado) {
        Banco_vinculado = banco_vinculado;
    }

    public String getConstructora() {
        return Constructora;
    }

    public void setConstructora(String constructora) {
        Constructora = constructora;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }

    

}
