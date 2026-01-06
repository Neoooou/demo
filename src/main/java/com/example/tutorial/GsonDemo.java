package com.example.tutorial;

import com.example.algorithm.leetcode.LRUCache;
import com.example.app.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.aggregation.DateOperators;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GsonDemo {
    public static void main(String[] args) {
////        GsonBuilder builder = new GsonBuilder();
////        builder.setPrettyPrinting();
////        Gson gson = builder.create();
////
////        Employee employee = new Employee();
////        employee.setName("Neo");
////        employee.setRole("Dev");
////        employee.setAge(20);
////        employee.setGender((byte) 1);
////        employee.setSalary(100.05);
////        employee.setLeader(false);
////        String jsonString = gson.toJson(employee);
////        System.out.println(jsonString);
////
////        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
////        System.out.println(object.get("age").toString());
////        System.out.println(object.get("gender").getAsString());
////        System.out.println(object.get("salary").getAsString());
////        System.out.println(object.get("isLeader").getAsString());
////
//        String timezoneStr = "Europe/Paris";
//        TimeZone zone = TimeZone.getTimeZone(timezoneStr);
////        System.out.println(timezoneStr + " useDaylightTime=" + zone.useDaylightTime());
////
////
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.YEAR, 2024);
////        calendar.set(Calendar.MONTH, 9);
////        calendar.set(Calendar.DAY_OF_MONTH, 26);
////        calendar.set(Calendar.HOUR, 23);
////        Date date = calendar.getTime();
////        System.out.println("Date: " + date);
////        date = toSpecificZone(date, zone);
//
////        System.out.println("transferred date:" + date);
////        System.out.println("inDayLightTime: " + zone.inDaylightTime(date));
//        Date date = new Date();
//        System.out.println(date);
////        toSpecificZone(, zone);
//
//
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson("{}", JsonObject.class);
//        JsonElement element = jsonObject.get("a");
//        System.out.println(element.getAsString());

        String conv = "[User]: Intenta reinstalar la aplicacino[User]: Lo que sucede es que esto ya es algo automatizado, incluso puede ser un error de la plataforma[User]: Y como se quita eso[Robot]: Sorry to hear about this automatic system error. May I know which system part?[User]: I don't know, perhaps the payment.[Agent]: Hola[Agent]: Vaya entiendo, lo que sucede es que a mi me sales como anonimo por lo que no puedo buscarlo manualmente, sin elnumero de pedido no sabria darte la informacion ..y no me sale aquí en orden[User]: No me lo se[Agent]: Buenas tardes, es un gusto atenderte. Vaya, siento escuchar esto, me permites el numero de pedido por favor. [User]: Me han llegado dos pedidos mal[Agent]:Hola, mi nombre es Julian y soy el agente que atenderá tu consulta. ¿En qué puedo ayudarte?¡Bienvenido al servicio de Atención al cliente de Miravia!";

        String prompt = "- Scenario:\n" +
                "You're a customer support specialist for an e-commerce platform, tasked with summarizing chat conversations between user and agent.\n" +
                "\n" +
                "-  Instructions:\n" +
                "Craft a bullet-point summary of the chat conversation focusing on the situation, action, and resolution. Carefully distinguish the behavior of customers and agents, and carefully distinguish whether events are caused by customers, agents, or side effects of other events. Ensure that the generated summary is logical and free from grammatical errors. Limit your summary to 3 key sentences, one for each aspect, within a 70-word total. \n" +
                "\n" +
                "- Format:\n" +
                "Begin each point with \"S\" for Situation, \"A\" for Action, or \"R\" for Resolution. Each should be followed by a hyphen and a succinct statement. Ensure each statement provides unique information and avoid repetition.\n" +
                "\"S\" - Concisely state the reason for the customer's contact and their tone.\n" +
                "\"A\" - Describe the precise measures customer support took to address the issue.\n" +
                "\"R\" - Convey the final outcome and the customer's reaction to the resolution.\n" +
                "\n" +
                "- Content Assessment:\n" +
                "Before summarizing, assess if the conversation contains enough information to extract a clear situation, action, and resolution.\n" +
                "\n" +
                "\n" +
                "- Language and Tone:\n" +
                "Use precise and active language in %s. Eliminate redundancy and maintain an objective tone without attributing any qualities or judgments to the participants.Distinguish whether the customer is a buyer or a seller based on the context.\n" +
                "\n" +
                "\n" +
                "Ensure that any personal data, including customer name, phone number, email, and address, is clearly marked with an asterisk (*) if present in the output.\n" +
                "Do not include any unreadable or invalid characters in the response.\n" +
                "Answer using only the data provided in the follow convesation \"%s\". \n" +
                "Messages beginning with [User] are sent by buyers or sellers, [Agent] by live agents, and [AI Agent] by the AI Agent, and [Robot] by the chatbot.\n" +
                "If no agent's message, state as \"insufficient data\".\n" +
                "Do not hallucinate anything not mentioned in the conversion.\n" +
                "If the data is unsufficient to generate the summary, reply \"insufficient data\". Otherwise reply in a JSON format like below example:\n" +
                "{\n" +
                "    \"S\" : \"Customer inquired about a missing shipment with urgency.\",\n" +
                "    \"A\" : \"Agent checked the tracking details and confirmed a carrier delay.\",\n" +
                "    \"R\" : \"Informed the customer about the delay; customer opted to wait for the shipment.\"\n" +
                "}\n" +
                "\n";
        System.out.println(String.format(prompt, "English", conv));
    }

    private static Date toSpecificZone(Date date, TimeZone timeZone) {
        try {
            System.out.println(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(timeZone);
            String zoneDateStr = sdf.format(date);
            Date rst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zoneDateStr);
            System.out.println(rst);
            return rst;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


}
