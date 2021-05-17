package gui.Meet;


import com.codename1.components.*;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import entities.Meet;
import esprit.pidev.MyApplication;
import services.ServiceMeet;

import java.util.List;

/**
 *
 * @author bhk
 */
public class ListMeetForm extends Form {
Form current;
    public ListMeetForm(Form previous) {
        this.setLayout(BoxLayout.y());
        current=this;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);


        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("Remaining meets", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("Completed Meets", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Meet Dashboard", "Title")
                        )
                ),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
         fab.addActionListener(e->{
                 new AddMeetForm(previous).show();
                 }
         );


        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        add(new Label("Upcoming Meets", "TodayTitle"));
        Accordion upcom = new Accordion();
        add(upcom);
        List<Meet> upcommingMeets = ServiceMeet.getInstance().getUpcommingMeets();
        addEntry(upcom,upcommingMeets);

        add(new Label("Previous Meets", "TodayTitle"));
        Accordion prev = new Accordion();
        add(prev);
        List<Meet> previousmeets= ServiceMeet.getInstance().getPreviousMeets();
        addEntry(prev,previousmeets);

        }

    void addEntry(Accordion accr,List<Meet> meets){
       //
        for (int i = 0; i < meets.size(); i++) {
            System.out.println(meets.get(i));
            Label meetID=new Label(String.valueOf(meets.get(i).getMeet_id()));
            Label meetName = new Label(meets.get(i).getMeet_name());
            Label meetPass=new Label(meets.get(i).getMeet_pass());
            Label meetTutor= new Label(meets.get(i).getTutor_id());
            Label meetStudent=new Label(meets.get(i).getStudent_id());
            Label meetLink=new Label(meets.get(i).getMeet_link());
            Label meetDate = new Label(meets.get(i).getMeet_date());
            accr.setBackgroundItemUIID("Container");
            Button details=new Button("Meeting Details");
            FontImage.setMaterialIcon(details, FontImage.MATERIAL_SETTINGS);

            SpanLabel data=new SpanLabel("  Meet Link " + ":  " + meetLink.getText() + "\n"
                    + "  Meet PassCode " + ":  " + meetPass.getText() + "\n"
                    + "  Meet Date " + ":\n" + "  " + meetDate.getText() + "\n"
                    + "  Meet Tutor " + ":  " + meetTutor.getText() + "\n"
                    + "  Meet Student " + ":  " + meetStudent.getText() + "\n"

                    , "Container"
            );
            data.add(BorderLayout.SOUTH,details);
            MultiButton icon=new MultiButton("");
            FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
            icon.setUIID("Container");
            icon.setEmblem(arrowDown);

            icon.setUIIDLine1("TodayEntry");
            icon.setIconUIID("Container");
            icon.setIcon(createCircleLine(0xd997f1, icon.getPreferredH(),  false));
            Container header = BorderLayout.center(meetName).add(BorderLayout.WEST,icon);
            accr.addContent(header,data);


            details.addActionListener(ee-> {
                try {
                    new DetailsMeetForm(current,meetName,meetDate,meetLink,meetID,meetTutor,meetStudent,meetPass).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                accr.removeContent(data);
                        accr.animateLayout(200);
                    }
            );
        }
}
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }


}



