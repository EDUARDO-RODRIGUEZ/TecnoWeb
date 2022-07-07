package Entity;

public class RespCalificacionMateria {

    private String docente;
    private String materia;
    private String estudiante;
    private Double notaconocimiento;
    private Double notapedagogica;
    private Double notafinal;

    public Double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(Double notafinal) {
        this.notafinal = notafinal;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
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

    public Double getNotaconocimiento() {
        return notaconocimiento;
    }

    public void setNotaconocimiento(Double notaconocimiento) {
        this.notaconocimiento = notaconocimiento;
    }

    public Double getNotapedagogica() {
        return notapedagogica;
    }

    public void setNotapedagogica(Double notapedagogica) {
        this.notapedagogica = notapedagogica;
    }

}
