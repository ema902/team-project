package view;

import interface_adapters.cancel_timer.CancelTimerController;
import interface_adapters.home.HomeViewModel;
import interface_adapters.home.HomeViewModelListener;
import interface_adapters.open_settings.OpenSettingsController;
import interface_adapters.start_timer.StartTimerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for when the user first enters the program.
 * Provides the initial focus timer interface.
 */
public class HomeView extends JPanel implements HomeViewModelListener {

    private HomeViewModel viewModel;
    private OpenSettingsController settingsController = null;
    private StartTimerController startTimerController = null;
    private CancelTimerController cancelTimerController = null;

    private JLabel timerLabel = new JLabel();

    private JButton startButton;
    private JButton adjustButton;
    private JButton cancelButton;

    private Image backgroundImage;
    private ImageIcon focusTimerImage;
    private ImageIcon breakTimerImage;
    private JLabel imageLabel;

    public HomeView(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addListener(this); // Register this view as a listener of the view model.

        backgroundImage = new ImageIcon("src/images/timerBackground.png").getImage();

        // Main panel containing all sections of the home view.
        JPanel mainTimerPanel = new JPanel();
        mainTimerPanel.setLayout(new BoxLayout(mainTimerPanel, BoxLayout.Y_AXIS));
        mainTimerPanel.setOpaque(false);

        // Top section containing the heading, timer display, and Start button.
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(800, 120));
        topPanel.setOpaque(false);

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(800, 260));
        middlePanel.setOpaque(false);

        focusTimerImage = new ImageIcon("src/images/focusTimerScreen.png");
        breakTimerImage = new ImageIcon("src/images/breakTimerScreen.png");
        imageLabel = new JLabel(focusTimerImage);

        middlePanel.add(imageLabel);

        // Bottom panel of the screen which stores the button to cancel the timer.
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(800, 40));
        bottomPanel.setOpaque(false);

        cancelButton = new JButton("Press to Cancel");
        cancelButton.setForeground(new Color(59, 38, 24));
        cancelButton.setAlignmentX(SwingConstants.CENTER);
        cancelButton.setFont(FontManager.getAppleGaramond(24f));

        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setVisible(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelTimerController.execute();
            }
        });

        bottomPanel.add(cancelButton);

        // Panel containing the timer display and adjustment button.
        JPanel timerPanel = new JPanel();
        timerPanel.setMaximumSize(new Dimension(450, 120));
        timerPanel.setOpaque(false);

        timerLabel = new JLabel("00:00");
        timerLabel.setFont(FontManager.getWinkle(100f));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setForeground(new Color(59, 38, 24));

        // Creates the button used to access timer adjustment settings.
        ImageIcon adjustIcon = new ImageIcon("src/images/AdjustIcon.png");
        adjustButton = new JButton(adjustIcon);
        adjustButton.setBorderPainted(false);
        adjustButton.setFocusPainted(false);
        adjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsController.execute();
            }
        });

        // Wraps the adjust timer button.
        JPanel buttonWrapper = new JPanel(new BorderLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.setPreferredSize(new Dimension(90, 90));
        buttonWrapper.add(adjustButton, BorderLayout.CENTER);

                    timerPanel.add(Box.createHorizontalStrut(90));
            timerPanel.add(timerLabel);
            timerPanel.add(buttonWrapper);

            // Start button for the timer.
            startButton = new CustomButton("Start");
            startButton.setFont(FontManager.getWinkle(48f));
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startTimerController.execute(viewModel.getState().getSessionType());
                }
            });

            topPanel.add(timerPanel);
            topPanel.add(startButton);
            topPanel.add(Box.createVerticalGlue());

            mainTimerPanel.add(topPanel);
            mainTimerPanel.add(middlePanel);
            mainTimerPanel.add(bottomPanel);

            this.setLayout(new BorderLayout());
            this.add(mainTimerPanel, BorderLayout.CENTER);

        }

        /**
         * Sets the background image of the JFrame.
         * @param g the graphics
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        /**
         * Assigns the startTimerController to the Home View.
         * @param startTimerController Start Timer Controller
         */
        public void setStartTimerController(StartTimerController startTimerController) {
            this.startTimerController = startTimerController;
        }

        /**
         * Assign the settingsController to the Home View.
         * @param settingsController Open Settings Controller
         */
        public void setSettingsController(OpenSettingsController settingsController) {
            this.settingsController = settingsController;
        }

        /**
         * Assigns the cancelTimerController to the Home View.
         * @param cancelTimerController Cancel Timer Controller
         */
        public void setCancelTimerController(CancelTimerController cancelTimerController) {
            this.cancelTimerController = cancelTimerController;
        }

        /**
         * Updates the timer display when the Home View Model state changes.
         */
        @Override
        public void stateChanged() {
            String timerDisplay = viewModel.getState().getTimerDisplay();
            boolean isTimerRunning = viewModel.getState().isTimerRunning();
            String sessionType = viewModel.getState().getSessionType();

            timerLabel.setText(timerDisplay);

            // Sets the text of the cancel button based on the session type.
            // Changes the image displayed in the middle panel based on the session type.
            if(sessionType.equals("break")) {
                cancelButton.setText("End Break");
                imageLabel.setIcon(breakTimerImage);
        } else {
            cancelButton.setText("Press to Cancel");
            imageLabel.setIcon(focusTimerImage);
        }

        // Enables start button when the timer isn't running
        startButton.setVisible(!isTimerRunning);
        // Enables adjust button when the timer isn't running and the current session isn't a break.
        adjustButton.setVisible(!isTimerRunning && !viewModel.getState().getSessionType().equals("break"));
        // Enables the cancel button when the timer is running
        cancelButton.setVisible(isTimerRunning);
    }
}