package com.danielfreitassc.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CodenameService {
    private final List<String> justiceLeagueCodenames = new ArrayList<>();
    private final List<String> avengersCodenames = new ArrayList<>();
    private final Random random = new Random();

    private static final String AVENGERS_URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
    private static final String JUSTICE_LEAGUE_URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

    @PostConstruct
    public void init() {
        loadCodenames();
    }

    private void loadCodenames() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet avengersRequest = new HttpGet(AVENGERS_URL);
            String avengersJson = httpClient.execute(avengersRequest, response -> {
                if (response.getCode() != 200) {
                    throw new HttpResponseException(response.getCode(), "Failed to fetch Avengers codenames");
                }
                return EntityUtils.toString(response.getEntity());
            });
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode avengersNode = objectMapper.readTree(avengersJson);
            avengersNode.get("vingadores").forEach(node -> {
                String codename = node.get("codinome").asText();
                avengersCodenames.add(codename);
            });

            HttpGet justiceLeagueRequest = new HttpGet(JUSTICE_LEAGUE_URL);
            String justiceLeagueXml = httpClient.execute(justiceLeagueRequest, response -> {
                if (response.getCode() != 200) {
                    throw new HttpResponseException(response.getCode(), "Failed to fetch Justice League codenames");
                }
                return EntityUtils.toString(response.getEntity());
            });
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(justiceLeagueXml.getBytes()));
            document.getDocumentElement().normalize();
            NodeList codenameNodes = document.getElementsByTagName("codinome");
            for (int i = 0; i < codenameNodes.getLength(); i++) {
                String codename = codenameNodes.item(i).getTextContent();
                justiceLeagueCodenames.add(codename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String getUniqueCodename(String group) {
        List<String> codenames = "liga_da_justica".equals(group) ? justiceLeagueCodenames : avengersCodenames;
        if (codenames.isEmpty()) {
            return null;
        }
        int index = random.nextInt(codenames.size());
        String codename = codenames.remove(index);
        return codename;
    }
}
