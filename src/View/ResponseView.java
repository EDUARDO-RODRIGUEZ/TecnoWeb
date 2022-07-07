package View;

public class ResponseView {

    public static String ResponseConfirm(String response) {
        String HTMLINICIO = "Content-Type: text/html; charset=\"UTF-8\";\n"
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Respuesta Solicitud</title>\n"
                + "</head>\n"
                + "<body>\n";
        String HTMLFIN = "</body>\n"
                + "</html>";
        String content = String.format("<h1>%s</h1>", response);
        String HTMLComplete = HTMLINICIO + content + HTMLFIN;
        return HTMLComplete;
    }
}
