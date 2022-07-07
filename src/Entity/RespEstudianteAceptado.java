package Entity;

public class RespEstudianteAceptado {

    String periodo;
    Integer convocatoria;
    String materia;
    Integer idmateria;
    String estudiante;
    String codigoe;

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

}
