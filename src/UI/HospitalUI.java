package UI;

import javax.swing.*;
import java.awt.*;

public interface HospitalUI {
    int welcomeFrameWidth = 700;
    int welcomeFrameHeight = 600;

    Font titleFont = new Font("Sans-serif", Font.BOLD, 75);
    Font buttonFont = new Font("Sans-serif", Font.PLAIN, 18);
    Font labelInputFont = new Font("Sans-serif", Font.PLAIN, 15);
    Font tabFont = new Font("Sans-serif", Font.BOLD, 22);
    Font tableFont = new Font("Sans-serif", Font.PLAIN, 16);
    Font tableHeaderFont = new Font("Sans-serif", Font.BOLD, 18);
    Font nameFont = new Font("Sans-serif", Font.BOLD, 55);
    Font informationFont = new Font("Sans-serif", Font.PLAIN, 30);
    Font addPatientFont = new Font("Sans-serif", Font.PLAIN, 20);

    ImageIcon hospitalIcon = new ImageIcon(new ImageIcon("src/Icons/hospital.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    ImageIcon userIcon = new ImageIcon(new ImageIcon("src/Icons/user.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    ImageIcon keyIcon = new ImageIcon(new ImageIcon("src/Icons/key.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

    ImageIcon myInfoIcon = new ImageIcon(new ImageIcon("src/Icons/myInfo.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon closedCasesIcon = new ImageIcon(new ImageIcon("src/Icons/closedCase.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon activeCasesIcon = new ImageIcon(new ImageIcon("src/Icons/activeCase.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon newCaseIcon = new ImageIcon(new ImageIcon("src/Icons/newCase.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon allPatientsIcon = new ImageIcon(new ImageIcon("src/Icons/patients.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

    ImageIcon doctorIcon = new ImageIcon(new ImageIcon("src/Icons/doctor.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    ImageIcon diseaseIcon = new ImageIcon(new ImageIcon("src/Icons/disease.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
}
