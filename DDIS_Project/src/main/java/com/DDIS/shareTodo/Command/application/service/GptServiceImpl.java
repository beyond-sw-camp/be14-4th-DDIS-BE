//package com.DDIS.shareTodo.Command.application.service;
//
//import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class GptServiceImpl implements GptService {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${openai.api-key}")
//    private String OPENAI_API_KEY;
//
//    @Override
//    public List<ShareTodo> generateTodoList(String topic) {
//        String prompt = createPrompt(topic);
//        String gptResponse = requestGpt(prompt);
//
//        return parseResponseToTodos(topic, gptResponse);
//    }
//
//    private String createPrompt(String topic) {
//        return String.format(
//                "'%s'을(를) 주제로 공동 Todo 리스트를 5개 만들어줘. " +
//                        "각 항목은 짧고 명확한 문장으로 해줘. " +
//                        "답변은 '1. ~~~', '2. ~~~' 이런 식으로 번호를 붙여서 줘.",
//                topic
//        );
//    }
//
//    private String requestGpt(String prompt) {
//        String gptApiUrl = "https://api.openai.com/v1/chat/completions";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(OPENAI_API_KEY);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", "gpt-3.5-turbo");
//        requestBody.put("messages", List.of(Map.of(
//                "role", "user",
//                "content", prompt
//        )));
//        requestBody.put("temperature", 0.7);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                gptApiUrl,
//                HttpMethod.POST,
//                entity,
//                String.class
//        );
//
//        return response.getBody();
//    }
//
//    private List<ShareTodo> parseResponseToTodos(String topic, String gptResponse) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(gptResponse);
//
//            String content = root
//                    .path("choices")
//                    .get(0)
//                    .path("message")
//                    .path("content")
//                    .asText();
//
//            List<ShareTodo> todos = new ArrayList<>();
//            String[] lines = content.split("\n");
//
//            for (String line : lines) {
//                String todoName = line.replaceAll("^\\d+\\.\\s*", "").trim();
//                if (!todoName.isEmpty()) {
//                    todos.add(ShareTodo.builder()
//                            .shareTodoNum(null)
//                            .shareTodoName(todoName)
//                            .post(null)
//                            .pinOrder(0)
//                            .build());
//                }
//            }
//            return todos;
//        } catch (Exception e) {
//            throw new RuntimeException("GPT 응답 파싱 실패", e);
//        }
//    }
//}
