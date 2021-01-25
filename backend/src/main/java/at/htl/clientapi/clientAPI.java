
// ClientAPI is an empty struct that's in an controller
// Maybe later it'll have content
class ClientAPI {
	public int DefaultLangID;
    public db.DB DB;  
    
    ClientAPI clientAPI;

    // Start starts the client api
    public void start(){

        db.DB db = db.New(dbConf.Type);
    
        if( db == null) {
		System.out.println("failed to create db");
        }
        
       bool ready = db.Connect(dbConf, ready);
	

        Integer langID = db.GetDefaultLanguageID();

        if( langID == null ){
            System.out.println("can't get default language")
        }


        clientAPI.DB = db;
        clientAPI.DefaultLangID = langID;


        router = NewRouter();

        address = confparser.Join(conf.URL, conf.Port);
        http.ListenAndServe(address, router);
        System.out.println("ClientAPI started");
        }


}

 