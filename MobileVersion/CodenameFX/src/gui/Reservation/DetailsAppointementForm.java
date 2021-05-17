package gui.Reservation;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.notifications.LocalNotification;
import com.codename1.push.Push;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

public class DetailsAppointementForm extends Form {
    public DetailsAppointementForm(Form previous,Label Date, Label meetID, Label Tutor, Label Student) throws ParseException, java.text.ParseException {
        setTitle("Reservation Details");
        setUIID("Toolbar");
        setLayout(BoxLayout.y());

     /*   Container header = BorderLayout.center(meetName);
        label.addContent(header,data);*/


        Label lmeetdate=new Label("Reservation Date");
        Label lmeetpart=new Label("Reservation Participants");
        Label lmeetstudent=new Label("Student");
        Label lmeettutor=new Label("Tutor");

        TextField tfMeetDate= new TextField(Date.getText(), "Meet Date");
        String output = ZonedDateTime.parse(Date.getText()).toInstant().toString();


        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        // alert sound file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        addAll(tfMeetDate,lmeetpart,lmeetstudent,lmeettutor);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
    }
}
