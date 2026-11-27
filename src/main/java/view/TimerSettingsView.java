package view;

import interface_adapters.update_timer_settings.UpdateTimerSettingsController;
import interface_adapters.update_timer_settings.TimerSettingsViewModel;
import interface_adapters.update_timer_settings.TimerSettingsViewModelListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The View for when the user is adjusting the timer settings for a study session in the program.
 */
public class TimerSettingsView extends JPanel implements TimerSettingsViewModelListener {

    private TimerSettingsViewModel viewModel;
    private UpdateTimerSettingsController controller = null;
    private final JFrame settingsFrame = new JFrame();

    public TimerSettingsView(TimerSettingsViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addListener(this);

        // Main panel containing all the settings components.
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBackground(new Color(209, 191, 169));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        // Heading section containing the title and subtitle.
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.setPreferredSize(new Dimension(425, 80));
        headingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        headingPanel.setBackground(new Color(209, 191, 169));

        JLabel headingLabel = new JLabel("Your study rhythm");
        headingLabel.setFont(FontManager.getAppleGaramondBold(40f));
        headingLabel.setForeground(new Color(59, 38, 24));
        headingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subheadingLabel = new JLabel("Find a pace that feels like you.");
        subheadingLabel.setFont(FontManager.getAppleGaramondItalic(20f));
        subheadingLabel.setForeground(new Color(82, 58, 40));
        subheadingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headingPanel.add(headingLabel);
        headingPanel.add(subheadingLabel);

        // Focus slider and its label.
        JLabel focusSliderLabel = new JLabel("Focus Duration");
        focusSliderLabel.setFont(FontManager.getAppleGaramond(22f));
        focusSliderLabel.setForeground(new Color(59, 38, 24));
        focusSliderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel focusSliderPanel = new JPanel();
        focusSliderPanel.setLayout(new BoxLayout(focusSliderPanel, BoxLayout.Y_AXIS));
        focusSliderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        focusSliderPanel.setBackground(new Color(209, 191, 169));


        JSlider focusSlider = createStyledSlider(0, 60, 25);

        focusSliderPanel.add(focusSliderLabel);
        focusSliderPanel.add(focusSlider);
        focusSliderPanel.setBackground(new Color(209, 191, 169));

        // Break slider and its label.
        JLabel breakSliderLabel = new JLabel("Break Duration");
        breakSliderLabel.setFont(FontManager.getAppleGaramond(22f));
        breakSliderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        breakSliderLabel.setForeground(new Color(59, 38, 24));

        JPanel breakSliderPanel = new JPanel();
        breakSliderPanel.setLayout(new BoxLayout(breakSliderPanel, BoxLayout.Y_AXIS));
        breakSliderPanel.setBackground(Color.WHITE);
        breakSliderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        breakSliderPanel.setBackground(new Color(209, 191, 169));

        JSlider breakSlider = createStyledSlider(0, 60, 5);

        breakSliderPanel.add(breakSliderLabel);
        breakSliderPanel.add(breakSlider);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.setPreferredSize(new Dimension(400, 30));
        savePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        savePanel.setBackground(new Color(209, 191, 169));

        JButton saveButton = new CustomButton("Save");
        saveButton.setFont(FontManager.getAppleGaramond(20f));
        saveButton.setPreferredSize(new Dimension(100, 45));
        saveButton.setMaximumSize(new Dimension(100, 45));
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Passes the focus and break durations to the Update Timer Settings controller when the user clicks Save.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.execute(focusSlider.getValue(), breakSlider.getValue());
            }
        });

        savePanel.add(saveButton);

        settingsPanel.add(headingPanel);
        settingsPanel.add(Box.createVerticalStrut(20));
        settingsPanel.add(focusSliderPanel);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(breakSliderPanel);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(savePanel);

        settingsFrame.add(settingsPanel);
        settingsFrame.setSize(425, 400);
        settingsFrame.setLocationRelativeTo(null);
    }

    /**
     * Responds to changes in the Update Timer Settings View Model.
     */
    @Override
    public void stateChanged() {
        if (viewModel.getState().getErrorMessage() != null) {
            JOptionPane.showMessageDialog(
                    settingsFrame,
                    viewModel.getState().getErrorMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
            viewModel.getState().setErrorMessage(null);
        } else if (viewModel.getState().getSettingsOpen()){
            this.showWindow();
        } else {
            settingsFrame.setVisible(false);
        }
    }

    /**
     * Assign the controller to the Update Timer Settings View.
     * @param controller Update Timer Settings Controller
     */
    public void setController(UpdateTimerSettingsController controller) {
        this.controller = controller;
    }

    /**
     * Display the timer settings popup window.
     */
    public void showWindow() {
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    public JSlider createStyledSlider(int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        slider.setPreferredSize(new Dimension(390, 50));
        slider.setFont(FontManager.getWinkle(20f));
        slider.setBackground(new Color(209, 191, 169));
        slider.setForeground(new Color(59, 38, 24));
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(false); // TODO: Change later
        slider.setUI(new CustomSlider(slider));
        return slider;
    }

}
