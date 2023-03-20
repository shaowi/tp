package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.model.Navigation.NavLayer;

import org.junit.jupiter.api.Test;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class NavigationTest {

    private Navigation navigation = new Navigation();

    @Test
    public void constructor_startAtRoot() {
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
        assertTrue(navigation.isAtLayer(NavLayer.ROOT));
    }

    @Test
    void navigate1_contextChangeToModule() {
        Module mod = TypicalModules.CS2040S;
        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        navigation.navigateTo(mod.getCode());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void navigate2_contextChangeToLecture() {
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        navigation.navigateTo(mod.getCode(), lec.getName());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }


    @Test
    void navigateToModFromRoot_contextChangeToMod() {
        Module mod = TypicalModules.CS2040S;

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        navigation.navigateToModFromRoot(mod.getCode());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void navigateToLecFromMod_contextChangeToLec() {
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        navigation.navigateToModFromRoot(mod.getCode());
        navigation.navigateToLecFromMod(lec.getName());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void back_fromRoot_contextNoChange() {
        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
        navigation.back();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void isAtLayer_fromRootToLecture_wrongLayer() {
        assertTrue(navigation.isAtLayer(NavLayer.ROOT));

        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        navigation.navigateToModFromRoot(mod.getCode());
        assertTrue(navigation.isAtLayer(NavLayer.MODULE));
        assertFalse(navigation.isAtLayer(NavLayer.ROOT));
        assertFalse(navigation.isAtLayer(NavLayer.LECTURE));

        navigation.navigateToLecFromMod(lec.getName());
        assertTrue(navigation.isAtLayer(NavLayer.LECTURE));
        assertFalse(navigation.isAtLayer(NavLayer.ROOT));
        assertFalse(navigation.isAtLayer(NavLayer.MODULE));


        navigation.goToRoot();
        assertTrue(navigation.isAtLayer(NavLayer.ROOT));
    }
}
