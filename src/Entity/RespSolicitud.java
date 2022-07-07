package Entity;

public class RespSolicitud {

    String periodo;
    String materia;
    Integer idmateria;
    String estudiante;
    String codigoe;
    Integer convocatoria;
    Boolean aceptado;
    Double notaac;

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCodigoe() {
        return codigoe;
    }

    public void setCodigoe(String codigoe) {
        this.codigoe = codigoe;
    }

    public Integer getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Integer convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public Double getNotaac() {
        return notaac;
    }

    public void setNotaac(Double notaac) {
        this.notaac = notaac;
    }

}
