# Implementation Plan - Fix and Enhance Login Background

The goal is to fix the `bg_login.xml` drawable and enhance it to better fit the "FlamePro" branding. The current implementation is a static red shape, which lacks visual depth and interactive feedback (ripple). Additionally, we will address a corrupted resource file `fire_bg.xml`.

## User Review Required

> [!NOTE]
> I am proposing to change the static red background to a fire-inspired gradient (Red to Orange) to match the "FlamePro" theme. I will also add a ripple effect for better touch feedback.

## Proposed Changes

### Resources

#### [MODIFY] [bg_login.xml](file:///C:/Users/Casay/AndroidStudioProjects/FlameProStaff2/app/src/main/res/drawable/bg_login.xml)
- Update to a `ripple` drawable.
- Add a `gradient` inside the shape (from `primary_red` to `flame_orange`).
- Use color resources instead of hardcoded hex values.

#### [MODIFY] [bg_register.xml](file:///C:/Users/Casay/AndroidStudioProjects/FlameProStaff2/app/src/main/res/drawable/bg_register.xml)
- Update to a `ripple` drawable.
- Maintain the light gray theme but add a ripple effect.

#### [DELETE] [fire_bg.xml](file:///C:/Users/Casay/AndroidStudioProjects/FlameProStaff2/app/src/main/res/flogo/fire_bg.xml)
- This file is a binary PNG incorrectly named `.xml` in a non-standard directory. Since `drawable/fire_bg.png` already exists, this redundant and corrupted file will be removed.

#### [MODIFY] [activity_main.xml](file:///C:/Users/Casay/AndroidStudioProjects/FlameProStaff2/app/src/main/res/layout/activity_main.xml)
- Add `android:id="@+id/main"` to the root `ScrollView` to fix the crash in `MainActivity.java`.
- Ensure buttons have `app:backgroundTint="@null"` if needed to respect the custom drawable backgrounds in Material3 theme.

## Verification Plan

### Manual Verification
- Render the `activity_main.xml` layout to verify the new button appearance.
- Deploy the app to a device/emulator to test the ripple effect on buttons.
