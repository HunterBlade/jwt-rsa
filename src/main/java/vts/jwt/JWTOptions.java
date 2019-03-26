package vts.jwt;

import vts.jwt.json.JsonArray;
import vts.jwt.json.JsonObject;

import java.util.List;

public class JWTOptions {

    private final JsonObject json;

    public JWTOptions() {
        json = new JsonObject();
    }

    public JWTOptions(JsonObject json) {
        this.json = json.copy();
    }

    public JWTOptions(JWTOptions options) {
        this(options.toJson());
    }

    public String getAlgorithm() {
        return json.getString("algorithm", "HS256");
    }

    /**
     * The algorithm to use, it should be one of the alias [HS256, HS384, HS512, RS256, RS384, RS512, ES256, ES384, ES512]
     * @param algorithm alias to keystore MAC/Certificate
     * @return fluent API
     */
    public JWTOptions setAlgorithm(String algorithm) {
        json.put("algorithm", algorithm);
        return this;
    }

    public Long getExpiresInMinutes() {
        return json.getLong("expiresInMinutes");
    }

    /**
     * The expiration time for the token in minutes
     * @param expiresInMinutes time in minutes
     * @return fluent API
     */
    public JWTOptions setExpiresInMinutes(Long expiresInMinutes) {
        if (expiresInMinutes != null) {
            json.put("expiresInMinutes", expiresInMinutes);
        } else {
            json.remove("expiresInMinutes");
        }
        return this;
    }

    public Long getExpiresInSeconds() {
        return json.getLong("expiresInSeconds");
    }

    /**
     * The expiration time for the token in seconds
     * @param expiresInSeconds time in seconds
     * @return fluent API
     */
    public JWTOptions setExpiresInSeconds(Long expiresInSeconds) {
        if (expiresInSeconds != null) {
            json.put("expiresInSeconds", expiresInSeconds);
        } else {
            json.remove("expiresInSeconds");
        }
        return this;
    }

    public List<String> getAudience() {
        return json.getJsonArray("audience") != null ? json.getJsonArray("audience").getList() : null;
    }

    /**
     * The target audience of this token
     * @param audience the audience for this token
     * @return fluent API
     */
    public JWTOptions setAudience(List<String> audience) {
        json.put("audience", new JsonArray(audience));
        return this;
    }

    /**
     * The target audience of this token
     * @param audience the audience for this token
     * @return fluent API
     */
    public JWTOptions addAudience(String audience) {
        if (!json.containsKey("audience")) {
            json.put("audience", new JsonArray());
        }

        json.getJsonArray("audience").add(audience);
        return this;
    }

    public String getSubject() {
        return json.getString("subject");
    }

    /**
     * The subject of this token
     * @param subject the subject for this token
     * @return fluent API
     */
    public JWTOptions setSubject(String subject) {
        json.put("subject", subject);
        return this;
    }

    public String getIssuer() {
        return json.getString("issuer");
    }

    /**
     * The issuer of this token
     * @param issuer the subject for this token
     * @return fluent API
     */
    public JWTOptions setIssuer(String issuer) {
        json.put("issuer", issuer);
        return this;
    }

    public boolean getNoTimestamp() {
        return json.getBoolean("noTimestamp", false);
    }

    /**
     * Disable the generation of issued at claim
     * @param noTimestamp flag to control iat claim
     * @return fluent API
     */
    public JWTOptions setNoTimestamp(boolean noTimestamp) {
        json.put("noTimestamp", noTimestamp);
        return this;
    }

    public JsonObject getHeader() {
        return json.getJsonObject("header");
    }

    public JWTOptions addHeader(String name, String value) {
        if (!json.containsKey("header")) {
            json.put("header", new JsonObject());
        }

        getHeader().put(name, value);
        return this;
    }

    /**
     * The permissions of this token.
     *
     * @param permissions the permissions for this token that will be used for AuthZ
     * @return fluent API
     */
    public JWTOptions setPermissions(List<String> permissions) {
        json.put("permissions", new JsonArray(permissions));
        return this;
    }

    /**
     * Add a permission to this token.
     *
     * @param permission permission for this token that will be used for AuthZ
     * @return fluent API
     */
    public JWTOptions addPermission(String permission) {
        if (!json.containsKey("permissions")) {
            json.put("permissions", new JsonArray());
        }

        json.getJsonArray("permissions").add(permission);
        return this;
    }

    public JsonObject toJson() {
        return json;
    }
}
