package gui.Reservation;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import gui.Meet.AddMeetForm;
import gui.Meet.JoinMeet;
import gui.Meet.ListMeetForm;

/**
 *
 * @author bhk
 */
public class HomeAppointementForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/

    public HomeAppointementForm () {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Meet");
        Button btnListTasks = new Button("List all Meets");


        btnAddTask.addActionListener(e -> new AddAppointementForm(current).show());
        btnListTasks.addActionListener(e -> new ListAppointementForm(current).show());

        addAll(btnAddTask, btnListTasks);

    }

}
