CREATE TABLE IF NOT EXISTS events (
  id INT AUTO_INCREMENT PRIMARY KEY,
  format_version VARCHAR(10) NOT NULL,
  vendor VARCHAR(50) NOT NULL,
  product VARCHAR(50) NOT NULL,
  version VARCHAR(20) NOT NULL,
  agent_id VARCHAR(50) NOT NULL,
  agent_desc VARCHAR(100) NOT NULL,
  agent_ver VARCHAR(20) NOT NULL,
  category VARCHAR(50) NOT NULL,
  severity INT NOT NULL,
  event_id VARCHAR(50) NOT NULL,
  event_name VARCHAR(100) NOT NULL,
  event_desc VARCHAR(255) NOT NULL,
  event_date DATETIME NOT NULL,
  source_host VARCHAR(100) NOT NULL,
  os VARCHAR(100) NOT NULL,
  source_ip VARCHAR(20) NOT NULL,
  event_subject VARCHAR(100) NOT NULL,
  event_type INT NOT NULL,
  user VARCHAR(100) NOT NULL,
  workgroup_id VARCHAR(100) NOT NULL,
  workgroup_desc VARCHAR(100) NOT NULL,
  workgroup_location VARCHAR(100) NOT NULL,
  nvps TEXT NOT NULL
);
