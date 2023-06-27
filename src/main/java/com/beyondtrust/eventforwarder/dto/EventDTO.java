package com.beyondtrust.eventforwarder.dto;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "event_table")
public class EventDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "severity")
    private String severity;

    @Column(name = "format_version")
    private String formatVersion;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "product")
    private String product;

    @Column(name = "version")
    private String version;

    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "agent_desc")
    private String agentDesc;

    @Column(name = "agent_ver")
    private String agentVer;

    @Column(name = "category")
    private String category;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_desc")
    private String eventDesc;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "source_host")
    private String sourceHost;

    @Column(name = "os")
    private String os;

    @Column(name = "source_ip")
    private String sourceIp;

    @Column(name = "event_subject")
    private String eventSubject;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "user")
    private String user;

    @Column(name = "workgroup_id")
    private String workgroupId;

    @Column(name = "workgroup_desc")
    private String workgroupDesc;

    @Column(name = "workgroup_location")
    private String workgroupLocation;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "event_table_nvps", joinColumns = @JoinColumn(name = "event_id"))
    private Map<String, String> nvps = new HashMap<>();

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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
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

    public void saveToDatabase() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(this);
                transaction.commit();
                System.out.println("Objeto EventDTO salvo no banco de dados: " + toString());
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Falha ao salvar o objeto EventDTO no banco de dados.");
                e.printStackTrace();
            }
        }
    }
}
