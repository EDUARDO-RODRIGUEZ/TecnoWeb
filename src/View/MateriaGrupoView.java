package View;

import Entity.RespDocenteMatGrupo;
import Entity.RespMateriaGrupo;
import java.util.List;

public class MateriaGrupoView {

    public static String ListMateriaGrupo(List<RespMateriaGrupo> lista) {
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
                + "    <h1>Lista de Materia Grupo</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Grupo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "  </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespMateriaGrupo mg : lista) {
            content += String.format(" <tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", mg.getMateria(), mg.getGrupo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListMateriaGrupo(List<RespMateriaGrupo> lista, String titulo) {
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
                + "    <h1>" + titulo + "</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Grupo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "  </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (RespMateriaGrupo mg : lista) {
            content += String.format(" <tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", mg.getMateria(), mg.getGrupo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListDocMatGrup(List<RespDocenteMatGrupo> lista) {
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
                + "    <h1>Lista de Docente Materia Grupo</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Docente</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Grupo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";

        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";
        for (RespDocenteMatGrupo dmg : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", dmg.getDocente(), dmg.getMateria(), dmg.getGrupo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListDocMatGrup(List<RespDocenteMatGrupo> lista, String titulo) {
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
                + "    <h1>" + titulo + "</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Docente</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Grupo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";

        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";
        for (RespDocenteMatGrupo dmg : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", dmg.getDocente(), dmg.getMateria(), dmg.getGrupo());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }
}
