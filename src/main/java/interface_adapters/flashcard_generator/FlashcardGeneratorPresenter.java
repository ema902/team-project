package interface_adapters.flashcard_generator;

import interface_adapters.ViewManagerModel;
import interface_adapters.flashcard_studying.FlashcardStudyingState;
import interface_adapters.flashcard_studying.FlashcardStudyingViewModel;
import interface_adapters.menu_bar.NavigationBarState;
import interface_adapters.menu_bar.NavigationBarViewModel;
import use_case.flashcard_generator.FlashcardGeneratorOutputBoundary;
import use_case.flashcard_generator.FlashcardGeneratorOutputData;
import view.ViewNames;

/**
 * Presenter class for the flashcard generator use case.
 */
public class FlashcardGeneratorPresenter implements FlashcardGeneratorOutputBoundary {

    private final FlashcardGeneratorViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final NavigationBarViewModel navigationBarViewModel;
    private final FlashcardStudyingViewModel studyingViewModel;

    public FlashcardGeneratorPresenter(FlashcardGeneratorViewModel viewModel, ViewManagerModel viewManagerModel, NavigationBarViewModel barViewModel, FlashcardStudyingViewModel studyingViewModel){
        this.viewModel = viewModel;
        // ADD other flashcard screen as constructor here
        this.viewManagerModel = viewManagerModel;
        this.navigationBarViewModel = barViewModel;
        this.studyingViewModel = studyingViewModel;
    }
    @Override
    public void prepareFailView(String errorMessage) {
        final FlashcardGeneratorState state = viewModel.getState();
        state.setMessage(errorMessage);
        viewModel.notifyStateChanged();
        
    }

    @Override
    public void prepareSuccessView(FlashcardGeneratorOutputData output) {
    FlashcardGeneratorState generatorState = viewModel.getState();
    generatorState.setSuccess(true);
    FlashcardStudyingState studyingState = studyingViewModel.getState();
    studyingState.setDeckTitle(output.getTitle());
    studyingViewModel.notifyStateChanged();
    //TODO: Set actual deck for studyingViewModel here
    //Switches the screen to Studying View and notifies it changed. Then notify current view change
        NavigationBarState barState = navigationBarViewModel.getState();
        barState.setCurrentViewName(ViewNames.STUDY_FLASHCARDS);
        navigationBarViewModel.setState(barState);
        navigationBarViewModel.notifyStateChanged();
        viewManagerModel.setState(ViewNames.STUDY_FLASHCARDS);
        viewManagerModel.notifyViewChanged();
        viewModel.notifyStateChanged();

    }
}
