package domain;

import spark.Request;

public class SessionService {
    private static final String UUID = "uuid";

    public static Long getSessionId(Request req) {
        return req.session().attribute(UUID);
    }

    public static void setSessionId(Request req, Long uuid) {
        req.session().attribute(UUID, uuid);
    }

    public static void removeSessionId(Request req) { req.session().removeAttribute(UUID); }
}
