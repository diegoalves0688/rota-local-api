package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.model.entity.Feedbacks;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public String getFeedback(Long userId) {
        String feedback = Feedbacks.getInstance().feedbackList.get(userId);
        if (feedback != null) {
            Feedbacks.getInstance().feedbackList.remove(userId);
        }
        return feedback;
    }

    @Override
    public void saveFeedback(Long userId, String feedbackCotent) {
        Feedbacks.getInstance().feedbackList.put(userId, feedbackCotent);
    }
    
}
