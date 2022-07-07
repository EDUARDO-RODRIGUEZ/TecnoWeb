package Entity;

public class RespMateriaOfertada {

    private String inicio;
    private String fin;
    private Integer convocatoria;
    private String fechaconv;
    private String materia;
    private Integer idmateria;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Integer getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Integer convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getFechaconv() {
        return fechaconv;
    }

    public void setFechaconv(String fechaconv) {
        this.fechaconv = fechaconv;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

    @Override
    public String toString() {
        return "RespMateriaOfertada{" + "inicio=" + inicio + ", fin=" + fin + ", convocatoria=" + convocatoria + ", fechaconv=" + fechaconv + ", materia=" + materia + ", idmateria=" + idmateria + '}';
    }
}
