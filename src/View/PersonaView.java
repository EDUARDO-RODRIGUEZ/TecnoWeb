package View;

import Entity.EntityPersona;
import java.util.List;

public class PersonaView {

    //RETURN HTML
    public static String ListPerson(List<EntityPersona> personas) {

        String HTMLStart = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Listado</title>\n"
                + "</head>\n"
                + "<style type='text/css'>\n"
                + "    table {\n"
                + "        border-collapse: collapse;\n"
                + "    }\n"
                + "\n"
                + "    th,\n"
                + "    tr,\n"
                + "    td {\n"
                + "        border: 1px solid #444;\n"
                + "        padding: 5px;\n"
                + "        text-align: center;\n"
                + "    }\n"
                + "</style>\n"
                + "<body>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>nombre</th>\n"
                + "                <th>apellido materno</th>\n"
                + "                <th>correo</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>";

        String HTMLEnd = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>.\n";

        String content = "";

        for (EntityPersona persona : personas) {
            String nombre = persona.getPer_nom().trim();
            String apellido = persona.getPer_appm().trim();
            String correo = persona.getPer_email().trim();
            content += String.format(
                    "<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>\n",
                    nombre, apellido, correo
            );

        }
        String HTMLComplete = HTMLStart + content + HTMLEnd;
        return HTMLComplete;
    }

    //RETURN HTML
    public static String ListPrueba(List<String> data) {

        String HTMLStart = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Listado</title>\n"
                + "</head>\n"
                + "<style type='text/css'>\n"
                + "    table {\n"
                + "        border-collapse: collapse;\n"
                + "    }\n"
                + "\n"
                + "    th,\n"
                + "    tr,\n"
                + "    td {\n"
                + "        border: 1px solid #444;\n"
                + "        padding: 5px;\n"
                + "        text-align: center;\n"
                + "    }\n"
                + "</style>\n"
                + "<body>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>nombre</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>";

        String HTMLEnd = "</tbody>\n"
                + "    </table>\n"
                + "<img style=\"width: 100px;\" src=\"https://www.tecnoweb.org.bo/inf513/grupo08sa/imagenes/2022-06-12T12:22:48.767.png\" alt=\"urbe\" />"
                + "</body>\n"
                + "</html>.\n";

        String content = "";
        for (String item : data) {
            content += String.format("<tr><td>%s</td></tr> ", item);
        }
        String HTMLComplete = HTMLStart + content + HTMLEnd;
        return HTMLComplete;
    }

}
