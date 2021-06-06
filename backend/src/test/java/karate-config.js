function fn() {
    var env = karate.env;
    if (!env) {
        env = 'dev';
    }

    var config = {
        baseUrl: 'http://localhost:8081',
        config: karate.tags
    };

    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return config;
}
