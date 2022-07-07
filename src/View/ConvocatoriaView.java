package View;

import Entity.RespConvocatoria;
import java.util.List;

public class ConvocatoriaView {

    public static String ListAllConvocatoria(List<RespConvocatoria> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Periodos</title>\n"
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
                + "    <h1>Lista de Convocatorias</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Titulo</th>\n"
                + "                <th>Fecha</th>\n"
                + "                <th>Periodo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespConvocatoria conv : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", conv.getConvocatoria(), conv.getTitulo(), conv.getFecha(), conv.getPeriodo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListAllConvocatoria(List<RespConvocatoria> lista, String titulo) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Periodos</title>\n"
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
                + "    <h1>" + titulo + "</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Titulo</th>\n"
                + "                <th>Fecha</th>\n"
                + "                <th>Periodo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespConvocatoria conv : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", conv.getConvocatoria(), conv.getTitulo(), conv.getFecha(), conv.getPeriodo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }
}
