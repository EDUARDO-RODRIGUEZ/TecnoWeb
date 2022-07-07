package App;

import Config.Validation;
import Controller.ActividadController;
import Controller.ConvocatoriaController;
import Controller.CronogramaController;
import Controller.DocenteController;
import Controller.EstudianteController;
import Controller.GrupoControler;
import Controller.MateriaController;
import Controller.MateriaGrupoController;
import Controller.PeriodoController;
import Controller.SolicitudController;
import Entity.EntityGrupo;
import Entity.EntityMateria;
import Entity.EntityPeriodo;
import Entity.RespActividad;
import Entity.RespCalificacionEstudiante;
import Entity.RespCalificacionMateria;
import Entity.RespConvocatoria;
import Entity.RespCronograma;
import Entity.RespDocente;
import Entity.RespDocenteMatGrupo;
import Entity.RespDocenteMateriagrupoPeriodo;
import Entity.RespEstudianteAceptado;
import Entity.RespMateriaEvaluar;
import Entity.RespMateriaGrupo;
import Entity.RespMateriaOfertada;
import Entity.RespMateriaPostulada;
import Entity.RespSolicitud;
import Helper.Cadena;
import Service.ClientePop;
import Service.ClienteSmtp;
import View.ActividadView;
import View.ConvocatoriaView;
import View.CronogramaView;
import View.DocenteView;
import View.EstudianteView;
import View.GrupoView;
import View.MateriaGrupoView;
import View.MateriaView;
import View.PeriodoView;
import View.ResponseView;
import View.SolicitudView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AppServer {

    private ClientePop clientePop;
    private ClienteSmtp clienteSmtp;

    public AppServer() {
        this.clientePop = new ClientePop();
        this.clienteSmtp = new ClienteSmtp("www.tecnoweb.org.bo");
    }

    public void OnEventServer() {
        while (true) {
            try {
                System.out.println("--------------------------------");
                System.out.println(LocalDateTime.now());
                start();
                System.out.println("-----------Esperando 3s---------");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());;
            }
        }
    }

    private void start() {
        clientePop.start();
        if (clientePop.NEWMESSAGE) {
            sendMessage();
            clientePop.NEWMESSAGE = false;
            clientePop.MESSAGE = "";
        }
    }

    //Formato ejemplo: REG_USUARIO (name,email,password)
    private String[] obtenerParams(String subject) {
        String filter = subject.substring(subject.indexOf("(") + 1, subject.indexOf(")"));
        String[] Params = filter.split(",");
        return Params;
    }

    private void sendMessage() {
        ArrayList<String> search = new ArrayList(Arrays.asList("From"));
        HashMap map = new HashMap();
        Cadena.Search(clientePop.MESSAGE, search, map);
        String lineaFrom = (String) map.get("From");
        String lineaSubject = Cadena.getRouteParams(clientePop.MESSAGE);
        lineaSubject = lineaSubject.replace("Subject:", "").trim();
        String correo = lineaFrom.substring(lineaFrom.indexOf("<") + 1, lineaFrom.indexOf(">"));
        String[] subject = lineaSubject.split("_");
        String route = subject[0].trim();
        String[] params = obtenerParams(subject[1].trim());
        routes(route, params, correo, clientePop.MESSAGE);
    }

    private void routes(String route, String[] params, String correo, String message) {

        for (String param : params) {
            System.out.println(param);
        }

        switch (route) {
            
            /*GESTIONAR USUARIO*/
            case "CREATEESTUDIANTE":
                if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                String response = EstudianteController.CreateEstudiante(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Usuario Estudiante"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "UPDATEESTUDIANTE":
                if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                response = EstudianteController.UpdateUsuario(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Actualizar Usuario Estudiante"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "CREATEDOCENTE":
                 if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                response = DocenteController.CreateDocente(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Usuario Docente"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "UPDATEDOCENTE":
                 if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                response = EstudianteController.UpdateUsuario(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Actualizar Usuario Docente"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "CREATEAUXILIAR":
                 if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                response = EstudianteController.CreateAuxiliar(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Auxiliar"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            /*GESTIONAR PERIODO*/
            case "SHOWPERIODO":
                List<EntityPeriodo> allPeriodo = PeriodoController.ShowPeriodo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Periodo"),
                        PeriodoView.ListPeriodo(allPeriodo)
                );
                break;
            case "CREATEPERIODO":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                response = PeriodoController.CreatePeriodo(params);
                List<EntityPeriodo> periodos = PeriodoController.ShowPeriodo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Crear Periodo"),
                        PeriodoView.ListPeriodo(periodos, response)
                );
                break;

            case "SHOWCONV":
                List<RespConvocatoria> AllConvocatoria = ConvocatoriaController.ShowAllConvocatoria();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listar Convocatorias"),
                        ConvocatoriaView.ListAllConvocatoria(AllConvocatoria)
                );
                break;

            case "CREATECONV":
                if (!Validation.isValid(params, 4, clienteSmtp, correo))  break;
                response = ConvocatoriaController.CreateConvocatoria(params);
                AllConvocatoria = ConvocatoriaController.ShowAllConvocatoria();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Convocatoria"),
                        ConvocatoriaView.ListAllConvocatoria(AllConvocatoria, response)
                );
                break;

            /*Gestionar Cronograma*/
            case "SHOWCRONOGRAMA":
                List<RespCronograma> AllCronograma = CronogramaController.ShowAllCronograma();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Cronogramas"),
                        CronogramaView.ListAllCronograma(AllCronograma)
                );
                break;

            case "CREATECRONOGRAMA":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                response = CronogramaController.CreateCronograma(params);
                AllCronograma = CronogramaController.ShowAllCronograma();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Cronogramas"),
                        CronogramaView.ListAllCronograma(AllCronograma, response)
                );
                break;
            case "SHOWACTIVIDAD":
                if (!Validation.isValid(params, 1, clienteSmtp, correo))  break;
                List<RespActividad> AllActividad = ActividadController.ShowActividad(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Actividades"),
                        ActividadView.ListActividades(AllActividad)
                );
                break;
            case "CREATEACTIVIDAD":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                response = ActividadController.CreateActividad(params);
                AllActividad = ActividadController.ShowActividad(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Actividad"),
                        ActividadView.ListActividades(AllActividad, response)
                );
                break;
                
            case "CREATEMATOF":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                response = MateriaController.CreateMateriaOfertada(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Materia ofertada"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "SHOWDOCENTE":
                List<RespDocente> AllDocente = DocenteController.ShowAllDocente();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado de Docente"),
                        DocenteView.ListDocente(AllDocente)
                );
                break;

            case "ADDEVALUADOR":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                response = DocenteController.AddEvaluador(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Agregacion de Evaluador"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            case "ADDDOCMATGRUPOPERIODO":
                if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                response = MateriaController.AgregarDocMatGrupoPeriodo(params);
                List<RespDocenteMateriagrupoPeriodo> AllDocenteMateriagrupPer = MateriaController.ShowDocAuxMatGrupPer(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Agregacion de Docente Materia Grupo Periodo"),
                        MateriaView.ListDocMatGrupPer(AllDocenteMateriagrupPer, response)
                );
                break;
                
            /*Comandos Ayuda*/    
            case "SHOWMATERIA":
                List<EntityMateria> AllMateria = MateriaController.ShowAllMateria();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Materias"),
                        MateriaView.ListMateria(AllMateria)
                );
                break;

            case "CREATEMATERIA":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                response = MateriaController.CreateMateria(params);
                AllMateria = MateriaController.ShowAllMateria();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Materias"),
                        MateriaView.ListMateria(AllMateria, response)
                );
                break;

            case "SHOWGRUPO":
                List<EntityGrupo> AllGrupo = GrupoControler.ShowAllGrupo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Grupo"),
                        GrupoView.ListAllGrupo(AllGrupo)
                );
                break;

            case "CREATEGRUPO":
                if (!Validation.isValid(params, 1, clienteSmtp, correo))  break;
                response = GrupoControler.CreateGrupo(params);
                AllGrupo = GrupoControler.ShowAllGrupo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Grupos"),
                        GrupoView.ListAllGrupo(AllGrupo, response)
                );
                break;

            case "SHOWMATGRUPO":
                List<RespMateriaGrupo> AllMateriaGrupo = MateriaGrupoController.ShowAllMateriaGrupo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Materias Grupos"),
                        MateriaGrupoView.ListMateriaGrupo(AllMateriaGrupo)
                );
                break;

            case "CREATEMATGRUP":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                response = MateriaGrupoController.CreateMateriaGrupo(params);
                AllMateriaGrupo = MateriaGrupoController.ShowAllMateriaGrupo();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Materia Grupo"),
                        MateriaGrupoView.ListMateriaGrupo(AllMateriaGrupo, response)
                );
                break;

            case "SHOWDOCMATGRUP":
                List<RespDocenteMatGrupo> AllDocMatGrup = MateriaGrupoController.ShowAllDocMatGrup();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Listado Docente Materia Grupo"),
                        MateriaGrupoView.ListDocMatGrup(AllDocMatGrup)
                );
                break;

            case "CREATEDOCMATGRUP":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                response = MateriaGrupoController.CreateDocMatGrup(params);
                AllDocMatGrup = MateriaGrupoController.ShowAllDocMatGrup();
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Creacion Docente Materia Grupo"),
                        MateriaGrupoView.ListDocMatGrup(AllDocMatGrup, response)
                );
                break;

           

            /*--------------Gestionar Postulante----------*/
 /*----------------Estudiante-----------------*/
            case "SHOWMATOF":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                List<RespMateriaOfertada> lista = MateriaController.getAllMateriaOfertada(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Lista de Materias OFertadas"),
                        MateriaView.ListMateriaOfertada(lista)
                );
                break;

            case "SENDSOLICITUD":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                String r1 = SolicitudController.sendSolicitud(params, message);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Envio de la Solictiud"),
                        ResponseView.ResponseConfirm(r1)
                );
                break;

            case "SHOWMATPOST":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                List<RespMateriaPostulada> listMatpost = EstudianteController.ShowMateriaPostuladas(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Materias postuladas"),
                        MateriaView.ListMateriasPostuladas(listMatpost)
                );
                break;

            case "SHOWCALIFTMAT":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                List<RespCalificacionMateria> CalificacionMateria = EstudianteController.ShowCalificacionMateria(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Calificacion Materia"),
                        EstudianteView.ListCalifiacionMateria(CalificacionMateria)
                );
                break;

            /*----------------Vicedecano-----------------*/
            case "SHOWALLSOL":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                List<RespSolicitud> AllSolicitudes = SolicitudController.ShowAllSolicitudes(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Solicitudes"),
                        SolicitudView.ShowAllSolictudes(AllSolicitudes)
                );
                break;

            case "SENDRESPSOL":
                if (!Validation.isValid(params, 5, clienteSmtp, correo))  break;
                response = SolicitudController.SendResponseSolicitud(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Envio de Respuesta de la Solictiud"),
                        ResponseView.ResponseConfirm(response)
                );
                break;

            /*--------------Gestionar Docente----------*/
            case "SHOWMATEVALUAR":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                List<RespMateriaEvaluar> MateriasEvaluar = DocenteController.ShowMateriasEvaluar(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Materias a Evaluar"),
                        MateriaView.ListMateriaEvaluar(MateriasEvaluar)
                );
                break;

            case "SHOWESTUDIANTESACEPT":
                if (!Validation.isValid(params, 2, clienteSmtp, correo))  break;
                List<RespEstudianteAceptado> EstudiantesAceptados = DocenteController.ShowEstudiantesAceptados(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Estudiantes Aceptados para el Examen"),
                        EstudianteView.ListEstudianteAceptado(EstudiantesAceptados)
                );
                break;

            case "ADDINFOEXAMEN":
                if (!Validation.isValid(params, 6, clienteSmtp, correo))  break;
                String r3 = DocenteController.AgregarInfoExamen(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Adicion de Informacion Examen"),
                        ResponseView.ResponseConfirm(r3)
                );
                break;

            /*--------------Gestionar Examen----------*/
           /*------------------Docente.--------------*/
            case "ADDCALIFICACION":
                String r4 = DocenteController.ADDCALIFICACION(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Agregacion de Calificacion"),
                        ResponseView.ResponseConfirm(r4)
                );
                break;

            case "SHOWCALIFESTUDIANTE":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                List<RespCalificacionEstudiante> CalificacionEstudiante = DocenteController.ShowCalificacionEstudiante(params);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Calificaciones Estudiantes"),
                        EstudianteView.ListEstudianteCalificado(CalificacionEstudiante)
                );
                break;

            case "UPLOADEXAMEN":
                if (!Validation.isValid(params, 3, clienteSmtp, correo))  break;
                String ResponseUpload = DocenteController.UploadExamen(params, message);
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Subida de Examen"),
                        ResponseView.ResponseConfirm(ResponseUpload)
                );
                break;

            default:
                clienteSmtp.sendMessage(
                        "grupo08sa@tecnoweb.org.bo",
                        correo,
                        String.format("Validacion"),
                        ResponseView.ResponseConfirm("Comando no Encontrado")
                );
                break;
        }

    }

}
