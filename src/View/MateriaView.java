package View;

import Entity.EntityMateria;
import Entity.RespDocenteMateriagrupoPeriodo;
import Entity.RespMateriaEvaluar;
import Entity.RespMateriaOfertada;
import Entity.RespMateriaPostulada;
import java.util.List;

public class MateriaView {

    public static String ListMateriaOfertada(List<RespMateriaOfertada> lista) {
        String HTMLINICIO = ""
                + "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Materias ofertadas</title>\n"
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
                + "    <h1>Materias Ofertadas</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo In</th>\n"
                + "                <th>Periodo Fi</th>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>FechaConv</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>idMateria</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        String content = "";
        for (RespMateriaOfertada materia : lista) {
            System.out.println(lista);
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "            </tr>\n", materia.getInicio(), materia.getFin(), materia.getConvocatoria(), materia.getFechaconv(), materia.getMateria(), materia.getIdmateria());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListMateriasPostuladas(List<RespMateriaPostulada> lista) {

        String HTMLINICIO = ""
                + "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Materias Postuladas</title>\n"
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
                + "    <h1>Materias Postuladas</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>IdMateria</th>\n"
                + "                <th>Estudiante</th>\n"
                + "                <th>Aceptado</th>\n"
                + "                <th>NotaAc</th>\n"
                + "                <th>NotaFinal</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";
        for (RespMateriaPostulada mp : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%b</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", mp.getPeriodo(), mp.getConvocatoria(), mp.getMateria(), mp.getIdmateria(), mp.getEstudiante(), mp.getAceptado(), mp.getNotaacumulada(), mp.getNotafinal());
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListMateriaEvaluar(List<RespMateriaEvaluar> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Materias a Evaluar</title>\n"
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
                + "    <h1>Materias a Evaluar</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Convocatoria</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Idmateria</th>\n"
                + "                <th>Docente</th>\n"
                + "                <th>Fecha</th>\n"
                + "                <th>Hora</th>\n"
                + "                <th>Lugar</th>\n"
                + "                <th>Examen</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = " </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        String content = "";

        for (RespMateriaEvaluar mat : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>", mat.getPeriodo(), mat.getConvocatoria(), mat.getMateria(), mat.getIdmateria(), mat.getDocente(), mat.getFecha(), mat.getHora(), mat.getLugar(), mat.getExamen());
        }

        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListMateria(List<EntityMateria> lista) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
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
                + "    <h1>Lista de Materias</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Id</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Sigla</th>\n"
                + "                <th>CargaHoraria</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (EntityMateria mat : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "            </tr>", mat.getIdmateria(), mat.getNombre(), mat.getSigla(), mat.getCargaHoraria()
            );
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListMateria(List<EntityMateria> lista, String titulo) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
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
                + "                <th>Id</th>\n"
                + "                <th>Materia</th>\n"
                + "                <th>Sigla</th>\n"
                + "                <th>CargaHoraria</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        String HTMLFIN = "</tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";
        String content = "";
        for (EntityMateria mat : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%d</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%d</td>\n"
                    + "            </tr>", mat.getIdmateria(), mat.getNombre(), mat.getSigla(), mat.getCargaHoraria()
            );
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }

    public static String ListDocMatGrupPer(List<RespDocenteMateriagrupoPeriodo> lista, String titulo) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
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
                + "    <h1>" + titulo + "</h1>\n"
                + "    <table>\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Periodo</th>\n"
                + "                <th>Docente</th>\n"
                + "                <th>Auxiliar</th>\n"
                + "                <th>Telefono</th>\n"
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
        for (RespDocenteMateriagrupoPeriodo obj : lista) {
            content += String.format("<tr>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "                <td>%s</td>\n"
                    + "            </tr>",
                    obj.getPeriodo(), obj.getDocente(), obj.getAuxiliar(), obj.getTelefono(), obj.getMateria(), obj.getGrupo()
            );
        }
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }
}
