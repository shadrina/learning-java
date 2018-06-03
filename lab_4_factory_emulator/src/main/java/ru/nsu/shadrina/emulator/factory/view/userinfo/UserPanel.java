package ru.nsu.shadrina.emulator.factory.view.userinfo;

import ru.nsu.shadrina.emulator.factory.view.userinfo.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    {
        setPreferredSize(new Dimension(700, 200));
        setLayout(new GridLayout());
        add(new SettingsPanel());
        add(new SettingsPanel());
    }
}
