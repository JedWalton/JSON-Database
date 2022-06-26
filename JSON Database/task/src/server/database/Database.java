package server.database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.exceptions.KeyNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum Database {

    INSTANCE;

    private JsonObject database;
    private final Lock readLock;
    private final Lock writeLock;

    {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    Database() {
    }

    public void init() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/server/data/db.json")));
            database = new Gson().fromJson(content, JsonObject.class);
        } catch (FileNotFoundException e) {
            throw new KeyNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new KeyNotFoundException();
        }
    }

    private void writeToDb() {
        try {
            FileWriter writer = new FileWriter("src/server/data/db.json");
            writer.write(database.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(JsonElement key, JsonElement value) {
        try {
            writeLock.lock();
            if (database == null) {
                database = new JsonObject();
                database.add(key.getAsString(), value);
            } else {
                if (key.isJsonPrimitive()) {
                    database.add(key.getAsString(), value);
                } else if (key.isJsonArray()) {
                    JsonArray keys = key.getAsJsonArray();
                    String toAdd = keys.remove(keys.size() - 1).getAsString();
                    findElement(keys, true).getAsJsonObject().add(toAdd, value);
                } else {
                    throw new KeyNotFoundException();
                }
            }
            writeToDb();

        } finally {
            writeLock.unlock();
        }
    }

    public JsonElement get(JsonElement key) {
        try {
            readLock.lock();
            if (key.isJsonPrimitive() && database.has(key.getAsString())) {
                return database.get(key.getAsString());
            } else if (key.isJsonArray()) {
                return findElement(key.getAsJsonArray(), false);
            }
            throw new KeyNotFoundException();

        } finally {
            readLock.unlock();
        }
    }

    public void delete(JsonElement key) {
        try {
            writeLock.lock();
            if (key.isJsonPrimitive() && database.has(key.getAsString())) {
                database.remove(key.getAsString());
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                String toDelete = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, true).getAsJsonObject().remove(toDelete);
            }
            writeToDb();
        } finally {
            writeLock.unlock();
        }

    }

    private JsonElement findElement(JsonArray keys, boolean createIfAbsent) {
        JsonElement tempDb = database;
        if (createIfAbsent) {
            for (JsonElement key : keys) {
                if (!tempDb.getAsJsonObject().has(key.getAsString())) {
                    tempDb.getAsJsonObject().add(key.getAsString(), new JsonObject());
                }
                tempDb = tempDb.getAsJsonObject().get(key.getAsString());
            }
        } else {
            for (JsonElement key : keys) {
                if (!key.isJsonPrimitive() || !tempDb.getAsJsonObject().has(key.getAsString())) {
                    throw new KeyNotFoundException();
                }
                tempDb = tempDb.getAsJsonObject().get(key.getAsString());
            }
        }
        return tempDb;
    }

}
