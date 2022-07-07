package View;

import Entity.RespEstudianteAceptado;
import Entity.RespCalificacionEstudiante;
import Entity.RespCalificacionMateria;
import java.util.List;

public class EstudianteView {

    public static String ListEstudianteAceptado(List<RespEstudianteAceptado> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Estudiantes Aceptados</title>\n"
                + "</head>\n"
                + "<style>\n"
                + "    thead {\n"
                + "        background-color: darkslategray;\n"
                + "        color: white;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    td {\n"
                + "        border: 1px solid;\n"
                + "        border-collapse: collapse;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "\n"
                + "    th {\n"
                + "        border: 0;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "</style>\n"
                + "<body>\n"
                + "    <h1>Estudiantes Acepados para rendir el examen</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>idmateria</th>\n"
                + "                <th>Estudiante</th>\n"
                + "                <th>Codigoe</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = " </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";
        for (RespEstudianteAceptado estAc : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", estAc.getPeriodo(), estAc.getConvocatoria(), estAc.getMateria(), estAc.getIdmateria(), estAc.getEstudiante(), estAc.getCodigoe());
        }

        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListEstudianteCalificado(List<RespCalificacionEstudiante> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Estudiantes Calificados</title>\n"
                + "</head>\n"
                + "<style>\n"
                + "    thead {\n"
                + "        background-color: darkslategray;\n"
                + "        color: white;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    td {\n"
                + "        border: 1px solid;\n"
                + "        border-collapse: collapse;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "\n"
                + "    th {\n"
                + "        border: 0;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "</style>\n"
                + "<body>\n"
                + "    <h1>Estudiantes Calificados</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Codigod</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>IdMateria</th>\n"
                + "                <th>IdConvocatoria</th>\n"
                + "                <th>Estudiante</th>\n"
                + "                <th>NotaPedagogica</th>\n"
                + "                <th>NotaConocimiento</th>\n"
                + "                <th>NotaFinal</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespCalificacionEstudiante CalEst : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", CalEst.getPeriodo(), CalEst.getCodigod(), CalEst.getMateria(), CalEst.getIdmateria(), CalEst.getIdconvocatoria(), CalEst.getEstudiante(), CalEst.getNotapedagogica(), CalEst.getNotaconocimiento(), CalEst.getNotafinal());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListCalifiacionMateria(List<RespCalificacionMateria> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Mis Calificiones</title>\n"
                + "</head>\n"
                + "<style>\n"
                + "    thead {\n"
                + "        background-color: darkslategray;\n"
                + "        color: white;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    td {\n"
                + "        border: 1px solid;\n"
                + "        border-collapse: collapse;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "\n"
                + "    th {\n"
                + "        border: 0;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "</style>\n"
                + "\n"
                + "<body>\n"
                + "    <h1>Mi Calificacion</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Docente</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Estudiante</th>\n"
                + "                <th>NotaConocimiento</th>\n"
                + "                <th>NotaPedagogica</th>\n"
                + "                <th>NotaFinal</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = " </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespCalificacionMateria cal : lista) {
            content += String.format(" <tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", cal.getDocente(), cal.getMateria(), cal.getEstudiante(), cal.getNotaconocimiento(), cal.getNotapedagogica(), cal.getNotafinal());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }
}
