package com.travel.rotalocal.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.rotalocal.dto.FeedbackDTO;
import com.travel.rotalocal.service.FeedbackService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private FeedbackService feedbackService;

    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity getFeedback(@PathVariable Long usuarioId) {
        String feedback = feedbackService.getFeedback(usuarioId);
        if (feedback == null) {
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new FeedbackDTO(usuarioId, feedback));
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> novoFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.saveFeedback(feedbackDTO.getUsuarioId(), feedbackDTO.getConteudo());
        return ResponseEntity.noContent().build();
    }
}
