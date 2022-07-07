package View;

import Entity.RespSolicitud;
import java.util.List;

public class SolicitudView {

    public static String ShowAllSolictudes(List<RespSolicitud> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Solicitudes de Postulacion</title>\n"
                + "</head>\n"
                + "<style>\n"
                + "    thead {\n"
                + "        background-color: darkslategray;\n"
                + "        color: white;\n"
                + "    }\n"
                + "    table,\n"
                + "    td {\n"
                + "        border: 1px solid;\n"
                + "        border-collapse: collapse;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "    th {\n"
                + "        border: 0;\n"
                + "        padding: 10px;\n"
                + "    }\n"
                + "</style>\n"
                + "<body>\n"
                + "    <h1>Solicitudes a Revisar</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Idmateria</th>\n"
                + "                <th>Estudiante</th>\n"
                + "                <th>Codigoe</th>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Aceptado</th>\n"
                + "                <th>NotaAc</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";

        String HTMLFIN = " </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";
        for (RespSolicitud solicitud : lista) {
            content += String.format(" <tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", solicitud.getPeriodo(), solicitud.getMateria(), solicitud.getIdmateria(),
                    solicitud.getEstudiante(), solicitud.getCodigoe(), solicitud.getConvocatoria(), solicitud.getAceptado(),
                    solicitud.getNotaac());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

}
