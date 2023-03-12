package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.model.lecture.LectureName;

/**
 * Tests that a {@code Module}'s {@code Code}, {@code LectureName} matches user's input.
 */
public class ModuleLectureContainsKeywordsPredicate implements Predicate<ReadOnlyModule> {
    private final ModuleCode moduleCode;
    private final LectureName lectureName;

    /**
     * Creates a ModuleLectureContainsKeywordsPredicate
     * @param code
     * @param lectureName
     */
    public ModuleLectureContainsKeywordsPredicate(ModuleCode code, LectureName lectureName) {
        this.moduleCode = code;
        this.lectureName = lectureName;
    }

    @Override
    public boolean test(ReadOnlyModule module) {
        return this.moduleCode.equals(module.getCode())
            && module.getLectureList().stream()
                .anyMatch(lecture -> lectureName.equals(lecture.getName()));
    }

}