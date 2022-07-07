package Entity;

public class RespMateriaPostulada {

    private String periodo;
    private Integer convocatoria;
    private String materia;
    private Integer idmateria;
    private String estudiante;
    private Boolean aceptado;
    private Double notaacumulada;
    private Double notafinal;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Integer convocatoria) {
        this.convocatoria = convocatoria;
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

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public Double getNotaacumulada() {
        return notaacumulada;
    }

    public void setNotaacumulada(Double notaacumulada) {
        this.notaacumulada = notaacumulada;
    }

    public Double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(Double notafinal) {
        this.notafinal = notafinal;
    }

}
