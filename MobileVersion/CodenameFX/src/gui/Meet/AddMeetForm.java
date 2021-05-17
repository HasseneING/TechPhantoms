package gui.Meet;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Meet;
import javafx.scene.control.DatePicker;

/**
 *
 * @author bhk
 */
public class AddMeetForm extends Form{

    public AddMeetForm(Form previous) {
        setUIID("Toolbar");
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new task");
        setLayout(BoxLayout.y());

        TextField tfMeetName = new TextField("","Meet Name");
        TextField tfMeetPass= new TextField("", "Meet Passcode");

        tfMeetName.getAllStyles().setMargin(LEFT, 0);
        tfMeetPass.getAllStyles().setMargin(LEFT, 0);
//        DatePicker tfMeetDate=new DatePicker();
        ComboBox<Meet> teacher=new ComboBox("");
        ComboBox<Meet> student=new ComboBox("");

        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);


        Button btnValider = new Button("Add Meet");
        btnValider.setUIID("CreateNewAccountButton");
        btnValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfMeetName.getText().length() == 0) || (tfMeetPass.getText().length() == 0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://127.0.0.1:8000/newmobile?");
                    req.setHttpMethod("POST");
                    req.addArgument("meetPass", tfMeetPass.getText());
                    req.addArgument("meetName", tfMeetName.getText());
                    req.addResponseListener(new ActionListener<NetworkEvent>() {
                        public void actionPerformed(NetworkEvent evt) {

                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);
                            System.out.println(s);
                            if (s.equals("Success")) {
                                Dialog.show("Confirmation", "Ajout ok", "Ok", null);
                            } else {
                                Dialog.show("Erreur", "erreur", "Ok", null);
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);
                }
            }
        });

        Container by = BoxLayout.encloseY(
                BorderLayout.center(tfMeetName).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(tfMeetPass).
                        add(BorderLayout.WEST, passwordIcon),
                btnValider

                );
        addAll(by);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente

    }


}