package com.travel.rotalocal.service;

public interface FeedbackService {
    String getFeedback(Long userId);
    void saveFeedback(Long userId, String feedbackCotent);
}
