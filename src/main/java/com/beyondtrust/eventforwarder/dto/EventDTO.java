package com.beyondtrust.eventforwarder.dto;

import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDTO {

    private String formatVersion;
    private String vendor;
    private String product;
    private String version;
    private String agentId;
    private String agentDesc;
    private String agentVer;
    private String category;
    private String severity;
    private String eventId;
    private String eventName;
    private String eventDesc;
    private String eventDate;
    private String sourceHost;
    private String os;
    private String sourceIp;
    private String eventSubject;
    private String eventType;
    private String user;
    private String workgroupId;
    private String workgroupDesc;
    private String workgroupLocation;
    private Map<String, String> nvps;

    public void determineSeverityLabel() {
        Map<String, String> severityLabels = new HashMap<>();
        severityLabels.put("Information", "0");
        severityLabels.put("low", "3");
        severityLabels.put("medium", "6");
        severityLabels.put("high", "9");

        if (severityLabels.containsKey(severity)) {
            String severityValue = severityLabels.get(severity);
            setSeverity(severityValue);
        } else {
            setSeverity("-1");
        }
    }


    
    // Método toString() para exibição dos dados
    @Override
    public String toString() {
        return "EventDTO{" +
                "severity='" + severity + '\'' +
                // Demais atributos da classe EventDTO...
                '}';
    }

    public static void main(String[] args) {
        EventDTO event = new EventDTO();
        event.setSeverity("low");
        event.determineSeverityLabel();
        event.saveToDatabase();
    }

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentDesc() {
        return agentDesc;
    }

    public void setAgentDesc(String agentDesc) {
        this.agentDesc = agentDesc;
    }

    public String getAgentVer() {
        return agentVer;
    }

    public void setAgentVer(String agentVer) {
        this.agentVer = agentVer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getSourceHost() {
        return sourceHost;
    }

    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getEventSubject() {
        return eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWorkgroupId() {
        return workgroupId;
    }

    public void setWorkgroupId(String workgroupId) {
        this.workgroupId = workgroupId;
    }

    public String getWorkgroupDesc() {
        return workgroupDesc;
    }

    public void setWorkgroupDesc(String workgroupDesc) {
        this.workgroupDesc = workgroupDesc;
    }

    public String getWorkgroupLocation() {
        return workgroupLocation;
    }

    public void setWorkgroupLocation(String workgroupLocation) {
        this.workgroupLocation = workgroupLocation;
    }

    public Map<String, String> getNvps() {
        return nvps;
    }

    public void setNvps(Map<String, String> nvps) {
        this.nvps = nvps;
    }

    @Autowired
    private Environment environment;

    public void saveToDatabase() {
        // Configuração de conexão com o banco de dados
        String url = environment.getProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
    
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Criação da declaração SQL com parâmetros para inserção dos dados
            String sql = "INSERT INTO event_table (severity, format_version, vendor, product, version, agent_id, agent_desc, " +
                    "agent_ver, category, event_id, event_name, event_desc, event_date, source_host, os, source_ip, " +
                    "event_subject, event_type, user, workgroup_id, workgroup_desc, workgroup_location) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            // Criação do PreparedStatement com a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Definição dos parâmetros com os valores do objeto EventDTO
                statement.setString(1, severity);
                statement.setString(2, formatVersion);
                statement.setString(3, vendor);
                statement.setString(4, product);
                statement.setString(5, version);
                statement.setString(6, agentId);
                statement.setString(7, agentDesc);
                statement.setString(8, agentVer);
                statement.setString(9, category);
                statement.setString(10, eventId);
                statement.setString(11, eventName);
                statement.setString(12, eventDesc);
                statement.setString(13, eventDate);
                statement.setString(14, sourceHost);
                statement.setString(15, os);
                statement.setString(16, sourceIp);
                statement.setString(17, eventSubject);
                statement.setString(18, eventType);
                statement.setString(19, user);
                statement.setString(20, workgroupId);
                statement.setString(21, workgroupDesc);
                statement.setString(22, workgroupLocation);
    
                // Execução da inserção no banco de dados
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Objeto EventDTO salvo no banco de dados: " + toString());
                } else {
                    System.out.println("Falha ao salvar o objeto EventDTO no banco de dados.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
