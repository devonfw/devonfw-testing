package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.selenium.pages.projectY.DynamicControlsPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DynamicControlsTest extends TheInternetBaseTest {

    private DynamicControlsPage dynamicControlsPage;
    private boolean isCheckboxOnPage;

    @Override
    public void setUp() {
        dynamicControlsPage = shouldTheInternetPageBeOpened().clickDynamicControlsLink();

        logStep("Verify if Dynamic Controls page is opened");
        assertTrue("Unable to open Dynamic Controls page", dynamicControlsPage.isLoaded());

        logStep("Verify if checkbox is visible on page load");
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();
        assertTrue("Checkbox is not present ", isCheckboxOnPage);
    }


    @Test
    public void shouldRemoveAndAddCheckboxDynamically() throws InterruptedException {
        logStep("Click remove button");
        dynamicControlsPage.clickRemoveButton();

        logStep("Wait few seconds to finish loading");
        dynamicControlsPage.waitUntilLoadingIsDone();
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();

        assertFalse("Checkbox has not disappeared", isCheckboxOnPage);

        logStep("Click add button");
        dynamicControlsPage.clickAddButton();

        logStep("Wait few seconds to finish loading");
        dynamicControlsPage.waitUntilLoadingIsDone();
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();

        assertTrue("Checkbox has not disappeared", isCheckboxOnPage);
    }

    @Test
    public void shouldEnableAndDisableInputFormDynamically() throws InterruptedException {
        logStep("Click enable button");
        dynamicControlsPage.clickEnableButton();

        logStep("Wait few seconds to finish loading");
        dynamicControlsPage.waitUntilLoadingIsDone();
        boolean isInputFormEnabled = dynamicControlsPage.isInputFormEnabled();

        assertTrue("InputForm is not enabled", isInputFormEnabled);

        logStep("Click disable button");
        dynamicControlsPage.clickDisableButton();

        logStep("Wait few seconds to finish loading");
        dynamicControlsPage.waitUntilLoadingIsDone();
        boolean isInputFormDisabled = !dynamicControlsPage.isInputFormEnabled();

        assertTrue("InputForm is not disabled", isInputFormDisabled);
    }


}
