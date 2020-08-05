package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Song;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongRepository {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String file = "resources/songFile.json";

    public Song saveSong(Song song) {
        List<Song> songs = getAllSongs();
        try(FileWriter writer = new FileWriter(file)) {
            songs.add(song);
            gson.toJson(songs, writer);
            return song;
        }catch (IOException e){
            throw new RuntimeException("Error saving song: " + e);
        }
    }

    public Optional<Song> getSongById(String id) {
        return getAllSongs().stream().filter(s -> s.getId().equals(id)).findAny();
    }

    public List<Song> getAllSongs() {
        Type listType = new TypeToken<ArrayList<Song>>(){}.getType();
        return gson.fromJson(file, listType);
    }

}
