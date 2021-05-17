package gui.Meet;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

public class DetailsMeetForm extends Form{
    public DetailsMeetForm(Form previous, Label meetName, Label meetDate, Label meetLink, Label meetID,Label meetTutor,Label meetStudent,  Label meetPass) throws ParseException, java.text.ParseException {
        setTitle("Meetings Details");
        setUIID("Toolbar");
        setLayout(BoxLayout.y());

     /*   Container header = BorderLayout.center(meetName);
        label.addContent(header,data);*/

        Label lmeetname=new Label("Meet Name");
        Label lmeetdate=new Label("Meet Date");
        Label lmeetpass=new Label("Meet Pass");
        Label lmeetlink=new Label("Meet Link");
        Label lmeetpart=new Label("Meeting Participants");
        Label lmeetstudent=new Label("Student");
        Label lmeettutor=new Label("Tutor");
        TextField tfMeetName = new TextField(meetName.getText(),"Meet Name");
        tfMeetName.getAllStyles().setMargin(LEFT, 0);
        TextField tfMeetPass= new TextField(meetPass.getText(), "Meet Passcode");
        TextField tfMeetDate= new TextField(meetDate.getText(), "Meet Date");
        String output = ZonedDateTime.parse(meetDate.getText()).toInstant().toString();

        Picker datemeet = new Picker();
        datemeet.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        datemeet.setDate(Date.from(Instant.parse (output)));



        TextField tfMeetLink= new TextField(meetLink.getText());
        tfMeetLink.setUIID("Container");
        tfMeetLink.setEditable(false);
        ComboBox<String> Tutor =new ComboBox();
        Tutor.addItem(meetTutor.getText());
        Tutor.setUIID("Container");
        ComboBox<String> Student =new ComboBox();
        Student.setUIID("Container");
        Student.addItem(meetStudent.getText());
        Button btnUpdate = new Button("Update Meet");
        Button btnRemove = new Button("Remove Meet");
        FontImage.setMaterialIcon(btnUpdate, FontImage.MATERIAL_UPDATE);
        FontImage.setMaterialIcon(btnRemove, FontImage.MATERIAL_DELETE);
        btnUpdate.setUIID("LoginButton");
        btnRemove.setUIID("LoginButton");
        btnUpdate.addActionListener(new ActionListener() {

            @Override
          public void actionPerformed(ActionEvent evt) {
             ConnectionRequest req = new ConnectionRequest();
             req.setUrl("http://127.0.0.1:8000/updatemobile?");
             req.setHttpMethod("POST");
             req.addArgument("meetName", tfMeetName.getText());
             req.addArgument("meetPass",tfMeetPass.getText());
             req.addArgument("meetId",meetID.getText());
             System.out.println(meetID.getText());
             req.addResponseListener(new ActionListener<NetworkEvent>() {
             public void actionPerformed(NetworkEvent evt) {
                 byte[] data = (byte[]) evt.getMetaData();
                 String s = new String(data);
                 System.out.println(s);
                 if (s.equals("Success")) {
                     Dialog.show("Confirmation", "Meeting has been update successfully", "Ok", null);
                 }
                 else {
                     Dialog.show("Erreur", "erreur", "Ok", null);
                    }
             }
          });

                                            NetworkManager.getInstance().addToQueue(req);
                                        }
                                    }
        );


        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                    ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://127.0.0.1:8000/mobiledelete?");
                    req.setHttpMethod("POST");
                    req.addArgument("meetId", meetID.getText());
                    System.out.println(meetID.getText());
                    req.addArgument("meetLink",meetLink.getText());
                    System.out.println(meetLink.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {
                        public void actionPerformed(NetworkEvent evt) {

                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);
                            System.out.println(s);
                            if (s.equals("Success")) {
                                Dialog.show("Confirmation", "Meeting has been deleted successfully", "Ok", null);
                            } else {
                                Dialog.show("Erreur", "erreur", "Ok", null);
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);
                }
            }
        );



        addAll(lmeetname,tfMeetName,lmeetpass,tfMeetPass,lmeetdate,datemeet,lmeetlink,tfMeetLink,lmeetpart,lmeetstudent,Student,lmeettutor,Tutor,btnUpdate,btnRemove);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
    }
    }
