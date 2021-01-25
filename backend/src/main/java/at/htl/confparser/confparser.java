// Config is representing the json configuration file
class Config {
    public String name; 
    public Broker broker;  
    public DB db; 
    public ClientAPI clientAPI;
    public Distributor distributor;
    public Topics topics;
    public NotificationCodes notificationCodes;
}


 // Broker represents the config of the broker
class Broker {
	public String clientID ;
	public String type;
	public Address address;
	public Auth auth;
}

// DB represents the config of the database
class DB {
	public String type;
	public String language;
	public Address address;
	public DBAuth auth;
	public Data data;
}

// Data describes what happens with the Data
class Data {
	public DeleteAfter deleteAfter;
}

// DeleteAfter contains years, months and days
class DeleteAfter {
	public int Years;
	public int Months;
	public int Days;
}

// Distributor defines to which location to log
class Distributor {
	public String type;
	public Address address;
	public DistTopics topics;
	public Auth auth;
}

// DistTopics are the topics of the distribution network
class DistTopics {
	public String Default;
}

// Topics consits of mqtt topics information
class Topics {
	public String all;
	public String measurements;
	public String lastWill;
	public String notifications;
}

// ClientAPI is the config for the REST API
class ClientAPI {
	public String url;
	public int port;
}

// Address has an IP and a Port
class Address {
	public String ip;
	public int port;
	public String caPemLocation;
	public String caKeyLocation;
}

// NotificationCodes holds codes to notify clients
class NotificationCodes {
	public int sensorDead;
}

// Join joins the ip address and the port to a valid url
/*public String join(Address adress) {
	return address.ip + ":" + String.valueOf(address.port);
}*/