package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import entities.Meet;
import entities.Reservation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReservation {

    public ArrayList<Reservation> tasks;

    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }


    public ArrayList<Reservation> parseTasks(String jsonText) throws ParseException {
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient
            l'utilité de new CharArrayReader(json.toCharArray())

            La méthode parse json retourne une MAP<String,Object> ou String est
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Reservation m = new Reservation();

                float id = Float.parseFloat(obj.get("reservationId").toString());
                m.setReservationId((int)id);


                // Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(obj.get("meetDate").toString());

                m.setReservationDate(obj.get("reservationDate").toString());

                Result result = Result.fromContent(obj);
                String tutor = result.getAsString("tutorid/username");
                String student=result.getAsString("studentid/username");
                m.setStudent_id(student);
                m.setTutor_id(tutor);
                tasks.add(m);
            }


        } catch (IOException ex) {

        }

         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web

        */
        return tasks;
    }


    public ArrayList<Reservation> getEvents(){
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost:8000/getreservations?");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {

                try {
                    tasks=parseTasks(new String(req.getResponseData()));
                    System.out.println(tasks);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}