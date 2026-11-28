package timer_settings;

import data_access.InMemoryTimerDataAccess;
import entities.TimerSessionFactory;
import org.junit.jupiter.api.Test;
import use_case.update_timer_settings.*;


import static org.junit.jupiter.api.Assertions.*;

class UpdateTimerSettingsTest {

    @Test
    void successTest() {
        UpdateTimerSettingsInputData inputData = new UpdateTimerSettingsInputData(30, 10);
        TimerSessionFactory timerSessionFactory = new TimerSessionFactory();
        InMemoryTimerDataAccess dataAccess = new InMemoryTimerDataAccess(timerSessionFactory);

        UpdateTimerSettingsOutputBoundary presenter = new UpdateTimerSettingsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTimerSettingsOutputData outputData) {
                assertEquals(30, outputData.getFocusDuration());
                assertEquals(10, outputData.getBreakDuration());
                assertEquals(30, dataAccess.getFocusDuration());
                assertEquals(10, dataAccess.getBreakDuration());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The use case should succeed.");
            }
        };

        UpdateTimerSettingsInputBoundary interactor = new UpdateTimerSettingsInteractor(presenter, dataAccess,
                timerSessionFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureBothDurationsTest() {
        UpdateTimerSettingsInputData inputData = new UpdateTimerSettingsInputData(0, -5);
        TimerSessionFactory timerSessionFactory = new TimerSessionFactory();
        InMemoryTimerDataAccess dataAccess = new InMemoryTimerDataAccess(timerSessionFactory);

        UpdateTimerSettingsOutputBoundary presenter = new UpdateTimerSettingsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTimerSettingsOutputData outputData) {
                fail("The success view should not be called.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Focus duration and break duration must be greater than 0.", errorMessage);
            }
        };

        UpdateTimerSettingsInputBoundary interactor = new UpdateTimerSettingsInteractor(presenter, dataAccess,
                timerSessionFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureBreakDurationInvalidTest() {
        UpdateTimerSettingsInputData inputData = new UpdateTimerSettingsInputData(25, 0);
        TimerSessionFactory timerSessionFactory = new TimerSessionFactory();
        InMemoryTimerDataAccess dataAccess = new InMemoryTimerDataAccess(timerSessionFactory);

        UpdateTimerSettingsOutputBoundary presenter = new UpdateTimerSettingsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTimerSettingsOutputData outputData) {
                fail("The success view should not be called.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Break duration must be greater than 0.", errorMessage);
            }
        };

        UpdateTimerSettingsInputBoundary interactor = new UpdateTimerSettingsInteractor(presenter, dataAccess,
                timerSessionFactory);
        interactor.execute(inputData);
    }

    @Test
    void failureFocusDurationInvalidTest() {
        UpdateTimerSettingsInputData inputData = new UpdateTimerSettingsInputData(-1, 5);
        TimerSessionFactory timerSessionFactory = new TimerSessionFactory();
        InMemoryTimerDataAccess dataAccess = new InMemoryTimerDataAccess(timerSessionFactory);

        UpdateTimerSettingsOutputBoundary presenter = new UpdateTimerSettingsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTimerSettingsOutputData outputData) {
                fail("The success view should not be called.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Focus duration must be greater than 0.", errorMessage);
            }
        };

        UpdateTimerSettingsInputBoundary interactor = new UpdateTimerSettingsInteractor(presenter, dataAccess,
                timerSessionFactory);
        interactor.execute(inputData);
    }
}
