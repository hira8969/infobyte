package com.exam.controller;

import com.exam.service.AuthenticationService;
import com.exam.util.DialogUtil;
import com.exam.view.DashboardFrame;
import com.exam.view.ProfilePanel;

public class ProfileController {
    private final ProfilePanel profilePanel;
    private final DashboardFrame dashboardFrame;
    private final AuthenticationService authenticationService;

    public ProfileController(ProfilePanel profilePanel, DashboardFrame dashboardFrame,
                             AuthenticationService authenticationService) {
        this.profilePanel = profilePanel;
        this.dashboardFrame = dashboardFrame;
        this.authenticationService = authenticationService;
        bindEvents();
    }

    private void bindEvents() {
        profilePanel.onSave(event -> saveProfile());
        profilePanel.onCancel(event -> dashboardFrame.showHome());
    }

    private void saveProfile() {
        try {
            authenticationService.updateProfile(profilePanel.getDisplayName(), profilePanel.getPassword());
            DialogUtil.showInfo(profilePanel, "Profile Updated Successfully");
            dashboardFrame.showHome();
        } catch (Exception exception) {
            DialogUtil.showError(profilePanel, exception.getMessage());
        }
    }
}
