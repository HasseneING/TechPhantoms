package gui.Meet;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeMeetForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/

    public HomeMeetForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Meet");
        Button btnListTasks = new Button("List all Meets");
        Button btnJoinMeet = new Button("Join Meet");


        btnAddTask.addActionListener(e -> new AddMeetForm(current).show());
        btnListTasks.addActionListener(e -> new ListMeetForm(current).show());
        btnJoinMeet.addActionListener(e -> new JoinMeet(current).show());

        addAll(btnAddTask, btnListTasks,btnJoinMeet);

    }

}
