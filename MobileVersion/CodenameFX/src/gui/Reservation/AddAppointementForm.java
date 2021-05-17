package gui.Reservation;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.Meet;
import javafx.scene.control.DatePicker;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddAppointementForm extends Form{

    public AddAppointementForm(Form previous) {
        setUIID("Toolbar");
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new task");
        setLayout(BoxLayout.y());

        Picker tfMeetName = new Picker();

        tfMeetName.getAllStyles().setMargin(LEFT, 0);

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);


        datePicker.setDate(new Date());

        timePicker.setTime(10 * 60); // 10:00AM = Minutes since midnight



      //  tfMeetPass.getAllStyles().setMargin(LEFT, 0);
//        DatePicker tfMeetDate=new DatePicker();
        ComboBox<Meet> teacher=new ComboBox("");
        ComboBox<Meet> student=new ComboBox("");

        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);


        Container by = BoxLayout.encloseY(
                        datePicker,

                         timePicker


        );
        addAll(by);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente

    }


}