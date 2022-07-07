package View;

import Entity.RespDocente;
import java.util.List;

public class DocenteView {

    public static String ListDocente(List<RespDocente> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Materias</title>\n"
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
                + "    <h1>Lista de Docente</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Codigo</th>\n"
                + "                <th>Nombre</th>\n"
                + "                <th>Email</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = " </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespDocente doc : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", doc.getCodigo(), doc.getNombre(), doc.getEmail());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

}
